package com.projeto_padrao.api.servicos;

import com.projeto_padrao.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @POST("account/register/")
    Call<Usuario> registrar(@Body Usuario usuario);


    @POST("account/login/")
    Call<Usuario> logar(@Body Usuario usuario);

    @GET("account/user/")
    Call<Usuario> requisitarObjetoUsuario(@Header("Authorization") String key);
}
