package com.qunews.psd.rsi.qunews.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qunews.psd.rsi.qunews.R;
import com.qunews.psd.rsi.qunews.dominio.Sessao;
import com.qunews.psd.rsi.qunews.dominio.Usuario;

public class MainActivity extends AppCompatActivity {

    private Usuario usuario = Sessao.getInstancia().getUsuarioLogado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Feed", "Username: "+usuario.getUsername());
        Log.i("Feed", "Token: "+usuario.getToken());


    }
}
