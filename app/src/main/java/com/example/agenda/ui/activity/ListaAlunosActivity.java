package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.agenda.ui.activity.ConstantesAcitivities.CHAVE_ALUNO;

/* Pegando como herença a classe AppCompatActivity */
//Em manifest se faz a configuração dos componetes principais do aplicativo
//As Activitys devem ser registradas nele
//Uma activity é um componente do android, onde contem o view, que é a tela exibida para o usuario
//E o codigo que é a parte logica por tras da views

//ctrl + alt + o tira imposts não utilizados

//Em AndroidManifest foi definido essa activity como principal
public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    //Instancia de DAO - onde é guardado a lista de alunos
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    //Adicionando novos comportamentos a activity (O android é baseado em um ciclo de vida, onde os componentes são criados e destuidos)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Colocando o titulo
        setTitle(TITULO_APPBAR); //ctrl + alt + c, convertendo em uma constante

        //Trabalhando com a view - layouts (Arquivos staticos) -- Exibindo na tela
        setContentView(R.layout.activity_lista_alunos); //R representa a pasta res, assim você pode chamar qualquer arquivo statico de la

        //Tratando evento do botão
        ConfigurarBotaoNovoAluno();

        //Exibindo os alunos cadastrados
        configurarLista();

        dao.salvar(new Aluno("Jefferson", "934509586", "j@gmail.com"));
        dao.salvar(new Aluno("Alex", "936059586", "a@gmail.com"));


    }

    //Dando a posibilidade do sistema utilizar menus de contexto (por exemplo quando clique e segura em um usuario e aparece a opçao de remover)
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu); //Utilizando um menu ja criado no menu de contexto
        //menu.add("Remover");
    }

    //Quando um menu de contexto for clicado sera chamado o metodo abaixo (Quando clicar em remover aluno)
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId(); //Pegando o Id do item do menu selecionado (Remover ou Cancelar)
        if(itemId == R.id.activity_lista_alunos_menu_remover){ //Só remove se for clicado na opção remover

            //Pegando os dados do item selecionado (da view que foi clicada que o menu esta vinculado)
            AdapterView.AdapterContextMenuInfo menuInfoitem = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            //Pegando o dados do aluno selecionado
            Aluno alunoEscolhido = adapter.getItem(menuInfoitem.position);
            removeAluno(alunoEscolhido); //Removendo aluno
        }

        return super.onContextItemSelected(item);
    }

    private void ConfigurarBotaoNovoAluno() {
        //Lidando com evento do botão novo aluno
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_bt_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirecionando para tela de formulario
                abrirFormularioModoAdicionaAluno();
            }
        });
    }

    private void abrirFormularioModoAdicionaAluno() {
        //Redirecionando para pagina de cadastro
        startActivity( new Intent(this, FormularioAlunoActivity.class));
    }

    private void configurarLista() {
        //Referenciando ListView do layout
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapterList(listaAlunos);
        //Configurando Clique de alterar aluno
        configuraListenerDeCliquePorItem(listaAlunos);
        //Configurando Clique longo para remover aluno

        registerForContextMenu(listaAlunos); //Definindo que a lista tera um registro de context menu, para efetuar remoção
    }

    private void removeAluno(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido); //Removendo o aluno do DAO (da lista interna)
        adapter.remove(alunoEscolhido); //Removendo o aluno do adapter (a lista da view que é exibida para o usuario)
    }

    private void configuraListenerDeCliquePorItem(ListView listaAlunos) {
        //Tratando o click em um aluno da lista
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // adapterView representa a lista de alunos, view representa a view que foi clicada (o aluno), posição do item e o id do mesmo
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {

                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao); //pegando o aluno selecionado
                abreFormularioModoEditaAluno(alunoEscolhido);

                Log.i("Posicao do aluno", "" + alunoEscolhido); //usando logs
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno alunoEscolhido) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, alunoEscolhido); //Enviando o o aluno escolhido para o formulario, para ser alterado
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapterList(ListView listaAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1); //Modelo de layout ja pronto no android framework
        listaAlunos.setAdapter(adapter);  //Colocando alunos na listView
    }

    //O onResume significa que toda vez que for entrado no activity essa parte do codigo sera reconstruida (Caso de duvidar consultar ciclo de vida android)
    @Override
    protected void onResume() {
        super.onResume();

        //Exibindo e atualizando lista de alunos
        atualizaListaAlunos();

    }

    private void atualizaListaAlunos() {
        //Toda vez que é entrado na activity a lista é limpa e populada de novo (Atualizando novo usuario cadastrado ou alterado)
        adapter.clear();
        //Preenchendo a listView com os dados da lista de alunos
        adapter.addAll(dao.todos()); //Metodo todos() para pegar alunos da lista
    }
}

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


//    private void configuraListenerDeCliqueLongoPorItem(ListView listaAlunos) {
//        //Tratando o clicar e manter precionado em cima de um item da lista
//        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            // adapterView representa a lista de alunos, view representa a view que foi clicada (o aluno), posição do item e o id do mesmo
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
//
//                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao); //pegando o aluno selecionado
//                removeAluno(alunoEscolhido);
//
//                return false; //Retornando false para ele continuar para o proximo item, que no caso é o context menu remover
//                //Se não tivesse o context menu deveria ser Retornando true para não continuar o click, ou seja, não ir para alteração
//            }
//        });
//    }