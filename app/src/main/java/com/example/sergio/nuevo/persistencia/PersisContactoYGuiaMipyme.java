package com.example.sergio.nuevo.persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.sergio.nuevo.dominio.Programa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Operador1 on 05/09/2017.
 */

public class PersisContactoYGuiaMipyme {
    private DBTuOficinaDeEmpleo contactosyGuia;


    public PersisContactoYGuiaMipyme(Activity activity) {
        contactosyGuia = new DBTuOficinaDeEmpleo(activity,"DBTuOficinaDeEmpleo",null,1);
    }

    public String levantar(String nombre) {
        SQLiteDatabase db = contactosyGuia.getWritableDatabase();
        Cursor fila = db.rawQuery("select * from " + nombre, null);
        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{

            String contactGuia = new String (fila.getString(1));
            db.close();
            return contactGuia;
        }

    }

    public void guardar(String nombre ,String contactGuia) {
        if(contactGuia != null){
            SQLiteDatabase db = contactosyGuia.getWritableDatabase();
            ContentValues registro = new ContentValues();
            Cursor fila = db.rawQuery("select * from "+ nombre, null);


            boolean b;
            if(!fila.moveToFirst()){
                b=false;
            }else{
                b=true;
            }
            registro.put("contenido",contactGuia);
            if(b){
                db.update(nombre,registro,"_id=1",null);
            }else{
                db.insert(nombre, null, registro);
            }
            db.close();
        }
    }
}
