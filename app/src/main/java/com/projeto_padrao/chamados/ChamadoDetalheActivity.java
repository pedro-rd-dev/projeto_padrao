package com.projeto_padrao.chamados;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.chamados.Chamado;

public class ChamadoDetalheActivity extends AppCompatActivity {


    private TextView chamado_detalhe_textview_titulo, chamado_detalhe_textview_respostaTecnico,
            chamado_detalhe_textview_id, chamado_detalhe_textview_descricao, chamado_detalhe_textview_dataAbertura,
            chamado_detalhe_textview_horaAbertura;
    private Usuario usuarioLogado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chamado_detalhe);

        Bundle b = getIntent().getExtras();
        long id_usuario_lista = -1; // or other values
        if(b != null){
            id_usuario_lista = b.getLong("id");
        }

        Log.d("activity","Valor do id recebido: "+ id_usuario_lista);

        identificandoComponentes();

        usuarioLogado = Usuario.verificaUsuarioLogado();

        Chamado chamado = Chamado.findById(Chamado.class, id_usuario_lista);

        inicializandoComponentes(chamado);

        chamado.buscarChamadosPeloId(usuarioLogado.getKey(), ChamadoDetalheActivity.this);
    }



    private void identificandoComponentes() {

        chamado_detalhe_textview_titulo = (TextView) findViewById(R.id.chamado_detalhe_textview_titulo);
        chamado_detalhe_textview_id = (TextView) findViewById(R.id.chamado_detalhe_textview_id);
        chamado_detalhe_textview_descricao = (TextView) findViewById(R.id.chamado_detalhe_textview_descricao);
        chamado_detalhe_textview_dataAbertura = (TextView) findViewById(R.id.chamado_detalhe_textview_dataAbertura);
        chamado_detalhe_textview_horaAbertura = (TextView) findViewById(R.id.chamado_detalhe_textview_horaAbertura);
        chamado_detalhe_textview_respostaTecnico = (TextView) findViewById(R.id.chamado_detalhe_textview_respostaTecnico);
    }

    public void inicializandoComponentes(Chamado chamado) {


        chamado_detalhe_textview_titulo.setText(chamado.getTitulo());
        chamado_detalhe_textview_id.setText(chamado.getId().toString());
        chamado_detalhe_textview_descricao.setText(chamado.getDescricao());
        chamado_detalhe_textview_dataAbertura.setText(chamado.getDataAbertura());
        chamado_detalhe_textview_horaAbertura.setText(chamado.getHorarioAbertura());
        chamado_detalhe_textview_respostaTecnico.setText(chamado.getResposta_tecnico());




    }


    public void emitirAlerta() {
        Toast.makeText(this, "atualizado", Toast.LENGTH_SHORT).show();
    }
}