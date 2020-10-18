package com.projeto_padrao.retrofit;

import com.projeto_padrao.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("account/register")
    Call<Usuario> registrar(@Body Usuario usuario);


}
