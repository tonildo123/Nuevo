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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            if(b){
                db.update(nombre,registro,"_id=1",null);
            }else{
                db.insert(nombre, null, registro);
            }
            db.close();
        }
    }
    public Date getModificacion(String nombre){
        SQLiteDatabase db = contactosyGuia.getWritableDatabase();
        Cursor fila = db.rawQuery("select modificacion from " + nombre, null);

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
