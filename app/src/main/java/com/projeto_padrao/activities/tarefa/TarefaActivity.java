package com.projeto_padrao.activities.tarefa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Tarefa;
import com.projeto_padrao.models.Usuario;

public class TarefaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_tarefa);

        Usuario usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(TarefaActivity.this);
        }

        Tarefa tarefa = new Tarefa(TarefaActivity.this);
        tarefa.receberListaDeTarefas(usuario.getKey());

    }
}