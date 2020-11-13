package com.projeto_padrao.models.eventos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.api.retrofit.RetrofitConfig;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Evento extends SugarRecord {
    private String data;
    private String horario;
    private String local;
    private double preco;
    private String nomeEvento;
    @Ignore
    private Context context;


    public Evento(){

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Date getData() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = new java.sql.Date(Objects.requireNonNull(format.parse(this.data)).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void setData(Date data) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.data = sdf.format(data);
    }

    public Date getHorario() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date data = null;
        try {
            data = new java.sql.Date(Objects.requireNonNull(format.parse(this.horario)).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void setHorario(Date Datahorario) {
        String horario = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Datahorario);
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public void enviarEvento(Usuario usuario){
        Call<Evento> call = new RetrofitConfig().setEventoService().salvarEvento(this,usuario.getKey());
        call.enqueue(new Callback<Evento>() {

            @Override
            public void onResponse(@NonNull Call<Evento> call, @NonNull Response<Evento> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Evento evento = new Evento();
                        Log.e("retrofit", "Evento enviado");

                    }
                } else {
                    //lancarErroDeUsuario(response);
                    try {
                        Log.v("Error code 400",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Evento> call, @NonNull Throwable t) {
                Log.e("retrofit", "Erro ao enviar o Evento:" + t.getMessage());
            }
        });
    }
}
