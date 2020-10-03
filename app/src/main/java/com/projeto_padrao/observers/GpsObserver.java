package com.projeto_padrao.observers;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

//podemos criar uma classe para objservar o comportamento da activity
//this.getLifecycle().addObserver(new GpsObserver());

public class GpsObserver implements LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void stopGps() {
        Log.d("evento","ONPAUSE");
    }

}