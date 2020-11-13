package com.projeto_padrao.models;


import android.content.Context;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import org.junit.Test;


public class EmailValidatorTest {

    private static final String FAKE_STRING = "HELLO_WORLD";
    private Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void readStringFromContext_LocalizedString() {
        Log.d("teste","teste: "+ context);
    }
}