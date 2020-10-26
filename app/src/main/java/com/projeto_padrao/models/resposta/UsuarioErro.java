package com.projeto_padrao.models.resposta;

import android.content.Context;

import com.google.gson.Gson;
import com.projeto_padrao.activities.LoginActivity;
import com.projeto_padrao.activities.RegisterActivity;
import com.projeto_padrao.activities.usuario.UsuarioDetalheActivity;
import com.projeto_padrao.models.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class UsuarioErro {
    private List<String> email;
    private List<String> password;
    private retrofit2.Response<Usuario> response;
    private Context context;

    public UsuarioErro(Response<Usuario> response, Context context) {
        this.response = response;
        this.context = context;
    }

    public void mostrarErroRegistro(){
        try {
            JSONObject jsonObject = new JSONObject(this.response.errorBody().string());
            String jsonString = jsonObject.toString();
            Gson gson = new Gson();
            UsuarioErro usuarioErro = gson.fromJson(jsonString, UsuarioErro.class);

            for (String erro : usuarioErro.getEmail()){
                ((RegisterActivity)context).mostrarAvisoEmail(erro);

            }
            for (String erro : usuarioErro.getPassword()){
                ((RegisterActivity)context).mostrarAvisoSenha(erro);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mostrarErroEditar(){
        try {
            JSONObject jsonObject = new JSONObject(this.response.errorBody().string());
            String jsonString = jsonObject.toString();
            Gson gson = new Gson();
            UsuarioErro usuarioErro = gson.fromJson(jsonString, UsuarioErro.class);

            if(usuarioErro.getEmail() !=null){
                for (String erro : usuarioErro.getEmail()){
                    ((UsuarioDetalheActivity)context).mostrarAvisoEmail(erro);

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mostrarErroLogin(){
        try {
            JSONObject jsonObject = new JSONObject(this.response.errorBody().string());
            String jsonString = jsonObject.toString();
            Gson gson = new Gson();
            UsuarioErro usuarioErro = gson.fromJson(jsonString, UsuarioErro.class);

            if(usuarioErro.getEmail() !=null){
                for (String erro : usuarioErro.getEmail()){
                    ((LoginActivity)context).mostrarAvisoEmail(erro);

                }
            }
            if(usuarioErro.getPassword()!= null){
                for (String erro : usuarioErro.getPassword()){
                    ((LoginActivity)context).mostrarAvisoSenha(erro);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }
}
