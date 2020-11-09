package com.projeto_padrao.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void salvaUsuarioNoBanco() {
        Usuario usuario = new Usuario();
        usuario.setNome("pedro");
        usuario.save();

    }
}