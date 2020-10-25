package com.projeto_padrao.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;

public class AppActivity extends AppCompatActivity {

    private Usuario usuario;
    private ListView listaDeGrupos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aplicacao);

        usuario = Usuario.verificaUsuarioLogado();
        usuario.setContext(AppActivity.this);

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
            case R.id.menu_item_usuarios:
                Aplicacao.irParaListarUsuariosActivity(AppActivity.this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fazerLogoff() {
        usuario.setLogado(false);
        usuario.save();
        Aplicacao aplicacao = new Aplicacao(AppActivity.this,LoginActivity.class);
        aplicacao.trocarDeActivity();
    }

    private void identificandoComponentes() {

    }
    private void inicializandoComponentes() {

    }

}