package com.qunews.psd.rsi.qunews.dominio;

/**
 * Created by airton on 26/07/17.
 */

public class Usuario {

    private String username;
    private String password;
    private String email;
    private Pessoa pessoa;
    private Pessoamac pessoamac;
    private String token;


    public Usuario(){}

    public Usuario(String username, String password, String email,Pessoa pessoa, Pessoamac pessoamac){
        this.username = username;
        this.password = password;
        this.email = email;
        this.pessoa = pessoa;
        this.pessoamac = pessoamac;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public Pessoa getPessoa(){
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }

    public Pessoamac getPessoamac(){
        return pessoamac;
    }

    public void setPessoamac(Pessoamac pessoamac){
        this.pessoamac = pessoamac;
    }
}
