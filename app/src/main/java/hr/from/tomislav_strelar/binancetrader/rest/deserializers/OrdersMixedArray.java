package hr.from.tomislav_strelar.binancetrader.rest.deserializers;

import java.util.List;

/**
 * Created by Tomislav on 5.11.2017..
 */

public class OrdersMixedArray {
    private String price;
    private String quantity;
    private List<String> rfu;

    OrdersMixedArray(String price, String quantity, List<String> rfu) {
        this.price = price;
        this.quantity = quantity;
        this.rfu = rfu;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public List<String> getRfu() {
        return rfu;
    }

    @Override
    public String toString() {
        return String.format("{OrdersMixedArray: price=%s, quantity=%s, rfu=%s}",
                price, quantity, rfu.toString());
    }
}