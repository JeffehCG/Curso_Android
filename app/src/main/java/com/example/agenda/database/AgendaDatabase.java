package com.example.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.agenda.database.converter.ConversorCalendar;
import com.example.agenda.database.dao.RoomAlunoDao;
import com.example.agenda.model.Aluno;

import static com.example.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

//Classe de comunicação com o banco Room
//Definindo as entidades (array de referência de classes configuradas como Entity (Classe que seram armazenadas no banco))
//versão do banco - cada vez que algum dado for adiciona , removido ou alterado na implementação do sistema, como criação de um novo campo no formulario, a versão deve ser alterada
//exportSchema Define que não sera gerado o modelo em Json de como o banco foi criado (O Room cria ele automaticamente apartir das classes implementadas com entidades)
@Database(entities = {Aluno.class}, version = 4, exportSchema = false)
@TypeConverters({ConversorCalendar.class}) //Definindo o conversor do Tipo Calendar
public abstract class AgendaDatabase extends RoomDatabase { //Definindo a classe abstract para não precisar implementar os metodos de RoomDatabase
    private static final String NOME_DATA_BASE = "agenda.db";

    //Metodo para pegar o Dao relacionado ao Room
    public abstract RoomAlunoDao getRoomAlunoDao();

    //Metodo para instanciar o database
    public static AgendaDatabase getInstance(Context contexto){ //Ira retornar a propria instancia
        //Criando uma instancia do database (Passando uma referencia de contexto, a classe com herença de RoomDatabase, e a string do nome do arquivo que armazera os dados do banco)
        return Room.databaseBuilder(contexto, AgendaDatabase.class, NOME_DATA_BASE)
                .allowMainThreadQueries() //Permitindo que seja executado na thread principal (não recomentado)
                //.fallbackToDestructiveMigration() // Destruindo o banco atual e criando um novo, quando tem a criação de um campo novo etc... (Não recomendado)
                .addMigrations(TODAS_MIGRATIONS) //Fazendo a migração de versão do banco ( quando a alguma alteração no mesmo)
                .build();
    }
}
