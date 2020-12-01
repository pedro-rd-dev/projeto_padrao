package com.projeto_padrao.models;


import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.orm.SugarRecord;
import com.projeto_padrao.activities.Agendamento.AgendamentoActivity;
import com.projeto_padrao.activities.Agendamento.MeusActivity;
import com.projeto_padrao.activities.autenticacao.RegisterActivity;
import com.projeto_padrao.activities.usuario.UsuarioDetalheActivity;
import com.projeto_padrao.adapters.AgendAdapter;
import com.projeto_padrao.adapters.MeusAdapter;
import com.projeto_padrao.api.retrofit.RetrofitConfig;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.System.in;
import static java.lang.System.out;

public class Agendamento extends SugarRecord {

    private transient Context context;
    private String nome_agendamento;
    private String data;
    private String horainicio;
    private String horafinal;
    private Long usuario;



//public Agendamento(List<MeusAgendamentos> meusAgendamentos){}


    public Agendamento() {

    }

    public Agendamento(Context context, Long usuario){
        this.context = context;
        this.usuario = usuario;
    }



    public Agendamento(Context context, String nome, String data, String horainicio, String horafinal,Long usuario) {
        this.context = context;
        this.nome_agendamento = nome;
        this.data = data;
        this.horainicio = horainicio;
        this.horafinal = horafinal;
        this.usuario = usuario;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public String getNome_agendamento() {
        return nome_agendamento;
    }

    public void setNome_agendamento(String nome_agendamento) {
        this.nome_agendamento = nome_agendamento;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(String horafinal) {
        this.horafinal = horafinal;
    }

    public void adicionar() {
        //Adicionar datas e horarios ao seu proprio painel de usuario.


    }

    public void deletarAgendamento(String key) {
        // deletar do seu painel os horarios marcados da su tela.
        Call<Agendamento> call = new RetrofitConfig().setAgendService().deletarAgend( "Token "+ key,this.getId());
        call.enqueue(new Callback<Agendamento>() {

            @Override
            public void onResponse(@NonNull Call<Agendamento> call, @NonNull Response<Agendamento> response) {
                if (response.isSuccessful()) {
                    confirmarAgendDeletado();

                } else {
                    deletaragendBanco();
                    Aplicacao.irParaAgendamentoActivity(context);

            }

            }

            @Override
            public void onFailure(Call<Agendamento> call, Throwable t) {
                Log.d("Excluiu","nao foi excluido");
            }
        });
    }



    public void editarAgendamento(String key, Context context)  {
        Call<Agendamento> call = new RetrofitConfig().setAgendService().addUserAgend("Token "+key,this.getId(),this);
        call.enqueue(new Callback<Agendamento>() {

            @Override
            public void onResponse(@NonNull Call<Agendamento> call, @NonNull Response<Agendamento> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                       Agendamento agendamento = response.body();
                       agendamento.save();
                       ((AgendamentoActivity)context).inicializandoComponentes();



                    }
                }else {
                    confirmarAgendNaoEditado(context);
                }

            }

            @Override
            public void onFailure(@NonNull Call<Agendamento> call, @NonNull Throwable t) {
                Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());

            }
        });
    }

    public void editMeusAgendamento(String key,Context context){
        Call<Agendamento> call = new RetrofitConfig().setAgendService().removerUserAgend("Token "+key,this.getId(),this);
        call.enqueue(new Callback<Agendamento>() {
            @Override
            public void onResponse(@NonNull Call<Agendamento> call, @NonNull Response<Agendamento> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Agendamento agendamentos = response.body();
                        agendamentos.save();
                        Aplicacao.irParaMeusAgends(context);




                    }
                }else {
                    confirmarAgendNaoEditado(context);
                }

            }

            @Override
            public void onFailure(@NonNull Call<Agendamento> call, @NonNull Throwable t) {
                Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());

            }
        });
    }



    public void adicionarAgendamento() {
        //Salvar em sua paginas seus horarios em tela.



    }



    public static void listarAgendRemoto(@NotNull Usuario usuario, ListView agend_lista_listview) {
        Call<List<Agendamento>> call = new RetrofitConfig().setAgendService().listarAgendRemoto("Token " + usuario.getKey());
        call.enqueue(new Callback<List<Agendamento>>() {
            @Override
            public void onResponse(Call<List<Agendamento>> call, Response<List<Agendamento>> response) {
                if (response.isSuccessful()) {
                    // apagar a lista de agendamentos do banco interno
                    List<Agendamento> agendamentos = response.body();
                    //salvar a lista no banco interno

                    Log.d("listarAgend", "listar");

                    AgendAdapter adaptador = new AgendAdapter(usuario.getContext(), agendamentos);
                     agend_lista_listview.setAdapter(adaptador);
                }
            }

            @Override
            public void onFailure(Call<List<Agendamento>> call, Throwable t) {
                Log.d("listarAgend", "listar");

            }
        });

    }




    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    private void confirmarAgendDeletado() {
        Toast.makeText(this.context, "Agendamento Deletado", Toast.LENGTH_SHORT).show();
    }
    public void deletaragendBanco(){
        this.delete();
    }
    static void confirmarAgendNaoEditado(Context context) {
        ((UsuarioDetalheActivity)context).esconderProgressBar();

        Toast.makeText(context, "Erro ao editar usuário", Toast.LENGTH_SHORT).show();

    }


    public void setUsuario() {
    }


}

