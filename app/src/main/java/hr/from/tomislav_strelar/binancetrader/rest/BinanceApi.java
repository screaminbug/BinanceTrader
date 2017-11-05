package hr.from.tomislav_strelar.binancetrader.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hr.from.tomislav_strelar.binancetrader.OkHttp;
import hr.from.tomislav_strelar.binancetrader.rest.deserializers.OrdersMixedArray;
import hr.from.tomislav_strelar.binancetrader.rest.deserializers.OrdersMixedArrayDeserializer;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Tomislav on 21.10.2017..
 */

public class BinanceApi {
    private static final String BASE_URL = "https://www.binance.com/";
    private RxJava2CallAdapterFactory rxAdapter;
    private Gson gson;
    private Retrofit retrofit;

    private static BinanceApiInterface instanceRef;

    private BinanceApi() {
        rxAdapter = RxJava2CallAdapterFactory.create();
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(OrdersMixedArray.class, new OrdersMixedArrayDeserializer())
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
