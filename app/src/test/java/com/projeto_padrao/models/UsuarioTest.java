package com.projeto_padrao.models;

import android.content.Context;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;

import com.orm.SugarContext;

import java.lang.reflect.Method;

import static com.google.common.truth.Truth.assertThat;

public class UsuarioTest {
    @Test
    void salvaUsuarioNoBanco() {
        Context context  = ApplicationProvider.getApplicationContext();
        SugarContext.init(context);
        Usuario usuario = new Usuario();
        long resultado = usuario.save();
        assertThat(resultado).isEqualTo(1L);

    }


}