package com.projeto_padrao.models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;
import com.projeto_padrao.activities.AppActivity;
import com.projeto_padrao.activities.LoginActivity;
import com.projeto_padrao.models.resposta.UsuarioErro;
import com.projeto_padrao.retrofit.RetrofitConfig;

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
    @Ignore
    private transient Context context;
    private String key;

    //É OBRIGATÓRIO A CRIAÇÃO DE UM CONSTRUTOR VAZIO
    public Usuario() {
    }

    public Usuario(String email, String senha, Context context) {
        this.email = email;
        this.password = senha;
        this.context = context;

    }

    public Usuario(String email, String senha,String nome,Context context) {
        this.email = email;
        this.password = senha;
        this.nome = nome;
        this.context = context;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public void logar(){
        Call<Usuario> call = new RetrofitConfig().setUserService().logar(this);
        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        salvarUsuarioBanco(response.body());
                    }
                    irParaAplicacaonActivity();
                } else {
                    lancarErroDeUsuario(response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());
            }
        });

    }

    public void logarNoBanco(Context context) {

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

    public void registrar() {

        Call<Usuario> call = new RetrofitConfig().setUserService().registrar(this);

        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        irParaLoginActivity();
                        Usuario usuario = response.body();
                        usuario.save();
                    }
                } else {
                    lancarErroDeUsuario(response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());
            }
        });

    }

    private void salvarUsuarioBanco(Usuario usuario) {
        this.setLogado(true);
        this.setKey(usuario.getKey());
        this.save();
    }

    private void irParaLoginActivity() {
        Aplicacao aplicacao = new Aplicacao(this.context,LoginActivity.class);
        aplicacao.trocarDeActivity();
    }
    private void irParaAplicacaonActivity() {
        Aplicacao aplicacao = new Aplicacao(this.context, AppActivity.class);
        aplicacao.trocarDeActivity();
    }

    private void lancarErroDeUsuario(Response<Usuario> response) {
        try {
            new UsuarioErro(response, this.context);
        } catch (Exception e) {
            Log.d("retrofit", "erro no catch: " + Objects.requireNonNull(e.getMessage()));
        }
    }

    public static Usuario verificaUsuarioLogado(){
        List<Usuario> usuarios = Usuario.listAll(Usuario.class);
        for (Usuario usuario : usuarios){
            if (usuario.getLogado()){
                return usuario;
            }
        }
        return null;
    }

}
