package com.example.agenda.DAO;

//Classe não mais usada, era usada antes da implementação do Room (Banco de dados) para mantes os dados pelomenos em memoria
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    //Lista static de alunos
    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    //Metodo para salvar
    public void salvar(Aluno aluno) {

        aluno.setId(contadorDeIds); //Atribuindo id do aluno
        alunos.add(aluno); //Adicionando aluno a lista
        atualizaIds();

    }

    private void atualizaIds() {
        contadorDeIds++; //incrementando id
    }

    public void edita(Aluno aluno){

        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);

        //Se o aluno for encontrado
        if(alunoEncontrado != null){
            int posicaoAluno = alunos.indexOf(alunoEncontrado); //Pegando a posição do aluno encontrado
            alunos.set(posicaoAluno, aluno); //Alterando dados do aluno
        }
    }

    private Aluno buscaAlunoPeloId(Aluno aluno) {
        //Percorrendo a lista de alunos
        for (Aluno a: alunos) {
            if(a.getId() == aluno.getId()){ //Encontrando o aluno a ser editado pelo id
                return a;
            }
        }
        return null;
    }

    //Metodo para pegar todos alunos
    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno alunoEscolhido) {
        Aluno alunoDevolvido = buscaAlunoPeloId(alunoEscolhido);
        if (alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }
}
