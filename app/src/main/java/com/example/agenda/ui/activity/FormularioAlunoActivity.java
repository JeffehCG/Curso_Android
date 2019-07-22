package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import static com.example.agenda.ui.activity.ConstantesAcitivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_ADICIONA = "Novo Aluno"; //constante, não muda
    public static final String TITULO_APPBAR_ALTERA = "Edita Aluno"; //constante, não muda
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    //DAO - data acess object (interface para salvar os dados do aluno)
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno; //Instancia da classe Aluno

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        //Pegando a referencia dos inputs da view, para poder pegar os valores
        InicializarCampos();

        //Carrega dados do aluno, para alteração ou instancia aluno apra inserção
        carregaAluno();

        //Tratando o evento do click do botão
        //ConfigurarBotaoSalvar();
    }

    //Criando o menu de opções (Botão salvar do canto superior direito)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Tratar a ação da opção salvar clicando
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar){

            preencheAluno(); //adiciona valores do formulario a instancia aluno
            //Altera ou salva novo aluno
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        //Pegando os dados do aluno passados pelo Intent, quando é selecionado para alteração
        Intent dados = getIntent();
        //Verificando se a chave aluno existe, para não dar conflito com a inserção de aluno
        if(dados.hasExtra(CHAVE_ALUNO)){
            setTitle(TITULO_APPBAR_ALTERA);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            //Setando os dados desse aluno no formulario
            preencheCampos();
        }else {
            //Colocando o titulo
            setTitle(TITULO_APPBAR_ADICIONA);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void finalizaFormulario() {
        //Verificando se existe um id no aluno, ou se ele é valido, para edição
        if (aluno.temIdValido()) {
            dao.edita(aluno); //Editando aluno
        }else {
            dao.salvar(aluno); //Salvando os dados no DAO
        }

        //Finalizando activity atual e voltando para anterior (Lista)
        finish();
    }

    private void InicializarCampos() {
        //ctrl + alt + f refatora uma variavel do active para um atributo
        //Pegando referencia para os inputs(É preciso colocar final por causa da classa anonima
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    //Em cima de determinada parte do codigo onde você queira tornar um metodo, selecionando o mesmo e escrevendo ctrl + alt + m ele refatora e cria o metodo
    private void preencheAluno() {
        //Pegando os valores do campo quando clicar
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        //setando os valores a instancia aluno
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }

//    private void ConfigurarBotaoSalvar() {
//        //Pegando a referencia para o botão salvar
//        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
//        //Lidando com o evendo do click
//        botaoSalvar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                preencheAluno(); //adiciona valores do formulario a instancia aluno
//                //Altera ou salva novo aluno
//                finalizaFormulario();
//
//            }
//        });
//    }
}



//Instanciando classe (Se ela não existir alt+enter criar uma)
//        return new Aluno(nome,telefone,email);


//É colocado FormularioAlunoActivity.this por que ela é uma classe anonima, assim é preciso pegar a referencia do Activity
//Toast.makeText(FormularioAlunoActivity.this,
//        alunoCriado.toString() , Toast.LENGTH_SHORT).show();