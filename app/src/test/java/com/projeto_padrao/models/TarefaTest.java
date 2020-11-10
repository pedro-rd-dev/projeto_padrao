package com.projeto_padrao.models;

import android.util.Log;

import com.orm.SugarContext;
import com.projeto_padrao.activities.StartActivity;
import com.projeto_padrao.activities.autenticacao.LoginActivity;
import com.projeto_padrao.adapters.TarefasAdapter;
import com.projeto_padrao.api.retrofit.RetrofitConfig;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.Context;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest implements BeforeTestExecutionCallback {

    private Usuario usuarioLogado;
    private static final String START_TIME = "start time";
    private Context context;

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        this.context = (Context) context;
        getStore(context).put(START_TIME, System.currentTimeMillis());
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }

    @Test
    void inicializarUsuarioLogado(){
        SugarContext.init((android.content.Context) context);

        this.usuarioLogado= Usuario.verificaUsuarioLogado();

    }

    @Test
    void receberListaDeTarefas() {
        Call<List<Tarefa>> call = new RetrofitConfig(usuarioLogado.getContext()).setTarefaService().listarTarefas("Token "+ usuarioLogado.getKey());
        call.enqueue(new Callback<List<Tarefa>>() {
            @Override
            public void onResponse(@NotNull Call<List<Tarefa>> call, @NotNull Response<List<Tarefa>> response) {
                if(response.isSuccessful()){
                    List<Tarefa> tarefas = response.body();
                    Log.d("listarUsuarios","listar");

                    assertTrue(true);



                }else{
                    fail();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Tarefa>> call, Throwable t) {
                Log.d("listarUsuarios","listar");
                fail();

            }
        });
    }

    @Test
    void deletarTarefa() {

    }
}