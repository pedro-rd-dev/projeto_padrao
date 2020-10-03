package com.projeto_padrao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto_padrao.models.Android;
import com.projeto_padrao.observers.GpsObserver;

import static com.projeto_padrao.statics.ConstantesGlobais.NOME_APP;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_sem_vetor);

        Toast.makeText(this,NOME_APP,Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        Log.d("clico_de_vida", "onStart - O codigo da atividade Começou a ser executado");
        super.onStart();
    }
    @Override
    protected void onResume() {


        Log.d("clico_de_vida", "onResume -Estado de interação com a tela");
        super.onResume();

    }

    @Override
    protected void onPause() {
        Log.d("clico_de_vida", "onPause - iniciou o término da activity");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("clico_de_vida", "onStop - A atividade não está mais visivel ao usuario");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("clico_de_vida", "onDestroy- A Activity foi completamente destruida" );
        super.onDestroy();
    }
}