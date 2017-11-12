package hr.from.tomislav_strelar.binancetrader.ui;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomislav on 12.11.2017..
 */

public class DepthChart {
    private LineData lineData;
    private static final String LOG_TAG = "DepthChart";

    public void updateLineData(Map<BigDecimal, BigDecimal> bids, Map<BigDecimal, BigDecimal> asks) {
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
            bidsDataSet.setDrawCircles(false);


            LineDataSet asksDataSet = new LineDataSet(askEntries, "Asks"); // add entries to dataset
            Log.i(LOG_TAG, "Asks: " + askEntries);
            asksDataSet.setColor(Color.RED);
            asksDataSet.setValueTextColor(Color.BLACK);
            asksDataSet.setDrawFilled(true);
            asksDataSet.setFillColor(Color.RED);
            asksDataSet.setDrawCircles(false);

            lineData = new LineData();
            lineData.addDataSet(bidsDataSet);
            lineData.addDataSet(asksDataSet);
        }
    }

    public LineData getLineData() {
        return lineData;
    }

}
