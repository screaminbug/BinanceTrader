package hr.from.tomislav_strelar.binancetrader.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import hr.from.tomislav_strelar.binancetrader.websocket.WebsockCommand;

/**
 * Created by Tomislav on 7.10.2017..
 */

public class Depth extends BinanceData {
    private static final String TAG = "Depth";
    private static final BigDecimal ZERO = new BigDecimal("0.00000000");

    private int updateId;
    private Map<BigDecimal, BigDecimal> mBids = new TreeMap<>(Collections.reverseOrder());
    private Map<BigDecimal, BigDecimal> mAsks = new TreeMap<>();

    private Map<BigDecimal, BigDecimal> mCurrentBids = new TreeMap<>(Collections.reverseOrder());
    private Map<BigDecimal, BigDecimal> mCurrentAsks = new TreeMap<>();

    public Depth(AllPrices.Symbol symbol, AfterUpdateListener listener) { super(symbol, listener); }

    public void update(JSONObject json) throws JSONException {
        super.update(json);
        if (!getSymbol().toString().equalsIgnoreCase(json.getString("s"))) {
            throw new JSONException("Can't find correct symbol in response. Expected: " + getSymbol().toString() + " Got: " + json.getString("s"));
        }
        updateId = json.getInt("u");
        updateBid(json.getJSONArray("b"));
        updateAsk(json.getJSONArray("a"));
        getAfterUpdateListener().afterDepthUpdate(this);
    }

    public int getUpdateId() {
        return updateId;
    }

    public WebsockCommand getWebSockCommand() { return WebsockCommand.DEPTH; }

    public Map<BigDecimal, BigDecimal> getBids() {
        return mBids;
    }

    public Map<BigDecimal, BigDecimal> getAsks() {
        return mAsks;
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