package com.movie.keyalive.movieuiux.Api;

import android.app.Service;

import com.movie.keyalive.movieuiux.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private ApiService service;

    public static final String BASE_URL="https://api.themoviedb.org/3/";

    public ApiClient(){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl httpUrl = request.url().newBuilder()
                        .addQueryParameter("api_key", "86b811f6a2145953425451c7d74f9678")
                        .build();

                request = request.newBuilder().url(httpUrl).build();
                return chain.proceed(request);
            }
        }).build();


        Retrofit retrofit = new Retrofit.Builder().client(client)
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();


        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;
    }


}
