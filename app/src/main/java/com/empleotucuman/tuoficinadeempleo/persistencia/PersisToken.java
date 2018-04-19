package com.empleotucuman.tuoficinadeempleo.persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.empleotucuman.tuoficinadeempleo.dominio.CronogramaJoven;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Operador1 on 04/09/2017.
 */

public class PersisToken {
    private DBTuOficinaDeEmpleo token;
    private String Stoken;


    public PersisToken(Activity activity) {
        token = new DBTuOficinaDeEmpleo(activity,"DBTuOficinaDeEmpleo",null,1);
    }
    public PersisToken(Context context){
        token = new DBTuOficinaDeEmpleo(context,"DBTuOficinaDeEmpleo",null,1);
    }

    public String levantar() {
        SQLiteDatabase db = token.getWritableDatabase();
        Cursor fila = db.rawQuery("select * from token", null);

        if(!fila.moveToFirst()){
            db.close();
        }else{
            Stoken = fila.getString(1);
            db.close();
            return  Stoken;
        }
        return null;
    }

    public void guardar(String Rtoken) {
        SQLiteDatabase db = token.getWritableDatabase();
        ContentValues registro = new ContentValues();
        Cursor fila = db.rawQuery("select * from token", null);

        boolean b;
        if(!fila.moveToFirst()){
            b=false;
        }else{
            b=true;
        }

        registro.put("token", Rtoken);
        if(b){
            db.update("token",registro,"_id=1",null);
        }else{
            db.insert("token", null, registro);
        }
        db.close();
    }
}
