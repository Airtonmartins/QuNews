package com.qunews.psd.rsi.qunews.dominio;

/**
 * Created by airton on 26/07/17.
 */

public class Pessoa {

    private Integer tiporef;

    public Pessoa(){}

    public Pessoa(Integer tiporef){
        this.tiporef = tiporef;
    }

    public Integer getTiporef(){
        return tiporef;
    }
    public void setTiporef(Integer tiporef){
        this.tiporef = tiporef;
    }
}
