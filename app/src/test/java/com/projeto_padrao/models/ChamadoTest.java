package com.projeto_padrao.models;


import com.projeto_padrao.models.chamados.Chamado;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChamadoTest {

    private static Usuario usuarioteste = new Usuario("pedroh.mix@gmail.com","123456");
    private  static Chamado chamado = new Chamado();



    @Test
    public void A_ListarChamadosTest(){

    }
}
