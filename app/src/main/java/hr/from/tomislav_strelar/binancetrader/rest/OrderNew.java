
package hr.from.tomislav_strelar.binancetrader.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderNew {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("orderId")
    @Expose
    private int orderId;
    @SerializedName("clientOrderId")
    @Expose
    private String clientOrderId;
    @SerializedName("transactTime")
    @Expose
    private int transactTime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public int getTransactTime() {
        return transactTime;
    }

    public void setTransactTime(int transactTime) {
        this.transactTime = transactTime;
    }

}
