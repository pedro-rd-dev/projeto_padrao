package com.projeto_padrao.api.servicos;


import com.projeto_padrao.models.remedio.Remedio;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RemedioService {

    @GET("remedios/")
    Call<List<Remedio>> listarRemedio(@Header("authorization")String key);


}
