package com.example.agenda.model;

import java.io.Serializable;

//Classe aluno
//Classe Serializable permite enviar dados serializados, para por exemplo conseguir enviar de uma activity para outra activity(Como é o exemplo da alteração do aluno
public class Aluno implements Serializable {

    private int id = 0;
    private String nome;
    private String telefone;
    private String email;

    //(alt+enter e Bind constructor cria os atributos e o construtor)
    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Aluno() {

    }

    //alt + insert - criar getter e setter


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    //Retornando apenas o nome e telefone em string para lista
    @Override
    public String toString() {
        return nome + " - " + telefone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0 ;
    }
}
