package com.example.agenda.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

//Classe para converter o tipo Calender para ser salvo no SQLite, pois o mesmo não suporta esse tipo
public class ConversorCalendar {

    @TypeConverter
    public Long paraLong(Calendar data){
        if(data != null){
            return data.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar paraCalendar(Long data){
        Calendar dataCadastro = Calendar.getInstance();
        if (data != null){
            dataCadastro.setTimeInMillis(data);
        }
        return dataCadastro;
    }
}
