package hr.from.tomislav_strelar.binancetrader.rest;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Tomislav on 21.10.2017..
 */

public interface BinanceApiInterface {
    @GET("api/v1/depth")
    Single<OrderBook> getOrderBook(@Query("symbol") String symbol, @Query("limit") int limit);

    @GET("api/v1/depth")
    Single<OrderBook> getOrderBook(@Query("symbol") String symbol);

    @GET("api/v1/ticker/allPrices")
    Single<List<Symbol>> getAllSymbolPrices();
}
