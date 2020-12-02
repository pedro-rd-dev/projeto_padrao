package com.projeto_padrao.api.servicos;

import com.projeto_padrao.models.chamados.Chamado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.projeto_padrao.statics.ConstantesGlobais.CHAMADOS;

public interface ChamadoService {



    @GET(CHAMADOS+"{id}/")
    Call<Chamado> chamadoPeloId(@Header("Authorization") String key, @Path("id") Long id);

    @PUT(CHAMADOS+"{id}/")
    Call<Chamado> editarChamado(@Header("Authorization") String key, @Path("id") Long id, @Body Chamado chamado);

    @DELETE(CHAMADOS+"{id}/")
    Call<Chamado> deletarChamado(@Header("Authorization") String key, @Path("id") Long id);


    @POST(CHAMADOS)
    Call<Chamado> criarChamado(@Header("Authorization") String key, @Body Chamado chamado);

    @GET(CHAMADOS)
    Call<List<Chamado>> listarChamados(@Header("Authorization") String key);



}
