package com.acv.marvel.data.repository;

import com.acv.marvel.data.MarvelClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestNetworkModule {


    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    private String getEndPoint() {
        return "/";
    }


    public MarvelClient getApiClient(MockWebServer mockWebServer) {
        return getRetrofitTest(mockWebServer).create(MarvelClient.class);
    }

    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(1, TimeUnit.SECONDS);
        builder.connectTimeout(1, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(false);
        return builder.build();
    }

    private Retrofit getRetrofitTest(MockWebServer mockWebServer) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .baseUrl(mockWebServer.url(getEndPoint()))
                .client(getOkHttpClient())
                .build();
        return retrofit;
    }

}
