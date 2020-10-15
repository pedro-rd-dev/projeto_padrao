package com.projeto_padrao.models;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.orm.SugarRecord;
import com.projeto_padrao.R;
import com.projeto_padrao.RegisterActivity;

import java.util.List;

public class Usuario extends SugarRecord {
    private String username;
    private String password;
    private boolean logado;

    //É OBRIGATÓRIO A CRIAÇÃO DE UM CONSTRUTOR VAZIO
    public Usuario(){

    }

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
            List<Usuario> usuarios = Usuario.find(Usuario.class, "username = ? and password = ?", this.username, this.password);
            if(usuarios.size()>0){
                this.logado = true;
            }else {
                this.logado = false;
                Toast.makeText(context,"Usuario e senha incorretos",Toast.LENGTH_LONG).show();
            }

        }else {
            this.logado = false;
            Log.d("credenciais", " \nLogin: " + this.username+"\nSenha: " + this.password);
            Toast.makeText(context,"Usuario não está logado",Toast.LENGTH_LONG).show();
        }

    }

    public void registrar(Context context) {
        List<Usuario> usuarios = Usuario.find(Usuario.class, "username = ? and password = ?", this.username, this.password);
        if(usuarios.size()==0){
            this.save();
        }else {
            Toast.makeText(context,"Este usuário já existe",Toast.LENGTH_SHORT).show();
        }
    }
}
