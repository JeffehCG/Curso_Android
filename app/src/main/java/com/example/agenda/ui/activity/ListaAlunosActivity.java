package com.example.agenda.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Pegando como herença a classe AppCompatActivity */
//Em manifest se faz a configuração dos componetes principais do aplicativo
//As Activitys devem ser registradas nele
//Uma activity é um componente do android, onde contem o view, que é a tela exibida para o usuario
//E o codigo que é a parte logica por tras da views

//Em AndroidManifest foi definido essa activity como principal
public class ListaAlunosActivity extends AppCompatActivity {
    //Adicionando novos comportamentos a activity (O android é baseado em um ciclo de vida, onde os componentes são criados e destuidos)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Colocando o titulo
        setTitle("Lista de Alunos");

        //Trabalhando com a view - layouts (Arquivos staticos) -- Exibindo na tela
        setContentView(R.layout.activity_lista_alunos); //R representa a pasta res, assim você pode chamar qualquer arquivo statico de la

        //Lista de alunos
        List<String> alunos = new ArrayList<>(
                Arrays.asList("Alex","Fran","Jose","Jefferson", "Maria", "Ana"));
        //Referenciando ListView do layout
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        //Preenchendo a listView com os dados da lista de alunos
        listaAlunos.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, //Modelo de layout ja pronto no android framework
                alunos));

        //Referenciando os textViews do layout
        /*TextView primeiroAluno = findViewById(R.id.textView3);
        TextView segundoAluno = findViewById(R.id.textView4);
        TextView terceiroAluno = findViewById(R.id.textView5);*/

        //Setando valores nos textViews
        /*primeiroAluno.setText(alunos.get(0));
        segundoAluno.setText(alunos.get(1));
        terceiroAluno.setText(alunos.get(2));*/

        //Criando uma view (Forma não recomendada - melhor criar um layout Resource)
        //TextView aluno = new TextView(this);
        //aluno.setText("Jefferson Costa");
        //setContentView(aluno);

        //Exibindo uma mensagem (context é o contexto que sera usado, text é o texto que sera exibito e depois é o tempo que permanesera na tela
        //Toast.makeText(this, "Bem Vindo Usuario", Toast.LENGTH_LONG).show();
    }
}
