package com.example.agenda;

//Classe não utilizada mais, apartir do uso do Room não é preciso adicionar os alunos de teste na inicalização do app
import android.app.Application;

import androidx.room.Room;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.RoomAlunoDao;
import com.example.agenda.model.Aluno;

//Classe que o codigo sera executado apenas uma vez, na abertura do aplicativo
@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application { //Essa classe deve ser definida em Manifest, no atributo name

    @Override
    public void onCreate() {
        super.onCreate();

        //Cuidado, nessa classe devem ser usados procedimentos rapidos, no exemplo abaixo é demorado 2 segundos, dessa forma, o aplicativo ir demorar esse tempo para iniciar
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //Criando uma instancia do database
        //AgendaDatabase database = AgendaDatabase.getInstance(this);
        //Tendo acesso o Dao do database (Onde sera armazenado os dados)
        //RoomAlunoDao dao = database.getRoomAlunoDao();


        //Criando alunos teste na lista
        //AlunoDAO dao = new AlunoDAO(); //Antigo dao, que salvava os dados em uma classe
        //dao.salvar(new Aluno("Jefferson", "934509586", "j@gmail.com"));
        //dao.salvar(new Aluno("Alex", "936059586", "a@gmail.com"));
    }
}
