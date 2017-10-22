
package hr.from.tomislav_strelar.binancetrader.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ping {

    @SerializedName("serverTime")
    @Expose
    private Integer serverTime;

    public Integer getServerTime() {
        return serverTime;
    }

    public void setServerTime(Integer serverTime) {
        this.serverTime = serverTime;
    }

}
