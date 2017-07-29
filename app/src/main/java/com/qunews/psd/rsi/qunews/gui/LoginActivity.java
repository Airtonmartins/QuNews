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

                Call<Usuario> call = qunewAPI.login(login,senha,"8888888");
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Response<Usuario> response, Retrofit retrofit) {

                        Usuario usuario = response.body();

                        if( usuario != null ){
                            Log.i("TAG", "Username: "+usuario.getUsername());
                            Log.i("TAG", "Username: "+usuario.getToken());
                            Sessao sessao = Sessao.getInstancia();
                            sessao.setUsuarioLogado(usuario);
                            Intent intentGoMain = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intentGoMain);
                            finish();

                        }
                        else{
                            Gson gson = new GsonBuilder().create();
                            Usuario suer = new Usuario();

                            String json = null;
                            try {
                                json = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            Log.i("TAG", "R: Error Login: "+json);

                        }

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.i("LOG", "Error Connection: " + t.getMessage());
                    }
                });

            }
        });






        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoMain = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intentGoMain);
            }
        });
    }
}
