package com.example.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agenda.model.Aluno;

import java.util.List;

@Dao
public interface RoomAlunoDao {

    //Metodo para salvar aluno no DB
    @Insert
    void salvar(Aluno aluno);

    //Metodo para retornar alunos do banco
    @Query("SELECT * FROM aluno")
    List<Aluno> todos();

    //Metodo para remover aluno
    @Delete
    void remove(Aluno alunoEscolhido);

    //Metodo para alterar aluno
    @Update
    void edita(Aluno aluno);
}
