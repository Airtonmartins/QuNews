package com.qunews.psd.rsi.qunews.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qunews.psd.rsi.qunews.ClientAPI.ConnectiontoAPI;
import com.qunews.psd.rsi.qunews.ClientAPI.QunewsAPI;
import com.qunews.psd.rsi.qunews.R;
import com.qunews.psd.rsi.qunews.dominio.Sessao;
import com.qunews.psd.rsi.qunews.dominio.Usuario;
import com.qunews.psd.rsi.qunews.util.Util;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by airton on 28/07/17.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText editTextLogin;
    private EditText editTextSenha;
    private Button btnLogar;
    private Button btnCadastrar;
    private static Context contexto;
    ConnectiontoAPI connectiontoAPI = new ConnectiontoAPI();
    Util util = new Util();
    QunewsAPI qunewAPI = connectiontoAPI.CreateRetrofit();
    private Usuario usuario = Sessao.getInstancia().getUsuarioLogado();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        contexto = this;
        editTextLogin = (EditText) findViewById(R.id.edtLogin);
        editTextSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        if (usuario != null){
            Intent intentGoMain = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intentGoMain);
            finish();
        }


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editTextLogin.getText().toString().trim();
                String senha = editTextSenha.getText().toString().trim();
                String mac = util.pegarMac(contexto);
                if (validarCampos(login, senha)) {
                    Call<Usuario> call = qunewAPI.login(login, senha, mac);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Response<Usuario> response, Retrofit retrofit) {

                            Usuario usuario = response.body();

                            if (response.isSuccess()) {
                                Sessao sessao = Sessao.getInstancia();
                                sessao.setUsuarioLogado(usuario);
                                Intent intentGoMain = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intentGoMain);
                                Toast.makeText(LoginActivity.this, "Bem vindo "+usuario.getUsername(), Toast.LENGTH_SHORT).show();
                                finish();

                            } else {

                                editTextLogin.setError("");
                                editTextSenha.setError("");
                                Toast.makeText(LoginActivity.this, "Usuario ou senha invalida", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }});






        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoMain = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intentGoMain);
            }
        });
    }

    public boolean validarCampos(String login, String senha){
        String msg = "Campo vazio";
        boolean result = true;
        if(login.equals("")){
            editTextLogin.setError(msg);
            result = false;
        }
        if(senha.equals("")){
            editTextSenha.setError(msg);
            result = false;
        }

        return result;
    }

}
