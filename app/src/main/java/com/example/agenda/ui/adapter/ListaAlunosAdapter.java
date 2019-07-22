package com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

//Adicionando adapter personalizado, para utilizar com o layout personalizado acima
public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    // Metodo que define a quantidade de elementos que o adapter ira conter
    public int getCount() {
        return alunos.size(); //Quantidade de alunos
    }

    // Metodo para retornar um item pela posição
    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    // Metodo que pega o Id do elemento que esta sendo retornado (Caso os elementos não possuem Id manter o codigo original desse metodo)
    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    // Metodo para apresentar a view para cada item da lista
    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        //Definindo o layour personalizado a ser utilizado nessa view
        View viewCriada = criaView(viewGroup);

        //Adicionando dados do aluno no item da lista
        Aluno alunoDevolvido = alunos.get(posicao);
        vinculaDadosView(viewCriada, alunoDevolvido);

        return viewCriada; //Retornando view Personalizada criada
    }

    private void vinculaDadosView(View viewCriada, Aluno alunoDevolvido) {
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(alunoDevolvido.getNome());
        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefone.setText(alunoDevolvido.getTelefone());
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context)
                .inflate(R.layout.item_aluno_lista, viewGroup, false); //é colocado false para o adapter se responsabilizar apenas por criar a view, e não adicionar a viewGroup(listAlunos)
    }

    //Atualiza lista de alunos, limpando e adicionando todos alunos de novo
    public void atualizaLista(List<Aluno> todosAlunos){
        alunos.clear();
        alunos.addAll(todosAlunos);
    }

    //Removendo aluno da lista
    public void remove(Aluno alunoEscolhido) {
        alunos.remove(alunoEscolhido);
        notifyDataSetChanged(); //Notificando o adapter que a lista foi alterada, para ele atualizar no List da activity
    }
}
