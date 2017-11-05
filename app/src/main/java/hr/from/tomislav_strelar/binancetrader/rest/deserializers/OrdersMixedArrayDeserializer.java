package hr.from.tomislav_strelar.binancetrader.rest.deserializers;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import hr.from.tomislav_strelar.binancetrader.rest.OrderBook;

/**
 * Created by Tomislav on 5.11.2017..
 */

public class OrdersMixedArrayDeserializer implements JsonDeserializer<OrdersMixedArray> {
    @Override
    public OrdersMixedArray deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException    {
        JsonArray jsonArray = json.getAsJsonArray();
        String price = jsonArray.get(0).getAsString();
        String quantity = jsonArray.get(1).getAsString();
        JsonArray jsonArray2 = jsonArray.get(2).getAsJsonArray();
        int length = jsonArray2.size();
        List<String> rfu = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            String str = jsonArray2.get(i).getAsString();
            rfu.set(i, str);
        }
        return new OrdersMixedArray(price, quantity, rfu);
    }
}