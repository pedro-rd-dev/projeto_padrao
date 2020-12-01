package com.projeto_padrao.models.chamados;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import com.orm.SugarRecord;
import com.projeto_padrao.adapters.ChamadosAdapter;
import com.projeto_padrao.api.retrofit.RetrofitConfig;
import com.projeto_padrao.chamados.ChamadoDetalheActivity;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chamado extends SugarRecord {
    private String titulo;
    private String descricao;
    private String dataAbertura;
    private String horarioAbertura;
    private long impressora;
    private String resposta_tecnico;
    private String nome;



    private String email;
    private String statusImpressora;

    public String getResposta_tecnico() {
        return resposta_tecnico;
    }

    public void setResposta_tecnico(String resposta_tecnico) {
        this.resposta_tecnico = resposta_tecnico;
    }

    public Chamado() {


    }
    public String getStatusImpressora() {
        return statusImpressora;
    }



    public void setStatusImpressora(String statusImpressora) {
        this.statusImpressora = statusImpressora;
    }

    public Chamado(String titulo, String descricao, String dataAbertura, String horarioAbertura) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataAbertura = dataAbertura;
        this.horarioAbertura = horarioAbertura;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(String horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public long getImpressora() {
        return impressora;
    }

    public void setImpressora(long impressora) {
        this.impressora = impressora;
    }

    public void listarChamados(@NotNull String key, ListView lista_chamados_ListView, Context context) {
        Call<List<Chamado>> call = new RetrofitConfig().setChamadoService().listarChamados("Token " + key);
        call.enqueue(new Callback<List<Chamado>>() {
            @Override
            public void onResponse(Call<List<Chamado>> call, Response<List<Chamado>> response) {
                if (response.isSuccessful()) {
                    List<Chamado> chamados = response.body();

                    Chamado.deleteAll(Chamado.class);

                    if (chamados != null) {
                        for (Chamado chamado: chamados){
                            chamado.save();
                        }
                    }


                    ChamadosAdapter chamadosAdapter = new ChamadosAdapter (context, chamados) ;
                    lista_chamados_ListView.setAdapter(chamadosAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Chamado>> call, Throwable t) {
                Log.d("listarChamados", "listar");

            }
        });


    }

    public void deletarChamados(@NotNull String key) {
        Call<Chamado> call = new RetrofitConfig().setChamadoService().deletarChamado("Token " + key, this.getId());
        call.enqueue(new Callback<Chamado>() {
            @Override
            public void onResponse(Call<Chamado> call, Response<Chamado> response) {
                if (response.isSuccessful()) {

                    Log.d("deletarChamados", "listar");

                }
            }

            @Override
            public void onFailure(Call<Chamado> call, Throwable t) {
                Log.d("deletarChamados", "deletar");

            }
        });
    }

    public void buscarChamadosPeloId(@NotNull String key, Context context) {
        Call<Chamado> call = new RetrofitConfig().setChamadoService().chamadoPeloId("Token " + key, this.getId());
        call.enqueue(new Callback<Chamado>() {
            @Override
            public void onResponse(Call<Chamado> call, Response<Chamado> response) {
                if (response.isSuccessful()) {

                  ((ChamadoDetalheActivity)context).inicializandoComponentes(response.body());
                    ((ChamadoDetalheActivity)context).emitirAlerta();

                    Log.d("deletarChamados", "listar");

                }
            }

            @Override
            public void onFailure(Call<Chamado> call, Throwable t) {
                Log.d("deletarChamados", "deletar");

            }
        });
    }


    public void criarChamado(@NotNull String key ){
            Call<Chamado> call = new RetrofitConfig().setChamadoService().criarChamado("Token " + key, this);
            call.enqueue(new Callback<Chamado>() {
                @Override
                public void onResponse(Call<Chamado> call, Response<Chamado> response) {
                    if (response.isSuccessful()) {

                        Log.d("criarChamados", "listar");

                    }
                }

                @Override
                public void onFailure(Call<Chamado> call, Throwable t) {
                    Log.d("criarChamados", "deletar");

                }
            });


        }


    }




