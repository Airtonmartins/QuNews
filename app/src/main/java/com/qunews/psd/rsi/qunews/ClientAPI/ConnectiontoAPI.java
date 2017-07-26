package com.qunews.psd.rsi.qunews.ClientAPI;

import com.google.gson.Gson;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by airton on 26/07/17.
 */

public class ConnectiontoAPI {

    public static final String API = "http://10.0.3.2:8000/";

    public QunewsAPI CreateRetrofit(Gson gson){

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        QunewsAPI qunewsAPI = retrofit.create(QunewsAPI.class);

        return qunewsAPI;

    }
}
