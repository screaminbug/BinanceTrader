package hr.from.tomislav_strelar.binancetrader.data;

/**
 * Created by Tomislav on 15.10.2017..
 */

public interface AfterUpdateListener {
    public void afterDepthUpdate(Depth depth);
    public void afterAccountUpdate(Account account);
    public void afterKlineUpdate(Kline kline);
    public void afterOrderUpdate(Order order);
    public void afterTradeUpdate(Trade trade);
}
