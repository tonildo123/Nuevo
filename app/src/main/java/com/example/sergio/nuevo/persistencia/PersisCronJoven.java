package com.example.sergio.nuevo.persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sergio.nuevo.dominio.CronogramaJoven;
import com.example.sergio.nuevo.dominio.CronogramaProgresar;

import java.util.ArrayList;

/**
 * Created by Operador1 on 04/09/2017.
 */

public class PersisCronJoven {
    private DBTuOficinaDeEmpleo cronProg;


    public PersisCronJoven(Activity activity) {
        cronProg = new DBTuOficinaDeEmpleo(activity,"DBTuOficinaDeEmpleo",null,1);
    }

    public ArrayList levantarNoticias() {
        SQLiteDatabase db = cronProg.getWritableDatabase();
        Cursor fila = db.rawQuery("select * from cronograma_joven", null);
        ArrayList<CronogramaJoven> cron = new ArrayList<>();

        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{
            cron.add(new CronogramaJoven(fila.getString(1),fila.getString(2)));
            while (fila.moveToNext()) {
                cron.add(new CronogramaJoven(fila.getString(1),fila.getString(2)));
            }
            db.close();
            return  cron;
        }
    }

    public void guardarNoticias(ArrayList<CronogramaJoven> novedades) {
        SQLiteDatabase db = cronProg.getWritableDatabase();
        ContentValues registro = new ContentValues();

        for (CronogramaJoven joven : novedades){

            registro.put("terminacionDni", joven.getTerminacionDni());
            registro.put("fecha", joven.getFecha());

            // los inserto en la base de datos
            db.insert("cronograma_joven", null, registro);
        }
        db.close();
    }
}
