package ru.dmm.steamservice.http;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import ru.dmm.steamservice.service.Context;

import java.net.*;


/**
 * Created by Dmitry
 */
public class ServiceGenerator {
//    private static CookieJar mCookieJar;

    public static <S> S createService(Class<S> serviceClass, String apiBaseUrl, String login) {
        /*if (mCookieJar == null) {
            mCookieJar = new BasicCookieJar();
        }*/


        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        chain -> {
                            Request original = chain.request();

                            Request.Builder requestBuilder = original.newBuilder()
                                    .header("Accept", "*/*")
                                    .method(original.method(), original.body());

                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        })
                // .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
              .addNetworkInterceptor(new LoggingInterceptor())
                //.cookieJar(new CookieStore())
                .cookieJar(new JavaNetCookieJar(new CookieManager(new PersistentCookieStore(login), CookiePolicy.ACCEPT_ORIGINAL_SERVER)))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(serviceClass);
    }
}
