package com.projeto_padrao.models;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.projeto_padrao.activities.AppActivity;
import com.projeto_padrao.activities.LoginActivity;
import com.projeto_padrao.activities.usuario.ListarUsuariosActivity;
import com.projeto_padrao.activities.usuario.UsuarioDetalheActivity;


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

    public static void irParaListarUsuariosActivity(Context context) {
        Intent intent = new Intent(context, ListarUsuariosActivity.class);
        context.startActivity(intent);
    }
    public static void irParaListarLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    public static void irParaUsuarioDetalheActivity(Context context, Long id) {
        Intent intent = new Intent(context, UsuarioDetalheActivity.class);
        Bundle b = new Bundle();
        b.putLong("id", id);
        intent.putExtras(b);
        context.startActivity(intent);
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
