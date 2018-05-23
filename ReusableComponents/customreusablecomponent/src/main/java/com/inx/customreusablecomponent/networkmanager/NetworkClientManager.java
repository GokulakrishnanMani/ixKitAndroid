package com.inx.customreusablecomponent.networkmanager;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ionixx on 18/5/18.
 */

public class NetworkClientManager {

    public static final String BASE_URL = "http://192.168.1.53:8005/api/";
    public static int ELAPSE_TIME=60;

    public static Retrofit getAPIClient() {
        Retrofit retrofit = null;
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("User-Agent", "Your-App-Name")
                            .header("Accept", "application/vnd.yourapi.v1.json")
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                }
            });

            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }


    public static Retrofit getAPIClientAfterLogin(Context context) {
        Retrofit retrofit = null;
//        final PreferenceManager pref = PreferenceManager.getInstance(context);
//        if (SyncStateContract.Constants.AUTH_TOCKEN == null) {
//            pref.putStringContent(Constants.AUTH_TOCKEN, "12345678");
//
//        }
        if (retrofit == null) {

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                           // .header("auth_token", pref.getStringContent(Constants.AUTH_TOCKEN))
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            }).readTimeout(ELAPSE_TIME, TimeUnit.MINUTES)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(ELAPSE_TIME, TimeUnit.MINUTES).build();


            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }


}
