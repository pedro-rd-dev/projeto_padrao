package com.projeto_padrao.models.chamados;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.projeto_padrao.api.retrofit.RetrofitConfig;
import com.projeto_padrao.models.Usuario;
import com.orm.SugarRecord;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Impressora extends SugarRecord {
    private StatusImpressora statusImpressora;
    private String status_impressora;
    private Usuario usuario;


    public StatusImpressora getStatusImpressora() {
        return statusImpressora;
    }

    public void setStatusImpressora(StatusImpressora statusImpressora) {
        this.statusImpressora = statusImpressora;
    }
    public void setStatus_impressora(String status_impressora) {
        this.status_impressora = status_impressora;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void listarImpressoras(@NotNull String key, Context context, Spinner criar_chamado_activity_menuImpressora) {
        Call<List<Impressora>> call = new RetrofitConfig().setImpressoraService().listarImpressoras("Token " + key);
        call.enqueue(new Callback<List<Impressora>>() {
            @Override
            public void onResponse(Call<List<Impressora>> call, Response<List<Impressora>> response) {
                if (response.isSuccessful()) {
                    List<Impressora> impressoras = response.body();
                    ArrayList<String> items =new ArrayList<>();

                    if (impressoras != null) {
                        for (Impressora impressora : impressoras){
                            impressora.save();

                            items.add(impressora.getId().toString());

                        }
                    }



                    criar_chamado_activity_menuImpressora.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, items));


                    /*List<Impressora> impressoraList = Impressora.listAll(Impressora.class);*/
                    Log.d("listarImpressoras", "listar");

                }


            }

            @Override
            public void onFailure(Call<List<Impressora>> call, Throwable t) {
                Log.d("listarImpressoras", "listar");

            }
        });


    }
}
