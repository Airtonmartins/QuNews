package com.qunews.psd.rsi.qunews.util;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.qunews.psd.rsi.qunews.dominio.Usuario;

import java.lang.reflect.Type;

/**
 * Created by airton on 26/07/17.
 */

public class UsuarioDes implements JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement usuario = json.getAsJsonObject();

        if (json.getAsJsonObject().get("usuario") != null){
            usuario = json.getAsJsonObject().get("usuario");
        }

        return ( new Gson().fromJson( usuario, Usuario.class ));
    }

}
