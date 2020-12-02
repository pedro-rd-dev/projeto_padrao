package com.projeto_padrao.activities.chamados;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.chamados.Chamado;
import com.projeto_padrao.models.chamados.Impressora;

public class CriarChamadoActivity extends AppCompatActivity {


    private EditText criar_chamado_activity_descricao, criar_chamado_activity_titulo;
    private Button criar_chamado_activity_button_criarChamado;
    private TextView criar_chamado_activity_voltar, criar_chamado_activity_erro_descricao, criar_chamado_activity_erro_titulo;
    private Usuario usuario;
    private Spinner criar_chamado_activity_menuImpressora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_chamado_activity);

        usuario = Usuario.verificaUsuarioLogado();

        identificandoComponentes();
        inicializandoComponentes();
        criar_chamado_activity_erro_descricao.setVisibility(View.GONE);
        criar_chamado_activity_erro_titulo.setVisibility(View.GONE);
    }


    private void identificandoComponentes() {

        criar_chamado_activity_button_criarChamado = (Button) findViewById(R.id.criar_chamado_activity_button_criarChamado);
        criar_chamado_activity_descricao = (EditText) findViewById(R.id.criar_chamado_activity_descricao);
        criar_chamado_activity_voltar = (TextView) findViewById(R.id.criar_chamado_activity_voltar);
        criar_chamado_activity_erro_descricao = (TextView) findViewById(R.id.criar_chamado_activity_erro_descricao);
        criar_chamado_activity_titulo = (EditText) findViewById(R.id.criar_chamado_activity_titulo);
        criar_chamado_activity_erro_titulo = (TextView) findViewById(R.id.criar_chamado_activity_erro_titulo);
        criar_chamado_activity_menuImpressora = (Spinner) findViewById(R.id.criar_chamado_activity_menuImpressora);


    }

    private void inicializandoComponentes() {
        //verificar a lista de impresoras
        Impressora impressora = new Impressora();
        impressora.listarImpressoras(usuario.getKey(), CriarChamadoActivity.this, criar_chamado_activity_menuImpressora);


        criar_chamado_activity_button_criarChamado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String descricao = criar_chamado_activity_descricao.getText().toString();

                String titulo = criar_chamado_activity_titulo.getText().toString();
                String impressora = criar_chamado_activity_menuImpressora.getSelectedItem().toString();

                Chamado chamado = new Chamado();
                chamado.setDescricao(descricao);
                chamado.setTitulo(titulo);


                Impressora impressoraBanco = Impressora.findById(Impressora.class, Integer.parseInt(impressora));

                impressoraBanco.setStatus_impressora("F");

                chamado.setImpressora(impressoraBanco.getId());


                chamado.criarChamado(usuario.getKey());
                Aplicacao.irParaChamadosActivity(CriarChamadoActivity.this);


            }

        });

        criar_chamado_activity_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CriarChamadoActivity.this, com.projeto_padrao.activities.chamados.ChamadosActivity.class);
                startActivity(intent);
            }
        });

    }
}




