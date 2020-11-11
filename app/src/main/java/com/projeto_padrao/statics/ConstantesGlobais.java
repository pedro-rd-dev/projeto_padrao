package com.projeto_padrao.statics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstantesGlobais {

    public static final String NOME_APP = "PROJETO PADRÃO";
    public static final String SENHAS_DISTINTAS = "As senhas não são iguais";
    public static final String ENDERECO_API ="https://escolarap.herokuapp.com/";
    public static ArrayList<String> ADMINS (){
        ArrayList<String> admins = new ArrayList<String>();
        admins.add("pedroh.mix@gmail.com");
        admins.add("admin@gmail.com");
        admins.add("rotina@gmail.com");

        return admins;
    }

}