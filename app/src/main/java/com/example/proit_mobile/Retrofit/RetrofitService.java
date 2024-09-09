package com.example.proit_mobile.Retrofit;

import android.content.SharedPreferences;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Interceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService(SharedPreferences sharedPreferences) {
        String token = sharedPreferences.getString("token", "");
        Log.d("RetrofitService", "Retrieved Token: " + token); // Log token for debugging

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();

                    if (!token.isEmpty()) {
                        requestBuilder.header("Authorization", "Bearer " + token); // Add "Bearer " prefix
                    }

                    Request request = requestBuilder
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8082")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
