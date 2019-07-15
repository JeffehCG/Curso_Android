package com.example.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo Aluno"; //constante, não muda
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    //DAO - data acess object (interface para salvar os dados do aluno)
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        //Colocando o titulo
        setTitle(TITULO_APPBAR);

        //Pegando a referencia dos inputs da view, para poder pegar os valores
        InicializarCampos();

        //Tratando o evento do click do botão
        ConfigurarBotaoSalvar();

    }

    private void ConfigurarBotaoSalvar() {
        //Pegando a referencia para o botão salvar
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        //Lidando com o evendo do click
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Aluno alunoCriado = criarAluno();
                salvarAluno(alunoCriado);

                //É colocado FormularioAlunoActivity.this por que ela é uma classe anonima, assim é preciso pegar a referencia do Activity
                //Toast.makeText(FormularioAlunoActivity.this,
                //        alunoCriado.toString() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InicializarCampos() {
        //ctrl + alt + f refatora uma variavel do active para um atributo
        //Pegando referencia para os inputs(É preciso colocar final por causa da classa anonima
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salvarAluno(Aluno alunoCriado) {
        //Salvando os dados no DAO
        dao.salvar(alunoCriado);

        //Finalizando activity atual e voltando para anterior (Lista)
        finish();
    }

    //Em cima de determinada parte do codigo onde você queira tornar um metodo, selecionando o mesmo e escrevendo ctrl + alt + m ele refatora e cria o metodo
    private Aluno criarAluno() {
        //Pegando os valores do campo quando clicar
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        //Instanciando classe (Se ela não existir alt+enter criar uma)
        return new Aluno(nome,telefone,email);
    }
}
