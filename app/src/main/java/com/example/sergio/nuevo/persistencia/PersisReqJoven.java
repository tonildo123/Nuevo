package com.example.sergio.nuevo.persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.sergio.nuevo.dominio.Noticia;
import com.example.sergio.nuevo.dominio.ProgramaJoven;
import com.example.sergio.nuevo.dominio.h3;
import com.example.sergio.nuevo.dominio.li;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operador1 on 05/09/2017.
 */

public class PersisReqJoven {
    private DBTuOficinaDeEmpleo not;


    public PersisReqJoven(Activity activity) {
        not = new DBTuOficinaDeEmpleo(activity,"DBTuOficinaDeEmpleo",null,1);
    }

    public ProgramaJoven levantarNoticias() {
        SQLiteDatabase db = not.getWritableDatabase();
        Cursor fila = db.rawQuery("select r.objetivo as Objetivo,r.titulo as Titulo,r.urlimagen as url,r.dirImagen as dir,h3.subtitulo as Subtitulo,li.item as Item " +
                                  "from requisitos_joven AS r " +
                                  "INNER JOIN h3 ON h3.id_requisitos_joven=r._id " +
                                  "INNER JOIN li ON li.id_h3=h3._id", null);
        int i = 1;
        int j = 1;
        ArrayList<h3> subtitulo = new ArrayList<>();
        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{
            Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + fila.getString(3));
            ProgramaJoven joven = new ProgramaJoven(fila.getString(2),fila.getString(1),fila.getString(0),bitmap);
            joven.setDirimagen(fila.getString(3));
            String s = fila.getString(4);
            subtitulo.add(new h3(j,s));
            do{
                if (s.equals(fila.getString(4))){
                    subtitulo.get(subtitulo.size()-1).getItem().add(new li(i,fila.getString(5)));
                    i++;
                }else {
                    j++;
                    s = fila.getString(4);
                    subtitulo.add(new h3(j,s));
                    subtitulo.get(subtitulo.size()-1).getItem().add(new li(i,fila.getString(5)));
                    i=1;
                }
            }while (fila.moveToNext());
            joven.setH3(subtitulo);
            System.out.println("");
            return joven;
        }

    }

    public void guardarNoticias(ProgramaJoven novedad) {
        SQLiteDatabase db = not.getWritableDatabase();
        ContentValues registro = new ContentValues();
        int i = 1;

        File myPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/SSE/tmp/"+novedad.getTitulo()+".jpg");

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(myPath);
            novedad.getImg().compress(Bitmap.CompressFormat.JPEG, 10, fos);
            fos.flush();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        for (h3 h3:novedad.getH3()) {
            registro.put("_id",i);
            registro.put("subtitulo",h3.getSubtitulo());
            registro.put("id_requisitos_joven", 1);
            db.insert("h3", null, registro);
            for (li list:h3.getItem()) {
                registro.clear();
                registro.put("item",list.getItem());
                registro.put("id_h3",i);
                db.insert("li", null, registro);
            }
            i++;
            registro.clear();
        }
        registro.put("_id",1);
        registro.put("objetivo",novedad.getObjetivo());
        registro.put("titulo",novedad.getTitulo());
        registro.put("urlimagen",novedad.getUrlimagen());
        registro.put("dirImagen","/SSE/tmp/"+novedad.getTitulo()+".jpg");
        db.insert("requisitos_joven", null, registro);
        db.update("requisitos_joven",registro,"where",new String[]{"id =1"});
        db.close();
    }
}
