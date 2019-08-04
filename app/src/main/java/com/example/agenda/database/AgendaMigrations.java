package com.example.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class AgendaMigrations {
    private static final Migration MIGRATION_1_PARA_2 = new Migration(1, 2) { //passando a versão anterior e a nova (1 e 2)
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
        }
    };
    private static final Migration MIGRATION_2_PARA_3 = new Migration(2, 3) { //Migration para alterar o que foi foi na anterior, voltando para o ponto da versão 1
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //Fazer uma copia da tabela atual
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_Copia` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`telefone` TEXT, " +
                    "`email` TEXT)");

            //Copiar dados da tabela antiga para Copia
            database.execSQL("INSERT INTO Aluno_Copia (id, nome, telefone, email)" +
                    "SELECT id, nome, telefone, email FROM Aluno");

            //Removendo a tabela antiga
            database.execSQL("DROP TABLE Aluno");

            //Alterando nome da copia com o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_Copia RENAME TO Aluno");
        }
    };
    private static final Migration MIGRATION_3_PARA_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN dataCadastro INTEGER");
        }
    };
    static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_PARA_2, MIGRATION_2_PARA_3, MIGRATION_3_PARA_4};
}
