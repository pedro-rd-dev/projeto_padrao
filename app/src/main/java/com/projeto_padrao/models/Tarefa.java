package com.projeto_padrao.models;

import android.content.Context;
import android.util.Log;

import com.orm.SugarRecord;
import com.projeto_padrao.adapters.UsuariosAdapter;
import com.projeto_padrao.api.retrofit.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tarefa extends SugarRecord {
    private boolean realizada;
    private String descricao;
    private Context context;

    public Tarefa() {
    }

    public Tarefa(Context context) {
        this.context = context;
    }

    public void receberListaDeTarefas(String key){
        Call<List<Tarefa>> call = new RetrofitConfig(this.getContext()).setTarefaService().listarTarefas("Token "+ key);
        call.enqueue(new Callback<List<Tarefa>>() {
            @Override
            public void onResponse(Call<List<Tarefa>> call, Response<List<Tarefa>> response) {
                if(response.isSuccessful()){
                    List<Tarefa> tarefas = response.body();
                    Log.d("listarUsuarios","listar");
                }
            }

            @Override
            public void onFailure(Call<List<Tarefa>> call, Throwable t) {
                Log.d("listarUsuarios","listar");

            }
        });
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
