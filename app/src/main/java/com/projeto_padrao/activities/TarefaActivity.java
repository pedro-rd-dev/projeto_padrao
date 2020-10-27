package com.projeto_padrao.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projeto_padrao.R;

public class TarefaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_tarefa);
    }
}