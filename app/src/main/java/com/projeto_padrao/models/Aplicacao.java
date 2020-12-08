package com.projeto_padrao.models;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.projeto_padrao.activities.Agendamento.AgendamentoActivity;
import com.projeto_padrao.activities.Agendamento.MeusActivity;
import com.projeto_padrao.activities.AppActivity;
import com.projeto_padrao.activities.autenticacao.LoginActivity;
import com.projeto_padrao.activities.autenticacao.RegisterActivity;
import com.projeto_padrao.activities.chamados.ChamadoDetalheActivity;
import com.projeto_padrao.activities.chamados.ChamadosActivity;
import com.projeto_padrao.activities.eventos.EventosActivity;
import com.projeto_padrao.activities.eventos.FavoritoActivity;
import com.projeto_padrao.activities.remedio.NaoUsuarioActivity;
import com.projeto_padrao.activities.remedio.RecomendacaoActivity;
import com.projeto_padrao.activities.tarefa.ListarTarefasActivity;
import com.projeto_padrao.activities.usuario.ListarUsuariosActivity;
import com.projeto_padrao.activities.usuario.UsuarioDetalheActivity;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.projeto_padrao.statics.ConstantesGlobais.ADICIONAR;
import static com.projeto_padrao.statics.ConstantesGlobais.REMOVER;


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

    public static void irParaListarUsuariosActivity(Context context) {
        Intent intent = new Intent(context, ListarUsuariosActivity.class);
        context.startActivity(intent);
    }
    public static void irParaListarLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
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

    //Agendamentos
    public static void irParaAgendamentoActivity(Context context) {
        Intent intent = new Intent(context, AgendamentoActivity.class);
        context.startActivity(intent);

    }
    public static void irParaMeusAgends(Context context) {
        Intent intent = new Intent(context, MeusActivity.class);
        context.startActivity(intent);
    }
    public static void AlertarpraAdicionar(Context context, Agendamento agendamento){
        android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(context);
        builder1.setMessage(ADICIONAR);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Aplicacao.isNetworkStatusAvialable(context);
                        Usuario usuario = Usuario.verificaUsuarioLogado();
                        agendamento.setUsuario(usuario.getId());
                        agendamento.editarAgendamento(usuario.getKey(),context);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        android.app.AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public static void AlertarpraRemover(Context context, Agendamento agendamento) {
        android.app.AlertDialog.Builder builder2 = new android.app.AlertDialog.Builder(context);
        builder2.setMessage(REMOVER);
        builder2.setCancelable(true);

        builder2.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Usuario usuario = Usuario.verificaUsuarioLogado();

                        agendamento.setUsuario(null);
                        agendamento.editMeusAgendamento(usuario.getKey(), context);

                    }
                });
        builder2.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        android.app.AlertDialog alert12 = builder2.create();
        alert12.show();
    }

    //Chamados
    public static void irParaChamadosActivity(Context context) {
        Intent intent = new Intent(context, ChamadosActivity.class);
        context.startActivity(intent);

    }
    public static void irParaChamadoDetalheActivity(Context context, Long id) {
        Intent intent = new Intent(context, ChamadoDetalheActivity.class);
        Bundle b = new Bundle();
        b.putLong("id", id);
        intent.putExtras(b);
        context.startActivity(intent);
    }

    //Eventos
    public static void irParaFavoritoActivity(Context context){
        Intent intent = new Intent(context, FavoritoActivity.class);
        context.startActivity(intent);
    }
    public static void irParaEventosActivity(Context context){
        Intent intent = new Intent(context, EventosActivity.class);
        context.startActivity(intent);
    }

    //Remedios
    public static void irParaNaoUsuarioActivity(Context context) {
        Intent intent = new Intent(context, NaoUsuarioActivity.class);
        context.startActivity(intent);
    }
    public static String veritificarHorario() {

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Log.d("Testedata", "Data testada");


        return date;
    }
    public static void irParaRecomendacaoActivity(Context context) {
        Intent intent = new Intent(context, RecomendacaoActivity.class);
        context.startActivity(intent);
    }

    //FECHAR
    public static void fecharApp(Context context) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(homeIntent);
    }

    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
            {
                return netInfos.isConnected();
            }
        }
        Toast.makeText(context, "Porfavor cheque sua conexão com a Internet", Toast.LENGTH_SHORT).show();
        return false;
    }



}
