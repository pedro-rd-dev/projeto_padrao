package com.projeto_padrao.api.servicos;

import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.remedio.Recomendacao;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface RecomendacaoService {

    @GET("recomendacoes/")
    Call<List<Recomendacao>> listarRecomendacao(@Header("Authorization") String key);

    @PUT("recomendacoes/{id}/")
    Call<Recomendacao> editarRecomendacao(@Header("Authorization") String key, @Path("id") Long id, @Body Recomendacao recomendacao);




}
