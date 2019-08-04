package com.example.agenda.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//Classe aluno
//Classe Serializable permite enviar dados serializados, para por exemplo conseguir enviar de uma activity para outra activity(Como é o exemplo da alteração do aluno
@Entity //Definindo a classe aluno como uma entidade do Room (Basicamente sera uma tabela do banco de tados)
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true) //id sera a primaryKey da entidade aluno ( e autoGenerate define ele como auto incrementavel)
    private int id = 0;
    private String nome;
    //private String sobrenome;
    private String telefone;
    private String email;
    private Calendar dataCadastro = Calendar.getInstance();

    //(alt+enter e Bind constructor cria os atributos e o construtor)
    @Ignore //Ignorando esse contrutor para o Room
    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Aluno() {

    }

    //alt + insert - criar getter e setter

    public Calendar getDataCadastro() { return dataCadastro; }

    public void setDataCadastro(Calendar dataCadastro) { this.dataCadastro = dataCadastro; }

    //public String getSobrenome() { return sobrenome; }

    //public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

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

    public  String getNomeCompleto() {
        return nome; // + " " + sobrenome;
    }

    public String dataFormatada(){
        SimpleDateFormat formatoBrasil = new SimpleDateFormat("dd/MM/yyyy");
        return formatoBrasil.format(dataCadastro.getTime());
    }
}
