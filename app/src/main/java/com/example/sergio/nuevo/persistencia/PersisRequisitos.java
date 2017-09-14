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

public class PersisRequisitos {
    private DBTuOficinaDeEmpleo not;


    public PersisRequisitos(Activity activity) {
        not = new DBTuOficinaDeEmpleo(activity,"DBTuOficinaDeEmpleo",null,1);
    }

    public Programa levantarNoticias(String nombre) {
        SQLiteDatabase db = not.getWritableDatabase();
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

    public void guardarNoticias(Programa programa,String nombre) {
        SQLiteDatabase db = not.getWritableDatabase();
        ContentValues registro = new ContentValues();
        Cursor fila = db.rawQuery("select * from "+nombre, null);

        boolean b;
        if(!fila.moveToFirst()){
            b=false;
        }else{
            b=true;
        }

        File myPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/SSE/tmp/"+programa.getTitulo()+".jpg");

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(myPath);
            programa.getImg().compress(Bitmap.CompressFormat.JPEG, 10, fos);
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
        if(b){
            db.update(nombre,registro,"_id=1",null);
        }else{
            db.insert(nombre, null, registro);
        }
        db.close();
    }
}
