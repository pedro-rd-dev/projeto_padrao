package com.projeto_padrao.models;

import java.util.Date;

public class Gato {
    private int dataNascimento;
    private String nome;
    private Boolean miando;
    private int idade;

    public Gato(){

    }

    public Gato(String nome,Boolean miando,int dataNascimento) {
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.miando = miando;
        this.calcularIdade();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(int dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getMiando() {
        return miando;
    }

    public void setMiando(Boolean miando) {
        this.miando = miando;
    }

    public void miar(){
        this.miando = true;
    }

    public void calcularIdade(){
        this.idade = 30 - this.dataNascimento;
    }
}
