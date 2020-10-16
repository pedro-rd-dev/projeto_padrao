package com.projeto_padrao.models;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class Android {
    private boolean conected;
    private Context context;
    private String meuAtributoComMaisDeUmaPalavra;

    public Android(Context context) {
        this.context = context;
        this.isNetworkConnected();
    }

    public boolean getConected(Context context) {
        if(!this.conected){
            Toast.makeText(context,"Verifique a internet",Toast.LENGTH_SHORT).show();
        }
        return conected;
    }

    public void setConected(boolean conected) {
        this.conected = conected;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()){
            this.conected = true;
            return;
        }
        this.conected = false;
    }
}
