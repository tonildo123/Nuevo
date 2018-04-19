package com.empleotucuman.tuoficinadeempleo.persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.empleotucuman.tuoficinadeempleo.dominio.Programa;

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

public class PersisRequisitos {
    private DBTuOficinaDeEmpleo persisrequisitos;


    public PersisRequisitos(Activity activity) {
        persisrequisitos = new DBTuOficinaDeEmpleo(activity,"DBTuOficinaDeEmpleo",null,1);
    }

    public Programa levantar(String nombre) {
        SQLiteDatabase db = persisrequisitos.getWritableDatabase();
        Cursor fila = db.rawQuery("select * from "+nombre, null);
        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{
            Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + fila.getString(4));
            Programa joven = new Programa(fila.getString(3),fila.getString(2),bitmap,fila.getString(1));
            joven.setDirimagen(fila.getString(4));
            db.close();
            return joven;
        }
    }

    public void guardar(Programa programa,String nombre) {
        if(programa != null){
            SQLiteDatabase db = persisrequisitos.getWritableDatabase();
            ContentValues registro = new ContentValues();
            Cursor fila = db.rawQuery("select * from "+nombre, null);

            boolean b;
            if(!fila.moveToFirst(   )){
                b=false;
            }else{
                b=true;
            }

            File myPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/SSE/tmp/"+programa.getTitulo()+".jpg");

            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(myPath);
                programa.getImg().compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
            }catch (FileNotFoundException ex){
                ex.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            }
            registro.put("contenido",programa.getContenido());
            registro.put("titulo",programa.getTitulo());
            registro.put("urlimagen",programa.getUrlimagen());
            registro.put("dirImagen","/SSE/tmp/"+programa.getTitulo()+".jpg");
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
        SQLiteDatabase db = persisrequisitos.getWritableDatabase();
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
