package com.example.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.RoomAlunoDao;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

//Classe usada para delegar algumas funções da activity, para não deixar a mesma muito carregado de funções
public class ListaAlunosViewComportamentos {

    //Instancia de DAO - onde é guardado a lista de alunos no DB
    private final RoomAlunoDao dao;
    private final ListaAlunosAdapter adapter; //Instanciando adpter - que ira adaptar os dados do aluno a lista
    private final Context context;

    public ListaAlunosViewComportamentos(Context context) {
        this.context = context;
        adapter = new ListaAlunosAdapter(this.context);
        //Criando uma instancia do database
        dao = AgendaDatabase.getInstance(context)
                .getRoomAlunoDao();
    }

    public void confirmacaoRemocao(@NonNull final MenuItem item) {
        //Quando clicar em sim no alert sera executado o codigo abaixo, para excluir
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover esse aluno ?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    //Pegando os dados do item selecionado (da view que foi clicada que o menu esta vinculado)
                    AdapterView.AdapterContextMenuInfo menuInfoitem = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                    //Pegando o dados do aluno selecionado
                    Aluno alunoEscolhido = adapter.getItem(menuInfoitem.position);
                    removeAluno(alunoEscolhido); //Removendo aluno
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaListaAlunos() {
        //Toda vez que é entrado na activity a lista é limpa e populada de novo (Atualizando novo usuario cadastrado ou alterado)

        adapter.atualizaLista(dao.todos());
        //Preenchendo a listView com os dados da lista de alunos
        //Metodo todos() para pegar alunos da lista
    }

    private void removeAluno(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido); //Removendo o aluno do DAO (da lista interna)
        adapter.remove(alunoEscolhido); //Removendo o aluno do adapter (a lista da view que é exibida para o usuario)
    }

    public void configuraAdapterList(ListView listaAlunos) {
        listaAlunos.setAdapter(adapter);  //Colocando alunos na listView
    }
}
