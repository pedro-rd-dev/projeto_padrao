package com.projeto_padrao.retrofit;

import com.projeto_padrao.models.Evento;
import com.projeto_padrao.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface EventoService {

    @POST("eventos/")
    Call<Evento> salvarEvento(@Body Evento evento,@Header("Authorization") String key);

}
