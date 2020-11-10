package com.projeto_padrao.api.servicos;

import com.projeto_padrao.models.Tarefa;
import com.projeto_padrao.models.Usuario;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface TarefaService {

    @GET("tarefas/")
    Call<List<Tarefa>> listarTarefas(@Header("Authorization") String key);

    @DELETE("tarefas/{id}/")
    Call<Tarefa> deletarTarefa(@Header("Authorization") String key, @Path("id") Long id);
}
