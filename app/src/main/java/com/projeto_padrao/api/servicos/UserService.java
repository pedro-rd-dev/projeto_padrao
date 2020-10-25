package com.projeto_padrao.api.servicos;

import com.projeto_padrao.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("account/register/")
    Call<Usuario> registrar(@Body Usuario usuario);


    @POST("account/login/")
    Call<Usuario> logar(@Body Usuario usuario);

    @GET("usuario/")
    Call<Usuario> requisitarObjetoUsuario(@Header("Authorization") String key);

    @DELETE("usuarios/{id}")
    Call<Usuario> deletarUsuario(@Header("Authorization") String key, @Path("id") Long id);

}
