package com.projeto_padrao.api.servicos;

import com.projeto_padrao.models.chamados.Chamado;
import com.projeto_padrao.models.chamados.Impressora;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ImpressoraService {



    @GET("impressoras/{id}/")
    Call<Chamado> chamadoPeloId(@Header("Authorization") String key, @Path("id") Long id);

    @PUT("impressoras/{id}/")
    Call<Chamado> editarImpressoras(@Header("Authorization") String key, @Path("id") Long id, @Body Impressora impressora);


    @GET("impressoras/")
    Call<List<Impressora>> listarImpressoras(@Header("Authorization") String key);

}
