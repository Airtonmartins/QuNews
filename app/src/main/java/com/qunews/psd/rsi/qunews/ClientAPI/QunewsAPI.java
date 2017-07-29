package com.qunews.psd.rsi.qunews.ClientAPI;

import com.qunews.psd.rsi.qunews.dominio.Tipo;
import com.qunews.psd.rsi.qunews.dominio.Usuario;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by airton on 26/07/17.
 */

public interface QunewsAPI {

    @GET("{ctrlCar}")
    Call<List<Tipo>> getTipo(@Path("ctrlCar") String ctrl);

    @POST("api-register/")
    Call<Usuario> saveUsuario(@Body Usuario usuario);

    @FormUrlEncoded
    @POST("login/")
    Call<Usuario> login(@Field("username") String username,
                                            @Field("password") String password,
                                            @Field("mac") String mac);

}
