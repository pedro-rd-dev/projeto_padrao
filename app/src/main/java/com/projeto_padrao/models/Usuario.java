package com.projeto_padrao.models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.projeto_padrao.activities.AppActivity;
import com.projeto_padrao.activities.LoginActivity;
import com.projeto_padrao.models.resposta.UsuarioErro;
import com.projeto_padrao.api.retrofit.RetrofitConfig;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Usuario extends SugarRecord {

    private String first_name;
    private String email;
    private String password;
    private boolean logado;
    @Ignore
    private transient Context context;
    private String key;
    private Long pk;

    //É OBRIGATÓRIO A CRIAÇÃO DE UM CONSTRUTOR VAZIO
    public Usuario() {
    }

    public Usuario(String email, String senha, Context context) {
        this.email = email;
        this.password = senha;
        this.context = context;

    }

    public Usuario(String email, String senha, String first_name, Context context) {
        this.email = email;
        this.password = senha;
        this.first_name = first_name;
        this.context = context;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public void salvaUsuarioNoBanco() {
        this.save();
    }

    public List<Usuario> listarUsuariosDoBanco() {
        List<Usuario> usuarios = Usuario.listAll(Usuario.class);

        return usuarios;

    }

    public Usuario buscarUsuarioPeloId() {

        Usuario usuario = Usuario.findById(Usuario.class, this.getId());

        return usuario;

    }

    public void editarUsuarioBanco() {
        Usuario usuario = this.buscarUsuarioPeloId();
        //INSERIR OS SETS DESEJADOS
        usuario.save();
    }

    public void redefinirSenhaUsuarioBanco() {
        Usuario usuario = this.buscarUsuarioPeloId();
        usuario.setPassword("NovaSenha1123");
        usuario.save();
    }

    public void logarNoBanco(Context context) {

        List<Usuario> usuarios = Usuario.find(Usuario.class, "email = ? and password = ?", this.email, this.password);
        if (usuarios.size() > 0) {
            this.logado = true;
        } else {
            this.logado = false;
            Toast.makeText(context, "Usuario e senha incorretos", Toast.LENGTH_LONG).show();
        }

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getKey() {
        if (key!=null){
            key = key.replace("Token ","");
        }
        return key;
    }

    public void setKey(String key) {
        this.key="";
        this.key = "Token " + key;
    }

    public String getNome() {
        return first_name;
    }

    public void setNome(String nome) {
        this.first_name = nome;
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

    public void logar() {
            Call<Usuario> call = new RetrofitConfig(this.context).setUserService().logar(this);
            call.enqueue(new Callback<Usuario>() {

                @Override
                public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            Usuario usuario = response.body();
                            usuario.setKey(response.body().getKey());
                            requisitarObjetoUsuario(usuario);
                        }
                    } else {
                        //lancarErroDeUsuario(response);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                    Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());
                }
            });
    }

    private void requisitarObjetoUsuario(Usuario usuario) {
        Call<Usuario> call = new RetrofitConfig(this.context).setUserService().requisitarObjetoUsuario(usuario.getKey());
        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Usuario usuarioCompleto = response.body();
                        usuarioCompleto.setLogado(true);
                        usuarioCompleto.setKey(usuario.getKey());
                        usuarioCompleto.setId(usuarioCompleto.getPk());
                        usuarioCompleto.save();
                        irParaAppnActivity();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());
            }
        });

    }

    public void registrar() {

        Call<Usuario> call = new RetrofitConfig(this.context).setUserService().registrar(this);

        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        irParaLoginActivity();
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


    private void irParaLoginActivity() {
        Aplicacao aplicacao = new Aplicacao(this.context, LoginActivity.class);
        aplicacao.trocarDeActivity();
    }

    private void irParaAppnActivity() {
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

    public static Usuario verificaUsuarioLogado() {
        List<Usuario> usuarios = Usuario.listAll(Usuario.class);
        for (Usuario usuario : usuarios) {
            if (usuario.getLogado()) {
                return usuario;
            }
        }
        return null;
    }

}
