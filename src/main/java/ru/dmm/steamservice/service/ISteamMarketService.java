package ru.dmm.steamservice.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import ru.dmm.steamservice.data.rs.ItemCurrencyRs;
import ru.dmm.steamservice.data.rs.SellItemRs;

import java.util.Map;

/**
 * Created by Dmitry
 */
public interface ISteamMarketService {

    //FIXME Посмотреть http://steamcommunity.com/market/pricehistory/?appid=753&market_hash_name=292370-Xia
    //FIXME Возвращает историю
    @GET("listings/{appid}/{market_hash_name}")
    Call<ResponseBody> getMarketItemRawHtml(@Path("appid") long appId, @Path("market_hash_name") String marketHashName);

    @GET("itemordershistogram")
    Call<ItemCurrencyRs> getMarketItemCurrency(@Query("language") String language, @Query("currency") int currencyCodeId, @Query("item_nameid") long itemNameId);

    @FormUrlEncoded
    @POST("sellitem")
    Call<SellItemRs> sellItem(@FieldMap Map<String, String> fields);
}
