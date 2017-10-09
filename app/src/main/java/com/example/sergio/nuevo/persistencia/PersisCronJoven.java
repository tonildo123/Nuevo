package com.example.sergio.nuevo.persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sergio.nuevo.dominio.CronogramaJoven;

import java.util.ArrayList;

/**
 * Created by Operador1 on 04/09/2017.
 */

public class PersisCronJoven extends SQLiteOpenHelper{
    private String sqlCreateCronJoven  = "CREATE TABLE cronograma_joven(_id INTEGER PRIMARY KEY, terminacionDni TEXT,fecha TEXT)";


    public PersisCronJoven(Activity activity) {
        super(activity, "DBTuOficinaDeEmpleo", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateCronJoven);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList levantar() {
        SQLiteDatabase db = getWritableDatabase();
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

    public void guardar(ArrayList<CronogramaJoven> novedades) {
        SQLiteDatabase db = getWritableDatabase();
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
