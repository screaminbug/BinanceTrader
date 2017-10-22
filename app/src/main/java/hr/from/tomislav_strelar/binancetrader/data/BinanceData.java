package hr.from.tomislav_strelar.binancetrader.data;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import hr.from.tomislav_strelar.binancetrader.websocket.WebsockCommand;

/**
 * Created by Tomislav on 15.10.2017..
 */

public abstract class BinanceData {
    private static final String TAG = "BinanceData";

    private Date eventTime;
    private AllPrices.Symbol symbol;
    private String listenKey;
    private AfterUpdateListener afterUpdateListener;

    public BinanceData(AllPrices.Symbol symbol, AfterUpdateListener afterUpdateListener) {
        this(symbol, null, afterUpdateListener);

    }

    public BinanceData(String listenKey, AfterUpdateListener afterUpdateListener) {
        this(new AllPrices.Symbol(""), listenKey, afterUpdateListener);
    }

    private BinanceData(AllPrices.Symbol symbol, String listenKey, AfterUpdateListener afterUpdateListener) {
        if (symbol == null) throw new InvalidParameterException("Symbol can't be null");
        this.symbol = symbol;
        this.listenKey = listenKey;
        setAfterUpdateListener(afterUpdateListener);
    }

    public void update(JSONObject json) throws JSONException {
        eventTime = new Date(json.getInt("E"));
    }

    public void setAfterUpdateListener(AfterUpdateListener listener) {
        if (listener == null) { afterUpdateListener = new DefaultAfterUpdateListener(); }
        else { afterUpdateListener = listener; }
    }

    public Date getEventTime() { return eventTime; }

    public AllPrices.Symbol getSymbol() { return symbol; }

    public String getListenKey() { return listenKey; }

    public abstract int getUpdateId();

    public abstract WebsockCommand getWebSockCommand();

    //public abstract RestCommand getRestCommand();

    protected AfterUpdateListener getAfterUpdateListener() {
        return afterUpdateListener;
    }



}
