
package hr.from.tomislav_strelar.binancetrader.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticker24 {

    @SerializedName("priceChange")
    @Expose
    private String priceChange;
    @SerializedName("priceChangePercent")
    @Expose
    private String priceChangePercent;
    @SerializedName("weightedAvgPrice")
    @Expose
    private String weightedAvgPrice;
    @SerializedName("prevClosePrice")
    @Expose
    private String prevClosePrice;
    @SerializedName("lastPrice")
    @Expose
    private String lastPrice;
    @SerializedName("bidPrice")
    @Expose
    private String bidPrice;
    @SerializedName("askPrice")
    @Expose
    private String askPrice;
    @SerializedName("openPrice")
    @Expose
    private String openPrice;
    @SerializedName("highPrice")
    @Expose
    private String highPrice;
    @SerializedName("lowPrice")
    @Expose
    private String lowPrice;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("openTime")
    @Expose
    private int openTime;
    @SerializedName("closeTime")
    @Expose
    private int closeTime;
    @SerializedName("fristId")
    @Expose
    private int fristId;
    @SerializedName("lastId")
    @Expose
    private int lastId;
    @SerializedName("count")
    @Expose
    private int count;

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(String priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public String getWeightedAvgPrice() {
        return weightedAvgPrice;
    }

    public void setWeightedAvgPrice(String weightedAvgPrice) {
        this.weightedAvgPrice = weightedAvgPrice;
    }

    public String getPrevClosePrice() {
        return prevClosePrice;
    }

    public void setPrevClosePrice(String prevClosePrice) {
        this.prevClosePrice = prevClosePrice;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(String askPrice) {
        this.askPrice = askPrice;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getOpenTime() {
        return openTime;
    }

    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }

    public int getFristId() {
        return fristId;
    }

    public void setFristId(int fristId) {
        this.fristId = fristId;
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
