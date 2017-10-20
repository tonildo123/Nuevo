package com.example.sergio.nuevo.persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sergio.nuevo.dominio.CronogramaJoven;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Operador1 on 04/09/2017.
 */

public class PersisCronJoven {
    private DBTuOficinaDeEmpleo cronJoven;


    public PersisCronJoven(Activity activity) {
        cronJoven = new DBTuOficinaDeEmpleo(activity,"DBTuOficinaDeEmpleo",null,1);
    }

    public ArrayList levantar() {
        SQLiteDatabase db = cronJoven.getWritableDatabase();
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
        SQLiteDatabase db = cronJoven.getWritableDatabase();
        ContentValues registro = new ContentValues();
        Cursor fila = db.rawQuery("select * from cronograma_joven", null);

        boolean b;
        if(!fila.moveToFirst()){
            b=false;
        }else{
            b=true;
        }

        int i = 1;
        for (CronogramaJoven joven : novedades){

            registro.put("terminacionDni", joven.getTerminacionDni());
            registro.put("fecha", joven.getFecha());
            Date fecha = new Date();
            String dia = "";
            if(fecha.getDate() < 10){
                dia = dia.concat("0"+fecha.getDate());
            }else{
                dia = String.valueOf(fecha.getDate());
            }
            String mes = "";
            if(fecha.getMonth() < 9){
                mes = mes.concat("0"+(fecha.getMonth()+1));
            }else{
                mes = String.valueOf((fecha.getMonth()+1));
            }
            String s = (fecha.getYear()+1900)+"-"+mes+"-"+dia;
            registro.put("modificacion", s);

            // los inserto en la base de datos
            if(b){
                db.update("cronograma_joven",registro,"_id="+i,null);
            }else{
                db.insert("cronograma_joven", null, registro);
            }
            i++;
        }
        db.close();
    }

    public Date getModificacion() {
        SQLiteDatabase db = cronJoven.getWritableDatabase();
        Cursor fila = db.rawQuery("select modificacion from cronograma_joven", null);

        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = null;
            String parsefecha = new String(fila.getString(0));
            try {

                fecha = formatoDelTexto.parse(parsefecha);

            } catch (ParseException ex) {

                ex.printStackTrace();

            }
            db.close();
            return fecha;
        }
    }
}
