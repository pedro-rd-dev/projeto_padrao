package com.projeto_padrao.models;

import android.content.Context;
import android.content.Intent;
import android.os.CpuUsageInfo;

import com.projeto_padrao.activities.AppActivity;


public class Aplicacao {

    private Context context;
    private Class<?> activityDestino;

    public Aplicacao(Context context, Class<?> activityDestino) {
        this.context = context;
        this.activityDestino = activityDestino;
    }

    public Aplicacao(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Class<?> getActivityDestino() {
        return activityDestino;
    }

    public void setActivityDestino(Class<?> activityDestino) {
        this.activityDestino = activityDestino;
    }

    public void trocarDeActivity(){

        Intent intent = new Intent(this.context, this.activityDestino);
        this.context.startActivity(intent);
    }


}
