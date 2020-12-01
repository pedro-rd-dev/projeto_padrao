package com.projeto_padrao.models.remedio;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.orm.SugarRecord;
import com.projeto_padrao.activities.remedio.RecomendacaoActivity;
import com.projeto_padrao.adapters.RecomendacaoAdpater;
import com.projeto_padrao.api.retrofit.RetrofitConfig;
import com.projeto_padrao.models.Usuario;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Remedio extends SugarRecord {

    public String nome;
    private double mg;

    //É OBRIGATÓRIO A CRIAÇÃO DE UM CONSTRUTOR VAZIO PARA SALVAR NO BANCO INTERNO
    public Remedio() {

    }

    public void Remedio(String nome, double mg) {
        this.nome = nome;
        this.mg = mg;
    }

    public static void listarRemedioRemoto(@NotNull Usuario usuario, ListView recomendacao_lista_listview, Context context) {
        Call<List<Remedio>> call = new RetrofitConfig().setRemedioService().listarRemedio("Token " + usuario.getKey());
        call.enqueue(new Callback<List<Remedio>>() {
            @Override
            public void onResponse(Call<List<Remedio>> call, Response<List<Remedio>> response) {
                if (response.isSuccessful()) {
                    List<Remedio> remedios = response.body();

                    if (remedios != null){
                        for(Remedio remedio1: remedios){
                            remedio1.save();
                        }

                        Usuario usuario = Usuario.verificaUsuarioLogado();
                        if (usuario != null) {

                            Recomendacao.listarRecomendacaoRemoto(usuario, recomendacao_lista_listview, context);

                        }

                    }


                    Log.d("ListarRemedio", "listar");

                }
            }

            @Override
            public void onFailure(Call<List<Remedio>> call, Throwable t) {
                Log.d("ListarRemedio", "listar");
            }

        });

    }
    public List<Remedio> listarRemedioDoBanco() {

        return Remedio.listAll(Remedio.class);

    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMg() {
        return mg;
    }

    public void setMg(double mg) {
        this.mg = mg;
    }
}



