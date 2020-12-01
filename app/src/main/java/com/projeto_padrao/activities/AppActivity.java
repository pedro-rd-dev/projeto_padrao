package com.projeto_padrao.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.observers.ActivityObserver;

import java.util.ArrayList;
import java.util.List;

public class AppActivity extends AppCompatActivity {

    private Usuario usuario;
    private ListView listaDeGrupos;
    private View aplicacao_view_tarefas,aplicacao_view_usuarios;
    private View aplicacao_view_chamados,view5,aplicacao_view_evento,view6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aplicacao);

        usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(AppActivity.this);
        }

        identificandoComponentes();
        inicializandoComponentes();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_logoff:
                fazerLogoff();
                return true;
            case R.id.menu_item_deletar_conta:
                usuario.deletarUsuario();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fazerLogoff() {
        usuario.setLogado(false);
        usuario.save();
        Aplicacao.irParaListarLoginActivity(AppActivity.this);
    }

    private void identificandoComponentes() {
        aplicacao_view_tarefas = (View) findViewById(R.id.aplicacao_view_tarefas);
        aplicacao_view_usuarios = (View) findViewById(R.id.aplicacao_view_usuarios);
        view5 = (View) findViewById(R.id.view5);
        aplicacao_view_chamados = (View) findViewById(R.id.aplicacao_view_chamados);
        aplicacao_view_evento = (View) findViewById(R.id.aplicacao_view_evento);
        view6 = (View) findViewById(R.id.view6);

    }
    private void inicializandoComponentes() {
        aplicacao_view_tarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aplicacao.irParaListarTarefaActivity(AppActivity.this);
            }
        });
        aplicacao_view_usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aplicacao.irParaListarUsuariosActivity(AppActivity.this);
            }
        });
        //Agendamentos
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aplicacao.irParaAgendamentoActivity(AppActivity.this);
            }
        });

        //Chamados
        aplicacao_view_chamados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aplicacao.irParaChamadosActivity(AppActivity.this);
            }
        });

        //Eventos
        aplicacao_view_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aplicacao.irParaEventosActivity(AppActivity.this);
            }
        });

        //Remedios
        view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Aplicacao.irParaAgendamentoActivity(AppActivity.this);
            }
        });



    }


    @Override
    public void onBackPressed() {
        Aplicacao.fecharApp(AppActivity.this);

        super.onBackPressed();
    }
}