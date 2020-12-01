package com.projeto_padrao.api.servicos;

import com.projeto_padrao.models.Agendamento;
import com.projeto_padrao.models.MeusAgendamentos;
import com.projeto_padrao.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.projeto_padrao.statics.ConstantesGlobais.AGENDAMENTOS;
import static com.projeto_padrao.statics.ConstantesGlobais.MEUS_AGENDAMENTOS;

public interface AgendService {


    @PUT(AGENDAMENTOS +"{id}/")
    Call<Agendamento> addUserAgend(@Header("Authorization") String key, @Path("id") Long id, @Body Agendamento agendamento);

    @DELETE(AGENDAMENTOS +"{id}/")
    Call<Agendamento> deletarAgend(@Header("Authorization") String key, @Path("id") Long id);

    @GET(AGENDAMENTOS)
    Call<List<Agendamento>> listarAgendRemoto(@Header("Authorization") String s);

    @GET(MEUS_AGENDAMENTOS)
    Call<List<Agendamento>>listarAgendporUser(@Header("Authorization")String u);

    @PUT(MEUS_AGENDAMENTOS +"{id}/")
    Call<Agendamento>removerUserAgend(@Header("Authorization")String key,@Path("id")Long id,@Body Agendamento agendamento);




}
