package hr.from.tomislav_strelar.binancetrader.data;

import android.util.Log;

import java.util.List;

/**
 * Created by Tomislav on 15.10.2017..
 */

public class DefaultAfterUpdateListener implements AfterUpdateListener {
    private static final String TAG = "DefaultAfterUpdateList";

    @Override
    public void afterDepthUpdate(Depth depth) {
        Log.i(TAG, "No after update implemented for Depth");
    }

    @Override
    public void afterAccountUpdate(Account account) {
        Log.i(TAG, "No after update implemented for Account");
    }

    @Override
    public void afterKlineUpdate(Kline kline) {
        Log.i(TAG, "No after update implemented for Kline");
    }

    @Override
    public void afterOrderUpdate(Order order) {
        Log.i(TAG, "No after update implemented for Order");
    }

    @Override
    public void afterTradeUpdate(Trade trade) {
        Log.i(TAG, "No after update implemented for Update");
    }

}
