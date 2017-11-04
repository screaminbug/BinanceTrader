package hr.from.tomislav_strelar.binancetrader.data;

import java.util.List;

/**
 * Created by Tomislav on 15.10.2017..
 */

public interface AfterUpdateListener {
    void afterDepthUpdate(Depth depth);
    void afterAccountUpdate(Account account);
    void afterKlineUpdate(Kline kline);
    void afterOrderUpdate(Order order);
    void afterTradeUpdate(Trade trade);
}
