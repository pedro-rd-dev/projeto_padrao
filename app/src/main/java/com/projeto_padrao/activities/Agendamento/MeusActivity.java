package com.projeto_padrao.activities.Agendamento;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto_padrao.R;
import com.projeto_padrao.adapters.AgendAdapter;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.MeusAgendamentos;
import com.projeto_padrao.models.Usuario;

public class MeusActivity extends AppCompatActivity {
    ListView agend_lista_user;
    private AgendAdapter adaptador = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meus_agendamentos);
        setTitle("Meus Agendamentos");
        agend_lista_user = (ListView) findViewById(R.id.agend_lista_user);

        Usuario usuario = Usuario.verificaUsuarioLogado();
        // buscar lista de agendamentos no banco interno
        
        //e necessario filtrar os agendamentos que nao possuem user
        if (usuario != null) {
            usuario.setContext(MeusActivity.this);
            MeusAgendamentos meusAgendamentos = new MeusAgendamentos(MeusActivity.this);
            meusAgendamentos.listarAgenduser(usuario, agend_lista_user);



        }
        
    }


    @Override
    public void onBackPressed() {
        Aplicacao.irParaAgendamentoActivity(MeusActivity.this);
        finish();
        super.onBackPressed();
    }

}