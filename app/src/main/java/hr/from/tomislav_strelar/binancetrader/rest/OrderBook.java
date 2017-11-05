
package hr.from.tomislav_strelar.binancetrader.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import hr.from.tomislav_strelar.binancetrader.rest.deserializers.OrdersMixedArray;

public class OrderBook {



    @SerializedName("lastUpdateId")
    @Expose
    private int lastUpdateId;
    @SerializedName("bids")
    @Expose
    private List<OrdersMixedArray> bids = null;
    @SerializedName("asks")
    @Expose
    private List<OrdersMixedArray> asks = null;

    public int getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(int lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public List<OrdersMixedArray> getBids() {
        return bids;
    }

    public void setBids(List<OrdersMixedArray> bids) {
        this.bids = bids;
    }

    public List<OrdersMixedArray> getAsks() {
        return asks;
    }

    public void setAsks(List<OrdersMixedArray> asks) {
        this.asks = asks;
    }

}
