package com.projeto_padrao.models;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.projeto_padrao.RegisterActivity;

public class Usuario {
    private String username;
    private String password;
    private boolean logado;

    public Usuario(String usuario, String senha) {
        this.username = usuario;
        this.password = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public void logar(Context context){

        Android android = new Android(context);
        if (android.getConected(context)){

            this.logado = true;
            Log.d("credenciais", " \nLogin: " + this.username+"\nSenha: " + this.password);
            Toast.makeText(context,"Usuario está logado",Toast.LENGTH_LONG).show();

        }else {

            this.logado = false;
            Log.d("credenciais", " \nLogin: " + this.username+"\nSenha: " + this.password);
            Toast.makeText(context,"Usuario não está logado",Toast.LENGTH_LONG).show();
        }

    }

    public void registrar(Context context) {
    }
}
