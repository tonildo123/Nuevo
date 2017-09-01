package com.example.sergio.nuevo.aplicacion.servicios;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sergio.nuevo.dominio.Noticia;
import com.example.sergio.nuevo.persistencia.DBNoticias;

import java.util.ArrayList;

/**
 * Created by Operador1 on 31/08/2017.
 */

public class ServicioNoticias {
    private DBNoticias not;


    public ServicioNoticias(Activity activity) {
        not = new DBNoticias(activity,"DBTuOficinaDeEmpleo",null,1);
    }

    public ArrayList levantarNoticias() {
        SQLiteDatabase db = not.getWritableDatabase();
        Cursor fila = db.rawQuery("select * from noticias", null);

        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{
            ArrayList<Noticia> noticias = new ArrayList<>();



            return  noticias;
        }
    }

    public void guardarNoticias(ArrayList<Noticia> novedades) {
        SQLiteDatabase db = not.getWritableDatabase();
        ContentValues registro = new ContentValues();

        for (Noticia novedad : novedades){
            registro.put("titulo", novedad.getTitulo());
            registro.put("urlimagen", novedad.getUrlImagen());
            registro.put("dirImagen", "");
            registro.put("urlparrafo", novedad.getUrlParrafo());
            registro.put("parrafo", novedad.getParrafo());

            // los inserto en la base de datos
            db.insert("noticias", null, registro);
        }


        db.close();
    }
}
