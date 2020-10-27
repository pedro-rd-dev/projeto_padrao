package com.projeto_padrao.models;

import com.orm.SugarRecord;

public class Tarefa extends SugarRecord {
    private boolean realizada;
    private String descricao;

    public Tarefa() {
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
