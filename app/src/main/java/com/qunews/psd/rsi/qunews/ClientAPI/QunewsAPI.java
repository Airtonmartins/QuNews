package com.qunews.psd.rsi.qunews.ClientAPI;

import com.qunews.psd.rsi.qunews.dominio.Usuario;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by airton on 26/07/17.
 */

public interface QunewsAPI {

    @POST("api-register/")
    Call<Usuario> saveUsuario(@Body Usuario usuario);

}
