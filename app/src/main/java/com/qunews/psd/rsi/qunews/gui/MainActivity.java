package com.qunews.psd.rsi.qunews.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.qunews.psd.rsi.qunews.ClientAPI.ConnectiontoAPI;
import com.qunews.psd.rsi.qunews.ClientAPI.QunewsAPI;
import com.qunews.psd.rsi.qunews.R;
import com.qunews.psd.rsi.qunews.dominio.Noticia;
import com.qunews.psd.rsi.qunews.dominio.Sessao;
import com.qunews.psd.rsi.qunews.dominio.Usuario;
import com.qunews.psd.rsi.qunews.util.NoticiaAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static Context contexto;
    private RecyclerView recyclerView;
    private NoticiaAdapter adapter;
    private List<Noticia> noticiaList;
    private Usuario usuario = Sessao.getInstancia().getUsuarioLogado();
    ConnectiontoAPI connectiontoAPI = new ConnectiontoAPI();
    QunewsAPI qunewAPI = connectiontoAPI.CreateRetrofit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contexto = this;

        Log.i("Feed", "Username: "+usuario.getUsername());
        Log.i("Feed", "Token: "+usuario.getToken());

        retornaNoticia();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        noticiaList = new ArrayList<>();
        adapter = new NoticiaAdapter(this, noticiaList);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.item_sair:
                Toast.makeText(MainActivity.this, "Saindo", Toast.LENGTH_SHORT).show();
                Intent intentGoLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentGoLogin);
                ((MainActivity)contexto).finish();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }


    public void retornaNoticia(){

        Call<List<Noticia>> call = qunewAPI.getNoticia(usuario.getToken(),"noticia-list");
        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Response<List<Noticia>> response, Retrofit retrofit) {
                List<Noticia> n = response.body();
                for(Noticia noti: n){
                    noticiaList.add(noti);
                }
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(contexto, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
