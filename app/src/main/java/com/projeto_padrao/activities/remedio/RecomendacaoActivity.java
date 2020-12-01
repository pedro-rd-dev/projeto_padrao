package com.projeto_padrao.activities.remedio;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.remedio.Recomendacao;
import com.projeto_padrao.models.remedio.Remedio;

public class RecomendacaoActivity extends AppCompatActivity {

    private View meusRemedios_view_remedios;
    public static ListView recomendacao_lista_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recomendacao_lista);

        idenfificandoComponentes();

        Usuario usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(RecomendacaoActivity.this);
            Remedio.listarRemedioRemoto(usuario, recomendacao_lista_listview, RecomendacaoActivity.this);

        }


    }

    private void idenfificandoComponentes() {
        recomendacao_lista_listview = (ListView) findViewById(R.id.recomendacao_lista_listview);
    }


}







































