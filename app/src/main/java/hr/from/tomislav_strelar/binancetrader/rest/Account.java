
package hr.from.tomislav_strelar.binancetrader.rest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("makerCommission")
    @Expose
    private int makerCommission;
    @SerializedName("takerCommission")
    @Expose
    private int takerCommission;
    @SerializedName("buyerCommission")
    @Expose
    private int buyerCommission;
    @SerializedName("sellerCommission")
    @Expose
    private int sellerCommission;
    @SerializedName("canTrade")
    @Expose
    private boolean canTrade;
    @SerializedName("canWithdraw")
    @Expose
    private boolean canWithdraw;
    @SerializedName("canDeposit")
    @Expose
    private boolean canDeposit;
    @SerializedName("balances")
    @Expose
    private List<Balance> balances = null;

    public int getMakerCommission() {
        return makerCommission;
    }

    public void setMakerCommission(int makerCommission) {
        this.makerCommission = makerCommission;
    }

    public int getTakerCommission() {
        return takerCommission;
    }

    public void setTakerCommission(int takerCommission) {
        this.takerCommission = takerCommission;
    }

    public int getBuyerCommission() {
        return buyerCommission;
    }

    public void setBuyerCommission(int buyerCommission) {
        this.buyerCommission = buyerCommission;
    }

    public int getSellerCommission() {
        return sellerCommission;
    }

    public void setSellerCommission(int sellerCommission) {
        this.sellerCommission = sellerCommission;
    }

    public boolean isCanTrade() {
        return canTrade;
    }

    public void setCanTrade(boolean canTrade) {
        this.canTrade = canTrade;
    }

    public boolean isCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    public boolean isCanDeposit() {
        return canDeposit;
    }

    public void setCanDeposit(boolean canDeposit) {
        this.canDeposit = canDeposit;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

}
