package com.qunews.psd.rsi.qunews.ClientAPI;

import com.google.gson.Gson;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by airton on 26/07/17.
 */

public class ConnectiontoAPI {

    public static final String API = "http://192.168.0.104:8000/";

    public QunewsAPI CreateRetrofit(){

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        QunewsAPI qunewsAPI = retrofit.create(QunewsAPI.class);

        return qunewsAPI;

    }
}
