package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* Pegando como herença a classe AppCompatActivity */
//Em manifest se faz a configuração dos componetes principais do aplicativo
//As Activitys devem ser registradas nele
//Uma activity é um componente do android, onde contem o view, que é a tela exibida para o usuario
//E o codigo que é a parte logica por tras da views

//ctrl + alt + o tira imposts não utilizados

//Em AndroidManifest foi definido essa activity como principal
public class ListaAlunosActivity extends AppCompatActivity {

    public static final String LISTA_DE_ALUNOS = "Lista de Alunos";
    //Instancia de DAO - onde é guardado a lista de alunos
    private final AlunoDAO dao = new AlunoDAO();

    //Adicionando novos comportamentos a activity (O android é baseado em um ciclo de vida, onde os componentes são criados e destuidos)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Colocando o titulo
        setTitle(LISTA_DE_ALUNOS); //ctrl + alt + c, convertendo em uma constante

        //Trabalhando com a view - layouts (Arquivos staticos) -- Exibindo na tela
        setContentView(R.layout.activity_lista_alunos); //R representa a pasta res, assim você pode chamar qualquer arquivo statico de la

        //Tratando evento do botão
        ConfigurarBotaoNovoAluno();

        //Lista de alunos
        //List<String> alunos = new ArrayList<>(
        //        Arrays.asList("Alex","Fran","Jose","Jefferson", "Maria", "Ana"));

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

    private void ConfigurarBotaoNovoAluno() {
        //Lidando com evento do botão novo aluno
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_bt_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirecionando para tela de formulario
                abrirFormularioAlunoActivity();
            }
        });
    }

    private void abrirFormularioAlunoActivity() {
        //Redirecionando para pagina de cadastro
        startActivity( new Intent(this, FormularioAlunoActivity.class));
    }

    //O onResume significa que toda vez que for entrado no activity essa parte do codigo sera reconstruida (Caso de duvidar consultar ciclo de vida android)
    @Override
    protected void onResume() {
        super.onResume();

        //Exibindo os alunos cadastrados
        configurarLista();

    }

    private void configurarLista() {
        //Referenciando ListView do layout
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        //Preenchendo a listView com os dados da lista de alunos
        listaAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, //Modelo de layout ja pronto no android framework
                dao.todos())); //Metodo todos() para pegar alunos da lista
    }
}
