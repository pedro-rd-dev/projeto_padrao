package com.projeto_padrao.models;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.projeto_padrao.LoginActivity;
import com.projeto_padrao.R;
import com.projeto_padrao.RegisterActivity;
import com.projeto_padrao.models.resposta.UsuarioErro;
import com.projeto_padrao.retrofit.RetrofitConfig;

import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Usuario extends SugarRecord {
    private String nome;
    private String email;
    private String password;
    private boolean logado;



    //É OBRIGATÓRIO A CRIAÇÃO DE UM CONSTRUTOR VAZIO


    public Usuario() {
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.password = senha;
    }

    public Usuario(String email, String senha,String nome) {
        this.email = email;
        this.password = senha;
        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLogado() {
        return logado;
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

    public void logar(Context context) {

        Android android = new Android(context);
        if (android.getConected(context)) {
            List<Usuario> usuarios = Usuario.find(Usuario.class, "email = ? and password = ?", this.email, this.password);
            if (usuarios.size() > 0) {
                this.logado = true;
            } else {
                this.logado = false;
                Toast.makeText(context, "Usuario e senha incorretos", Toast.LENGTH_LONG).show();
            }

        } else {
            this.logado = false;
            Log.d("credenciais", " \nLogin: " + this.email + "\nSenha: " + this.password);
            Toast.makeText(context, "Usuario não está logado", Toast.LENGTH_LONG).show();
        }

    }

    public void registrar(Context context) {

        Call<Usuario> call = new RetrofitConfig().setUserService().registrar(this);

        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful()) {
                    salvarUsuarioBanco();
                    Aplicacao aplicacao = new Aplicacao(context,LoginActivity.class);
                    aplicacao.trocarDeActivity();

                } else {
                    try {
                        new UsuarioErro(response, context);
                    } catch (Exception e) {
                        Log.d("retrofit", "erro no catch: " + Objects.requireNonNull(e.getMessage()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());
            }
        });


    }

    private void salvarUsuarioBanco() {
        this.save();
    }
}
