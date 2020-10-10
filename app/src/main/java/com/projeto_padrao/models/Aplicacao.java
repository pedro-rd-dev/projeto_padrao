package com.projeto_padrao.models;

import android.content.Context;
import android.content.Intent;

import androidx.core.view.KeyEventDispatcher;


public class Aplicacao {

    public void trocarDeActivity(Context activityAtual, Class<?> activityDestino){

        Intent intent = new Intent(activityAtual, activityDestino);
        activityAtual.startActivity(intent);
    }

}
