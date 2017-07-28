package com.qunews.psd.rsi.qunews.dominio;

/**
 * Created by airton on 28/07/17.
 */

public class Tipo {

    private Integer id;
    private String nome;

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
