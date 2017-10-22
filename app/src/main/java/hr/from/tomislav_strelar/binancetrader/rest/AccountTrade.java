
package hr.from.tomislav_strelar.binancetrader.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountTrade {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("commission")
    @Expose
    private String commission;
    @SerializedName("commissionAsset")
    @Expose
    private String commissionAsset;
    @SerializedName("time")
    @Expose
    private int time;
    @SerializedName("isBuyer")
    @Expose
    private boolean isBuyer;
    @SerializedName("isMaker")
    @Expose
    private boolean isMaker;
    @SerializedName("isBestMatch")
    @Expose
    private boolean isBestMatch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getCommissionAsset() {
        return commissionAsset;
    }

    public void setCommissionAsset(String commissionAsset) {
        this.commissionAsset = commissionAsset;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isIsBuyer() {
        return isBuyer;
    }

    public void setIsBuyer(boolean isBuyer) {
        this.isBuyer = isBuyer;
    }

    public boolean isIsMaker() {
        return isMaker;
    }

    public void setIsMaker(boolean isMaker) {
        this.isMaker = isMaker;
    }

    public boolean isIsBestMatch() {
        return isBestMatch;
    }

    public void setIsBestMatch(boolean isBestMatch) {
        this.isBestMatch = isBestMatch;
    }

}
