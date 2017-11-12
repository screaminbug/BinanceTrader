package hr.from.tomislav_strelar.binancetrader.data;

import android.util.Log;

import com.github.mikephil.charting.data.LineData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import hr.from.tomislav_strelar.binancetrader.rest.OrderBook;
import hr.from.tomislav_strelar.binancetrader.rest.deserializers.OrdersMixedArray;
import hr.from.tomislav_strelar.binancetrader.ui.DepthChart;
import hr.from.tomislav_strelar.binancetrader.websocket.WebsockCommand;

/**
 * Created by Tomislav on 7.10.2017..
 */

public class Depth extends BinanceData<Depth> {
    private static final String TAG = "Depth";
    private static final BigDecimal ZERO = new BigDecimal("0.00000000");

    private int updateId;
    private boolean isInitialized = false;

    private Map<BigDecimal, BigDecimal> mBids = new TreeMap<>(Collections.reverseOrder());
    private Map<BigDecimal, BigDecimal> mAsks = new TreeMap<>();

    private Map<BigDecimal, BigDecimal> mCurrentBids = new TreeMap<>(Collections.reverseOrder());
    private Map<BigDecimal, BigDecimal> mCurrentAsks = new TreeMap<>();

    private DepthChart depthChart = new DepthChart();

    public Depth(AllPrices.Symbol symbol) { super(symbol); }

    public void initWithOrderBook(OrderBook orderbook) {
        synchronized (this) {
            Log.i(TAG, "initWithOrderBook() starts");
            for (OrdersMixedArray ask : orderbook.getAsks()) {
                mCurrentAsks.put(
                        new BigDecimal(ask.getPrice()),
                        new BigDecimal(ask.getQuantity())
                );
            }

            for (OrdersMixedArray bid : orderbook.getBids()) {
                mCurrentBids.put(
                        new BigDecimal(bid.getPrice()),
                        new BigDecimal(bid.getQuantity())
                );
            }

            updateCumul(mCurrentBids, mBids);
            updateCumul(mCurrentAsks, mAsks);
            isInitialized = true;

            emitUpdate();

            Log.i(TAG, "initWithOrderBook() ends");
        }
    }

    public void update(JSONObject json) {
        synchronized (this) {
            Log.i(TAG, "update() starts");
            if (isInitialized) {
                try {
                    super.update(json);
                    if (getSymbol().toString().equalsIgnoreCase(json.getString("s"))) {
                        updateId = json.getInt("u");
                        updateBid(json.getJSONArray("b"));
                        updateAsk(json.getJSONArray("a"));

                        emitUpdate();

                    } else {
                        onError(
                                new InvalidParameterException(
                                        "Can't find correct symbol in response. Expected: "
                                                + getSymbol().toString() + " Got: "
                                                + json.getString("s")
                                )
                        );
                    }
                } catch (JSONException jsonEx) {
                    onError(jsonEx);
                }

            }
            Log.i(TAG, "update() ends");
        }
    }

    public int getUpdateId() {
        return updateId;
    }

    public LineData getLineData() {
        return depthChart.getLineData();
    }

    public WebsockCommand getWebSockCommand() { return WebsockCommand.DEPTH; }

    public Map<BigDecimal, BigDecimal> getBids() {
        return mBids;
    }

    public Map<BigDecimal, BigDecimal> getAsks() {
        return mAsks;
    }

    private void emitUpdate() {
        depthChart.updateLineData(mBids, mAsks);
        onUpdate(this);
    }

    private void updateDepth(JSONArray data, Map<BigDecimal, BigDecimal> current, Map<BigDecimal, BigDecimal> cumul) throws JSONException{
        if (data == null) return;

        for (int i = 0; i < data.length(); ++i) {
            BigDecimal price = new BigDecimal(data.getJSONArray(i).getString(0));
            BigDecimal quantity = new BigDecimal(data.getJSONArray(i).getString(1));

            if (quantity.equals(ZERO)) {
                current.remove(price);
                cumul.remove(price);
            } else {
                current.put(price, quantity);
            }
        }
        updateCumul(current, cumul);

    }

    private void updateCumul(Map<BigDecimal, BigDecimal> current, Map<BigDecimal, BigDecimal> cumul) {
        BigDecimal total = ZERO;
        for (BigDecimal price : current.keySet()) {
            BigDecimal quant = current.get(price);
            cumul.put(price, quant.add(total));
            total = total.add(quant);
        }
    }

    private void updateAsk(JSONArray asks) throws JSONException {
        updateDepth(asks, mCurrentAsks, mAsks);
    }

    private void updateBid(JSONArray bids) throws JSONException {
        updateDepth(bids, mCurrentBids, mBids);
    }


    @Override
    public String toString() {
        return "Event time:" +  getEventTime() + "\n" +
                "Symbol:" +  getSymbol() + "\n" +
                "Update Id:" +  updateId + "\n" +
                "Bids:" +  mBids + "\n" +
                "Asks:" +  mAsks + "\n";
    }
}