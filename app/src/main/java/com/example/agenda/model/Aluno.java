package com.example.agenda.model;

//Classe aluno
public class Aluno {
    private final String nome;
    private final String telefone;
    private final String email;

    //(alt+enter e Bind constructor cria os atributos e o construtor)
    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    //alt + insert - criar getter e setter

    //Retornando apenas o nome em string
    @Override
    public String toString() {
        return nome;
    }
}
