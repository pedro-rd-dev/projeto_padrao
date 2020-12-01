package com.projeto_padrao.activities.Agendamento;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto_padrao.R;
import com.projeto_padrao.adapters.AgendAdapter;
import com.projeto_padrao.models.Agendamento;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.activities.Agendamento.MeusActivity;

import java.util.List;


public class AgendamentoActivity extends AppCompatActivity {
    ListView agend_lista_listview;
    private AgendAdapter adaptador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agend_lista);
        setTitle("Studio Agendamento");

        inicializandoComponentes();

    }

    public void inicializandoComponentes() {
        agend_lista_listview = (ListView) findViewById(R.id.agend_lista_listview);

        Usuario usuario = Usuario.verificaUsuarioLogado();
        List<Agendamento> agendamentos = Agendamento.listAll(Agendamento.class);
        //e necessario filtrar os agendamentos que possuem user
        listarAgendamentos(agendamentos);

        if (usuario != null) {
            usuario.setContext(AgendamentoActivity.this);
            Agendamento.listarAgendRemoto(usuario, agend_lista_listview);
        }

    }

    public void listarAgendamentos(List<Agendamento> agendamentos) {
        //chama o adaptador
        AgendAdapter adaptador = new AgendAdapter(AgendamentoActivity.this, agendamentos);
        agend_lista_listview.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agend, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_meus:
                meus_agend();
                return true;
            case R.id.menu_item_voltar:
                voltar();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
       Aplicacao.irParaAppActivity(AgendamentoActivity.this);
        finish();

        super.onBackPressed();
    }

    private void meus_agend() {
        Aplicacao.irParaMeusAgends(AgendamentoActivity.this);
        finish();
    }

    private void voltar() {

        Aplicacao.irParaAppActivity(AgendamentoActivity.this);
        finish();
    }

}