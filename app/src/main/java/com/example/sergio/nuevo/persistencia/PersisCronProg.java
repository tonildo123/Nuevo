package com.example.sergio.nuevo.persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sergio.nuevo.dominio.CronogramaProgresar;

import java.util.ArrayList;

/**
 * Created by Operador1 on 04/09/2017.
 */

public class PersisCronProg extends SQLiteOpenHelper{
    private String sqlCreateCronProg   = "CREATE TABLE cronograma_progresar(_id INTEGER PRIMARY KEY,terminacionDni TEXT,fecha TEXT)";


    public PersisCronProg(Activity activity) {
        super(activity,"DBTuOficinaDeEmpleo",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateCronProg);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList levantar() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor fila = db.rawQuery("select * from cronograma_progresar", null);
        ArrayList<CronogramaProgresar> cron = new ArrayList<>();

        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{
            cron.add(new CronogramaProgresar(fila.getString(1),fila.getString(2)));
            while (fila.moveToNext()) {
                cron.add(new CronogramaProgresar(fila.getString(1),fila.getString(2)));
            }
            db.close();
            return  cron;
        }
    }

    public void guardar(ArrayList<CronogramaProgresar> novedades) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues registro = new ContentValues();

        for (CronogramaProgresar progresar : novedades){

            registro.put("terminacionDni", progresar.getTerminacionDni());
            registro.put("fecha", progresar.getFecha());

            // los inserto en la base de datos
            db.insert("cronograma_progresar", null, registro);
        }
        db.close();
    }
}
