package com.example.sergio.nuevo.persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

public class PersisRequisitos extends SQLiteOpenHelper{
    private String sqlCreateReqJoven   = "CREATE TABLE requisitos_joven(_id INTEGER PRIMARY KEY, contenido TEXT,titulo TEXT,urlimagen TEXT,dirImagen TEXT)";
    private String sqlCreateReqProg    = "CREATE TABLE requisitos_progresar(_id INTEGER PRIMARY KEY, contenido TEXT,titulo TEXT,urlimagen TEXT,dirImagen TEXT)";


    public PersisRequisitos(Activity activity) {
        super(activity,"DBTuOficinaDeEmpleo",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateReqJoven);
        db.execSQL(sqlCreateReqProg);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Programa levantar(String nombre) {
        SQLiteDatabase db = getWritableDatabase();
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
            SQLiteDatabase db = getWritableDatabase();
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
}
