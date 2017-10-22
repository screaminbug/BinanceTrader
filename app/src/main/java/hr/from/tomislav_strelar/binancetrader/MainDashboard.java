package hr.from.tomislav_strelar.binancetrader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import hr.from.tomislav_strelar.binancetrader.data.AllPrices;
import hr.from.tomislav_strelar.binancetrader.data.DefaultAfterUpdateListener;
import hr.from.tomislav_strelar.binancetrader.data.Depth;
import hr.from.tomislav_strelar.binancetrader.rest.BinanceApi;
import hr.from.tomislav_strelar.binancetrader.rest.BinanceApiInterface;
import hr.from.tomislav_strelar.binancetrader.rest.Symbol;
import hr.from.tomislav_strelar.binancetrader.websocket.BinanceWebSocket;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainDashboard.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainDashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainDashboard extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String LOG_TAG = "MainDashboard";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View view;
    private Button startButton;
    private Button stopButton;
    private TextView outputTextView;
    private List<BinanceWebSocket> webSocks = new ArrayList<>();

    LineChart depthChart;

    private BinanceApiInterface rest = BinanceApi.getInstance();
    private Observable<List<Symbol>> symbolsObservable = rest.getAllSymbolPrices();

    Subscription subscription = symbolsObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<Symbol>>() {

                @Override
                public void onCompleted() {
                    Log.i(LOG_TAG, "symbolsObservable complete");
                }

                @Override
                public void onError(Throwable throwable) {
                    if (throwable instanceof HttpException) {
                        HttpException response = (HttpException)throwable;
                        int code = response.code();
                        Log.i(LOG_TAG, "Error in symbolsObservable: " + code + ": " + response.getLocalizedMessage());
                    }
                }

                @Override
                public void onNext(List<Symbol> symbols) {
                    Log.i(LOG_TAG, symbols.toString());
                }
            } );

    public MainDashboard() {
        // Required empty public constructor
    }

    private final class DepthChartUpdater extends DefaultAfterUpdateListener {
        @Override
        public void afterDepthUpdate(Depth depth) {
            chart(depth.getBids(), depth.getAsks());
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param savedMessage Last message received.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainDashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static MainDashboard newInstance(String savedMessage, String param2) {
        MainDashboard fragment = new MainDashboard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, savedMessage);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        Log.i(LOG_TAG, "Instantiated with " + savedMessage + " " + param2);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Depth depth = new Depth(new AllPrices.Symbol("ETHBTC"), new DepthChartUpdater());

        webSocks.add(new BinanceWebSocket(depth));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        subscription.unsubscribe();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(LOG_TAG,"Inflating Main Dashboard Fragment");

        View view = inflater.inflate(R.layout.fragment_main_dashboard, container, false);
        Log.i(LOG_TAG,"Created Main Dashboard Fragment: " + startButton + " " + outputTextView);

        startButton = (Button) view.findViewById(R.id.start_button);
        stopButton = (Button) view.findViewById(R.id.stop_button);
        outputTextView = (TextView) view.findViewById(R.id.output_start_textview);
        depthChart = (LineChart) view.findViewById(R.id.depth_chart);

        outputTextView.setText(mParam1);


        startButton.setOnClickListener(v -> {

            for (BinanceWebSocket sock : webSocks) {
                sock.connect();
            }
        });

        stopButton.setOnClickListener(v -> {
            for (BinanceWebSocket sock : webSocks) {
                sock.close();
            }
        });

        return view;
    }

    public void output(final String txt) {

        Activity current = getActivity();
        if (current != null) {
            saveLastMessage(txt);
            current.runOnUiThread(() -> outputTextView.setText(txt));
        } else {
            for (BinanceWebSocket sock : webSocks) {
                sock.close();
            }
        }
    }

    public void chart(Map<BigDecimal, BigDecimal> bids, Map<BigDecimal, BigDecimal> asks) {
        List<Entry> bidEntries = new ArrayList<>();
        for (BigDecimal price : bids.keySet()) {
            BigDecimal amount = bids.get(price);
            bidEntries.add(new Entry(price.floatValue(), amount.floatValue()));
        }

        List<Entry> askEntries = new ArrayList<>();
        for (BigDecimal price : asks.keySet()) {
            BigDecimal amount = asks.get(price);
            askEntries.add(new Entry(price.floatValue(), amount.floatValue()));
        }

        if (bidEntries.size() > 0 && askEntries.size() > 0) {
            Collections.sort(bidEntries, new EntryXComparator());
            Collections.sort(askEntries, new EntryXComparator());

            LineDataSet bidsDataSet = new LineDataSet(bidEntries, "Bids"); // add entries to dataset
            Log.i(LOG_TAG, "Bids: " + bidEntries);
            bidsDataSet.setColor(Color.GREEN);
            bidsDataSet.setValueTextColor(Color.BLACK);
            bidsDataSet.setDrawFilled(true);
            bidsDataSet.setFillColor(Color.GREEN);


            LineDataSet asksDataSet = new LineDataSet(askEntries, "Asks"); // add entries to dataset
            Log.i(LOG_TAG, "Asks: " + askEntries);
            asksDataSet.setColor(Color.RED);
            asksDataSet.setValueTextColor(Color.BLACK);
            asksDataSet.setDrawFilled(true);
            asksDataSet.setFillColor(Color.RED);

            final LineData linedata = new LineData();
            linedata.addDataSet(bidsDataSet);
            linedata.addDataSet(asksDataSet);

            Activity current = getActivity();
            if (current != null) {
                current.runOnUiThread(() -> {
                    depthChart.invalidate();
                    depthChart.setData(linedata);
                });
            }
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void saveLastMessage(String mess) {
        if (mListener != null) {
            mListener.onFragmentInteraction(mess);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String mess);
    }
}
