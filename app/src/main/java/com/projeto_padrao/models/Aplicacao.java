package com.projeto_padrao.models;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

import com.projeto_padrao.activities.AppActivity;
import com.projeto_padrao.activities.autenticacao.LoginActivity;
import com.projeto_padrao.activities.autenticacao.RegisterActivity;
import com.projeto_padrao.activities.tarefa.ListarTarefasActivity;
import com.projeto_padrao.activities.usuario.ListarUsuariosActivity;
import com.projeto_padrao.activities.usuario.UsuarioDetalheActivity;

import com.projeto_padrao.chamados.ChamadoDetalheActivity;
import com.projeto_padrao.chamados.ChamadosActivity;


public class Aplicacao {

    private Context context;
    private Class<?> activityDestino;

    public Aplicacao(Context context, Class<?> activityDestino) {
        this.context = context;
        this.activityDestino = activityDestino;
    }


    public static void aguardar(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void apresentarAlertaDeletar(String titulo, String menssagem) {
        new AlertDialog.Builder(context)
                .setTitle(titulo)
                .setMessage(menssagem)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    public Aplicacao(Context context) {
        this.context = context;
    }

    public static void irParaChamadoDetalheActivity(Context context, Long id) {
        Intent intent = new Intent(context, ChamadoDetalheActivity.class);
        Bundle b = new Bundle();
        b.putLong("id", id);
        intent.putExtras(b);
        context.startActivity(intent);
    }

    public static void irParaListarUsuariosActivity(Context context) {
        Intent intent = new Intent(context, ListarUsuariosActivity.class);
        context.startActivity(intent);
    }
    public static void irParaListarLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    public static void irParaChamadosActivity(Context context) {
        Intent intent = new Intent(context, ChamadosActivity.class);
        context.startActivity(intent);




    }
    public static void irParaAppActivity(Context context) {
        Intent intent = new Intent(context, AppActivity.class);
        context.startActivity(intent);
    }
    public static void irParaRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
    public static void irParaLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    public static void irParaListarTarefaActivity(Context context) {
        Intent intent = new Intent(context, ListarTarefasActivity.class);
        context.startActivity(intent);
    }
    public static void irParaUsuarioDetalheActivity(Context context, Long id) {
        Intent intent = new Intent(context, UsuarioDetalheActivity.class);
        Bundle b = new Bundle();
        b.putLong("id", id);
        intent.putExtras(b);
        context.startActivity(intent);
    }


    public static void fecharApp(Context context) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(homeIntent);
    }
}
