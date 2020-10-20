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
    private Long pk;

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


    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public void salvaUsuarioNoBanco(){
        this.save();
    }

    public List<Usuario> listarUsuariosDoBanco(){
        List<Usuario> usuarios = Usuario.listAll(Usuario.class);

        return usuarios;

    }

    public Usuario buscarUsuarioPeloId(){

        Usuario usuario = Usuario.findById(Usuario.class, this.getId());

        return usuario;

    }

    public void editarUsuarioBanco(){
        Usuario usuario = this.buscarUsuarioPeloId();
        //INSERIR OS SETS DESEJADOS
        usuario.save();
    }

    public void redefinirSenhaUsuarioBanco(){
        Usuario usuario = this.buscarUsuarioPeloId();
        usuario.setPassword("NovaSenha1123");
        usuario.save();
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
                        Usuario usuario = new Usuario();
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
        Call<Usuario> call = new RetrofitConfig().setUserService().verificarUsuarioLogado("Token " +usuario.getKey());
        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Usuario usurioRecebido = response.body();
                        usurioRecebido.setKey(usuario.getKey());
                        usurioRecebido.setLogado(true);
                        usurioRecebido.setId(usurioRecebido.getPk());
                        usurioRecebido.save();
                        irParaAppnActivity();
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
        Aplicacao aplicacao = new Aplicacao(this.context,LoginActivity.class);
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

    public static Usuario verificaUsuarioLogado(){
        List<Usuario> usuarios = Usuario.listAll(Usuario.class);
        for (Usuario usuario : usuarios){
            if (usuario.getLogado()){
                return usuario;
            }
        }
        return null;
    }


    public void atualizarUsuarioBanco(){
        Usuario usuario = Usuario.findById(Usuario.class,this.getId());
        if(this.getKey()!=null && !this.getKey().isEmpty()){
            usuario.setKey(this.getKey());
        }
        if(this.getEmail()!=null && !this.getEmail().isEmpty()){
            usuario.setEmail(this.getEmail());
        }

        usuario.save();
    }

}
