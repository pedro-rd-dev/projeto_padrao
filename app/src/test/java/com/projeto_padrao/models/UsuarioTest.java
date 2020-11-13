package com.projeto_padrao.models;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.orm.SugarContext;



public class UsuarioTest {
    @Test
    void salvaUsuarioNoBanco(ExtensionContext context) throws Exception {
        Toast.makeText(context.getClass(),"teste",Toast.LENGTH_SHORT).show();
    }



}