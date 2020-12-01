package com.projeto_padrao.models;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;
import com.projeto_padrao.activities.Agendamento.MeusActivity;
import com.projeto_padrao.adapters.AgendAdapter;
import com.projeto_padrao.adapters.MeusAdapter;
import com.projeto_padrao.api.retrofit.RetrofitConfig;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.projeto_padrao.models.Agendamento.confirmarAgendNaoEditado;
import static com.projeto_padrao.statics.ConstantesGlobais.VAZIO;

public class MeusAgendamentos extends SugarRecord {


    private long usuario;
    private Context context;

    public MeusAgendamentos(Class<MeusAgendamentos> meusAgendamentosClass, long agendamento){

    }

    public MeusAgendamentos(Context context){
        this.context = context;
    }


    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public static void listarAgenduser(@NotNull Usuario usuario, ListView agend_lista_user) {
        Call<List<Agendamento>> call = new RetrofitConfig().setAgendService().listarAgendporUser("Token " + usuario.getKey());
        call.enqueue(new Callback<List<Agendamento>>() {
            @Override
            public void onResponse(Call<List<Agendamento>> call, Response<List<Agendamento>> response) {
                if (response.isSuccessful()) {
                    List<Agendamento> agendamentos = response.body();
                    Log.d("listarAgenduser", "listar");

                    MeusAdapter adaptador = new MeusAdapter(usuario.getContext(),agendamentos);
                    agend_lista_user.setAdapter(adaptador);
                }
            }

            @Override
            public void onFailure(Call<List<Agendamento>> call, Throwable t) {
                Log.d("listarAgend", "listar");

            }
        });
    }


    }



