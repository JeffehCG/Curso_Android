package com.example.agenda.DAO;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    //Lista static de alunos
    private final static List<Aluno> alunos = new ArrayList<>();

    //Metodo para salvar
    public void salvar(Aluno aluno) {
        alunos.add(aluno); //Adicionando aluno a lista
    }

    //Metodo para pegar todos alunos
    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
