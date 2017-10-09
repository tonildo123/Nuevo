package com.example.sergio.nuevo.persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.sergio.nuevo.dominio.Noticia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Operador1 on 31/08/2017.
 */

public class PersisNoticias extends SQLiteOpenHelper{
    private String sqlCreateNoticias   = "CREATE TABLE noticias(_id INTEGER PRIMARY KEY, titulo TEXT,urlimagen TEXT, dirImagen TEXT, urlparrafo TEXT, parrafo TEXT)";


    public PersisNoticias(Activity activity) {
        super(activity,"DBTuOficinaDeEmpleo",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateNoticias);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList levantar() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor fila = db.rawQuery("select * from noticias", null);
        ArrayList<Noticia> noticias = new ArrayList<>();

        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{
            Bitmap bit;
            String s = fila.getString(3);
            bit = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + fila.getString(3));
            noticias.add(new Noticia(fila.getInt(0),fila.getString(1),fila.getString(2),bit,fila.getString(4),fila.getString(5)));
            while (fila.moveToNext()) {
                bit = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + fila.getString(3));
                noticias.add(new Noticia(fila.getInt(0),fila.getString(1),fila.getString(2),bit,fila.getString(4),fila.getString(5)));
            }
            db.close();
            return  noticias;
        }
    }

    public void guardar(ArrayList<Noticia> novedades) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues registro = new ContentValues();
        Cursor fila = db.rawQuery("select * from noticias", null);

        boolean b;
        if(!fila.moveToFirst()){
            b=false;
        }else{
            b=true;
        }

        for (Noticia novedad : novedades){
            File myPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/SSE/images/"+novedad.getId()+".jpg");

            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(myPath);
                if(novedad.getFoto() != null){
                    novedad.getFoto().compress(Bitmap.CompressFormat.JPEG, 10, fos);
                }
                fos.flush();
            }catch (FileNotFoundException ex){
                ex.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            }
            registro.put("titulo", novedad.getTitulo());
            registro.put("urlimagen", novedad.getUrlImagen());
            registro.put("dirImagen", "/SSE/images/"+novedad.getId()+".jpg");
            registro.put("urlparrafo", novedad.getUrlParrafo());
            registro.put("parrafo", novedad.getParrafo());
            if(b){
                db.update("noticias",registro,"_id="+novedad.getId(),null);
            }else {
                // los inserto en la base de datos
                db.insert("noticias", null, registro);
            }
        }
        db.close();
    }
}
