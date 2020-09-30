package com.projeto_padrao.models;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Android {
    private boolean conected;
    private Context context;

    public Android(Context context) {
        this.context = context;
        this.isNetworkConnected();
    }

    public boolean getConected() {
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

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()){
            this.conected = true;
            return true;
        }
        this.conected = false;
        return false;
    }

}
