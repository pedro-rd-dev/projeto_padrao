package com.projeto_padrao.activities.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.projeto_padrao.R;
import com.projeto_padrao.adapters.UsuariosAdapter;
import com.projeto_padrao.models.Usuario;

public class ListarUsuariosActivity extends AppCompatActivity {

    private ListView usuarios_lista_listview;
    private UsuariosAdapter adaptador = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuarios_lista);

        idenfificandoComponentes();
        inicializandoComponentes();
    }


    private void idenfificandoComponentes() {

        usuarios_lista_listview = (ListView) findViewById(R.id.usuarios_lista_listview);

    }

    public void inicializandoComponentes() {


        Usuario usuario = Usuario.verificaUsuarioLogado();
        usuario.setContext(ListarUsuariosActivity.this);

        Usuario.listarUsuariosRemoto(usuario,usuarios_lista_listview);

    }

    @Override
    protected void onResume(){
        super.onResume();
        inicializandoComponentes();
    }

}