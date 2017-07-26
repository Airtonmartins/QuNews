package com.qunews.psd.rsi.qunews.gui;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qunews.psd.rsi.qunews.ClientAPI.ConnectiontoAPI;
import com.qunews.psd.rsi.qunews.ClientAPI.QunewsAPI;
import com.qunews.psd.rsi.qunews.R;
import com.qunews.psd.rsi.qunews.dominio.Pessoa;
import com.qunews.psd.rsi.qunews.dominio.Pessoamac;
import com.qunews.psd.rsi.qunews.dominio.Usuario;
import com.qunews.psd.rsi.qunews.util.UsuarioDes;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by airton on 26/07/17.
 */

public class CadastroActivity extends AppCompatActivity {

    private static Context contexto;
    private EditText editPessoaNome;
    private EditText editUsuarioLogin;
    private EditText editUsuarioSenha;
    private EditText editUsuarioSenhaConfirmar;
    private EditText editEmail;
    private Button btnCadastrar;
    private String[] spinnerTipo = new String[]{"Aluno","Funcionario","Professor"};
    private Spinner sp;
    private Integer tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        contexto = this;



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, spinnerTipo);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        sp = (Spinner) findViewById(R.id.planets_spinner);
        editPessoaNome = (EditText) findViewById(R.id.edtNomePessoa);
        editUsuarioLogin= (EditText) findViewById(R.id.edtLoginCadastro);
        editUsuarioSenha = (EditText) findViewById(R.id.edtSenhaCadastro);
        editUsuarioSenhaConfirmar = (EditText) findViewById(R.id.edtConfirmarSenhaCadastro);
        editEmail = (EditText) findViewById(R.id.edtEmail);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrarCadastro);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.i("LOG",String.valueOf(position));
                Log.i("LOG", (String) sp.getSelectedItem());
                tipo = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = editPessoaNome.getText().toString().trim();
                String login = editUsuarioLogin.getText().toString().trim();
                String senha = editUsuarioSenha.getText().toString().trim();
                String senhaConfirmar = editUsuarioSenhaConfirmar.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                WifiManager manager = (WifiManager) contexto.getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = manager.getConnectionInfo();
                String mac = info.getMacAddress();
                Log.i("MAC", mac);

                if(senha.equals(senhaConfirmar)){

                    Pessoa pessoa = new Pessoa(tipo);
                    Pessoamac pessoamac = new Pessoamac(mac);
                    Usuario usuario =new Usuario(login,senha,email,pessoa,pessoamac);

                    Gson gson = new GsonBuilder().registerTypeAdapter(Usuario.class, new UsuarioDes()).create();
                    ConnectiontoAPI connectiontoAPI = new ConnectiontoAPI();
                    QunewsAPI qunewAPI = connectiontoAPI.CreateRetrofit(gson);

                    Call<Usuario> call = qunewAPI.saveUsuario(usuario);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Response<Usuario> response, Retrofit retrofit) {

                            Usuario n = response.body();

                            if( n != null ){

                                Log.i("TAG", "Username: "+n.getUsername());

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
                                json = json.replace("[","").replace("]","");
                                suer = gson.fromJson(json, Usuario.class);

                                Log.i("TAG", "R: Error Usuario: "+suer.getUsername());


                            }


                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.i("LOG", "Error Usuario: " + t.getMessage());
                        }
                    });

                }

            }
        });


    }



}
