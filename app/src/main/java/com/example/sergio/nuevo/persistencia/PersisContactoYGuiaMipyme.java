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
    private DBTuOficinaDeEmpleo contactosPagina;


    public PersisContactoYGuiaMipyme(Activity activity) {
        contactosPagina = new DBTuOficinaDeEmpleo(activity,"DBTuOficinaDeEmpleo",null,1);
    }

    public String levantarNoticias() {
        SQLiteDatabase db = contactosPagina.getWritableDatabase();
        Cursor fila = db.rawQuery("select * from contactos_syme", null);
        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{

            String contactpag = new String (fila.getString(1));
            db.close();
            return contactpag;
        }

    }

    public void guardarNoticias(String contactpag) {
        if(contactpag != null){
            SQLiteDatabase db = contactosPagina.getWritableDatabase();
            ContentValues registro = new ContentValues();
            Cursor fila = db.rawQuery("select * from contactos_syme", null);

            boolean b;
            if(!fila.moveToFirst()){
                b=false;
            }else{
                b=true;
            }
            registro.put("contenido",contactpag);
            if(b){
                db.update("contactos_syme",registro,"_id=1",null);
            }else{
                db.insert("contactos_syme", null, registro);
            }
            db.close();
        }
    }
}
