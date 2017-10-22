package hr.from.tomislav_strelar.binancetrader.rest;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tomislav on 21.10.2017..
 */

public interface BinanceApiInterface {
    @GET("depth")
    Observable<OrderBook> getOrderBook(@Query("symbol") String symbol, @Query("limit") int limit);

    @GET("depth")
    Observable<OrderBook> getOrderBook(@Query("symbol") String symbol);

    @GET("ticker/allPrices")
    Observable<List<Symbol>> getAllSymbolPrices();
}
