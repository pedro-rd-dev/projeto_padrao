package com.projeto_padrao.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Evento;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.retrofit.RetrofitConfig;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppActivity extends AppCompatActivity {

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aplicacao);

        usuario = Usuario.verificaUsuarioLogado();

        identificandoComponentes();
        inicializandoComponentes();
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