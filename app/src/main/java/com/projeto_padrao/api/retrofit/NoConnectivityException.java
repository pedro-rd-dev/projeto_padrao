package com.projeto_padrao.api.retrofit;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    private Context context;

    public NoConnectivityException(Context mContext) {
        this.context = mContext;
    }

    @Override
    public String getMessage() {
        ((Activity)context).runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = Toast.makeText(context, "Verifique a internet", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        return "Verifique a internet";
    }

}
