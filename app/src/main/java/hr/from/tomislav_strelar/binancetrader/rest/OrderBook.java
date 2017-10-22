
package hr.from.tomislav_strelar.binancetrader.rest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderBook {

    @SerializedName("lastUpdateId")
    @Expose
    private int lastUpdateId;
    @SerializedName("bids")
    @Expose
    private List<List<String>> bids = null;
    @SerializedName("asks")
    @Expose
    private List<List<String>> asks = null;

    public int getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(int lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public void setBids(List<List<String>> bids) {
        this.bids = bids;
    }

    public List<List<String>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<String>> asks) {
        this.asks = asks;
    }

}
