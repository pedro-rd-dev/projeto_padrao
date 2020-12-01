package com.projeto_padrao.models.remedio;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;


import androidx.annotation.NonNull;

import com.orm.SugarRecord;
import com.projeto_padrao.adapters.RecomendacaoAdpater;
import com.projeto_padrao.api.retrofit.RetrofitConfig;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;
import org.jetbrains.annotations.NotNull;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recomendacao extends SugarRecord {

    private Long remedio;
    private int intervalo;

    private Long usuario;
    private String ultima_hora_que_tomou;
    private int quantidade_restante;
    private String proximo_horario;


    //É OBRIGATÓRIO A CRIAÇÃO DE UM CONSTRUTOR VAZIO PARA SALVAR NO BANCO INTERNO
    public Recomendacao() {
    }

    public Long getRemedio() {
        return remedio;
    }

    public void setRemedio(Long remedio) {
        this.remedio = remedio;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }


    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public String getUltima_hora_que_tomou() {
        return ultima_hora_que_tomou;
    }

    public void setUltima_hora_que_tomou(String ultima_hora_que_tomou) {
        this.ultima_hora_que_tomou = ultima_hora_que_tomou;
    }

    public int getQuantidade_restante() {
        return quantidade_restante;
    }

    public void setQuantidade_restante(int quantidade_restante) {
        this.quantidade_restante = quantidade_restante;
    }

    public String getProximo_horario() {
        return proximo_horario;
    }

    public void setProximo_horario(String proximo_horario) {
        this.proximo_horario = proximo_horario;
    }

    public Recomendacao(Long remedio, int intervalo, Long usuario, String ultima_hora_que_tomou, int quantidade_restante, String proximo_horario) {
        this.remedio = remedio;
        this.intervalo = intervalo;

        this.usuario = usuario;
        this.ultima_hora_que_tomou = ultima_hora_que_tomou;
        this.quantidade_restante = quantidade_restante;
        this.proximo_horario = proximo_horario;
    }

    public static void listarRecomendacaoRemoto(@NotNull Usuario usuario, ListView recomendacao_lista_listview, Context context) {
        Call<List<Recomendacao>> call = new RetrofitConfig().setRecomendacaoService().listarRecomendacao("Token " + usuario.getKey());
        call.enqueue(new Callback<List<Recomendacao>>() {
            @Override
            public void onResponse(Call<List<Recomendacao>> call, Response<List<Recomendacao>> response) {
                if (response.isSuccessful()) {
                    List<Recomendacao> recomendacaos = response.body();

                    if (recomendacaos != null){
                        for(Recomendacao recomendacao1: recomendacaos){
                            recomendacao1.save();
                        }
                    }
                    if (recomendacaos == null){

                        Aplicacao.irParaNaoUsuarioActivity(context);

                    }

                    Log.d("listarRecomendacao", "listar");

                    RecomendacaoAdpater adaptador = new RecomendacaoAdpater( context, recomendacaos);
                    recomendacao_lista_listview.setAdapter(adaptador);


                }
            }

            @Override
            public void onFailure(Call<List<Recomendacao>> call, Throwable t) {
                Log.d("listarRecomendacao", "listar");

            }
        });

    }

    public void editarRecomendacao(@NotNull String key,Context context,ListView recomendacao_lista_listview) {
        Call<Recomendacao> call = new RetrofitConfig().setRecomendacaoService().editarRecomendacao("Token " + key, this.getId(), this);
        call.enqueue(new Callback<Recomendacao>() {

            @Override
            public void onResponse(@NonNull Call<Recomendacao> call, @NonNull Response<Recomendacao> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Recomendacao recomendacao = response.body();

                        recomendacao.save();
                        List<Recomendacao> recomendacaoList = Recomendacao.listAll(Recomendacao.class);

                        RecomendacaoAdpater adaptador = new RecomendacaoAdpater(context, recomendacaoList);
                        recomendacao_lista_listview.setAdapter(adaptador);
                    }
                }

            }


            @Override
            public void onFailure(Call<Recomendacao> call, Throwable t) {
                Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());
            }


        });
    }



    public List<Recomendacao> listarRecomendacaoDoBanco() {

        return Recomendacao.listAll(Recomendacao.class);

    }



}