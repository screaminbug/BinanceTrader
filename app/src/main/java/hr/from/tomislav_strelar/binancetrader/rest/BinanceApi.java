package hr.from.tomislav_strelar.binancetrader.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hr.from.tomislav_strelar.binancetrader.OkHttp;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Tomislav on 21.10.2017..
 */

public class BinanceApi {
    public static final String BASE_URL = "https://www.binance.com/";
    RxJavaCallAdapterFactory rxAdapter;
    Gson gson;
    Retrofit retrofit;

    private static BinanceApiInterface instanceRef;

    private BinanceApi() {
        rxAdapter = RxJavaCallAdapterFactory.create();
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        retrofit = new Retrofit.Builder()
                .client(OkHttp.client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();
    }

    public static BinanceApiInterface getInstance() {
        if (instanceRef == null) {
            BinanceApi api = new BinanceApi();
            instanceRef = api.retrofit.create(BinanceApiInterface.class);
        }
        return instanceRef;
    }
}
