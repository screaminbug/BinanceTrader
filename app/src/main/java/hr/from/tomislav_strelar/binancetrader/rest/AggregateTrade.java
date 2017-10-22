
package hr.from.tomislav_strelar.binancetrader.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AggregateTrade {

    @SerializedName("a")
    @Expose
    private int a;
    @SerializedName("p")
    @Expose
    private String p;
    @SerializedName("q")
    @Expose
    private String q;
    @SerializedName("f")
    @Expose
    private int f;
    @SerializedName("l")
    @Expose
    private int l;
    @SerializedName("T")
    @Expose
    private int t;
    @SerializedName("m")
    @Expose
    private boolean m;
    @SerializedName("M")
    @Expose
    private boolean ma;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public boolean isM() {
        return m;
    }

    public void setM(boolean m) {
        this.m = m;
    }

    public boolean isMa() {
        return ma;
    }

    public void setMa(boolean ma) {
        this.ma = ma;
    }

}
