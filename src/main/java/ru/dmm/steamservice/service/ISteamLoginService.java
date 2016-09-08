package ru.dmm.steamservice.service;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.dmm.steamservice.data.rs.DoLoginRs;
import ru.dmm.steamservice.data.rs.RsaKeyRs;

import java.util.Map;

/**
 * Created by Dmitry
 */
public interface ISteamLoginService {
    @FormUrlEncoded
    @POST("getrsakey")
    Call<RsaKeyRs> getRsaKey(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("dologin")
    Call<DoLoginRs> doLogin(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("transfer")
    Call<Void> transfer(@FieldMap Map<String, String> fields);
}
