package com.qunews.psd.rsi.qunews.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qunews.psd.rsi.qunews.R;

import com.qunews.psd.rsi.qunews.dominio.Noticia;

import java.util.List;

/**
 * Created by airton on 02/08/17.
 */

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.MyViewHolder> {

    private Context mContext;
    private List<Noticia> noticiaList;
    String url = "http://10.0.3.2:8000/qunew/Images/";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, conteudo;
        public ImageView imagem, overflow;

        public MyViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.txtTitulo);
            conteudo = (TextView) view.findViewById(R.id.txtConteudo);
            imagem = (ImageView) view.findViewById(R.id.imgNoticia);

        }
    }


    public NoticiaAdapter(Context mContext, List<Noticia> noticiaList) {
        this.mContext = mContext;
        this.noticiaList = noticiaList;
    }

    @Override
    public NoticiaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noticia_card, parent, false);

        return new NoticiaAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NoticiaAdapter.MyViewHolder holder, int position) {
        Noticia noticia = noticiaList.get(position);
        holder.titulo.setText(noticia.getTitulo());
        holder.conteudo.setText(noticia.getConteudo());

        String str = noticia.getImage();
        String array[]= str.split("/");
        String img = array[array.length - 1];
        Log.i("AQUI", "imagem: "+img);

        // loading album cover using Glide library
        Glide.with(mContext).load(url+img).into(holder.imagem);

        holder.imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return noticiaList.size();
    }
}
