package com.projeto_padrao.api.servicos;

import com.projeto_padrao.models.Tarefa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TarefaService {

    @GET("tarefas/")
    Call<List<Tarefa>> listarTarefas(@Header("Authorization") String key);
}
