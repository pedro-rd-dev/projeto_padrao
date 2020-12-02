package com.projeto_padrao.activities.chamados;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.projeto_padrao.R;
import com.projeto_padrao.adapters.ChamadosAdapter;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.chamados.Chamado;
import com.projeto_padrao.models.Usuario;

import java.util.List;

public class ChamadosActivity extends AppCompatActivity {
    private Usuario usuario;
    ListView lista_chamados_ListView;
    ImageView lista_chamados_imageView_criarChamado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_chamados);
        setTitle("Chamado_Impressora");
        usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(ChamadosActivity.this);
        }

        lista_chamados_ListView = (ListView) findViewById(R.id.lista_chamados_ListView);

        lista_chamados_imageView_criarChamado = (ImageView) findViewById(R.id.lista_chamados_imageView_criarChamado);

        lista_chamados_imageView_criarChamado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aplicacao.irParaCriarChamadoActivity(ChamadosActivity.this);
            }
        });


        List<Chamado> chamados = Chamado.listAll(Chamado.class);

        if (chamados !=null){
            ChamadosAdapter chamadosAdapter = new ChamadosAdapter (ChamadosActivity.this, chamados) ;
            lista_chamados_ListView.setAdapter(chamadosAdapter);
        }

        Chamado chamado = new Chamado();
        chamado.listarChamados(usuario.getKey(), lista_chamados_ListView, ChamadosActivity.this);




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

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
        Aplicacao.irParaListarLoginActivity(ChamadosActivity.this);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }



}