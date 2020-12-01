package com.projeto_padrao.activities.eventos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.eventos.Evento;
import com.projeto_padrao.models.eventos.Favorito;

public class EventosActivity extends AppCompatActivity {


    Usuario usuario;
    ListView evento_lista_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Party Time");
        setContentView(R.layout.eventos_lista);

        usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(EventosActivity.this);
        }

        idenfificandoComponentes();

        inicializandoComponentes();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu3, menu);
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

            case R.id.menu_item_favorito:
                Aplicacao.irParaFavoritoActivity(EventosActivity.this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fazerLogoff() {
        usuario.setLogado(false);
        usuario.save();
        Aplicacao.irParaLoginActivity(EventosActivity.this);
    }


    private void idenfificandoComponentes() {

        evento_lista_listview = findViewById(R.id.evento_lista_listview);

    }

    public void inicializandoComponentes() {


        Usuario usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(EventosActivity.this);


            Evento evento = new Evento(EventosActivity.this);
            evento.receberListaDeEventos(usuario,evento_lista_listview);



        }



    }

    public void carregandoListaDeFavoritos(){


        Favorito favorito = new Favorito(EventosActivity.this);
        favorito.receberListaDeFavoritos(usuario,evento_lista_listview);

    }



}


