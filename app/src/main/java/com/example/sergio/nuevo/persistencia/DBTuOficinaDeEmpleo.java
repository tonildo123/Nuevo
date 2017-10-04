package com.example.sergio.nuevo.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Operador1 on 30/08/2017.
 */

public class DBTuOficinaDeEmpleo extends SQLiteOpenHelper {
    private String sqlCreateNoticias = "CREATE TABLE noticias(_id INTEGER PRIMARY KEY, titulo TEXT,urlimagen TEXT, dirImagen TEXT, urlparrafo TEXT, parrafo TEXT)";
    private String sqlCreateCronProg = "CREATE TABLE cronograma_progresar(_id INTEGER PRIMARY KEY,terminacionDni TEXT,fecha TEXT)";
    private String sqlCreateCronJoven = "CREATE TABLE cronograma_joven(_id INTEGER PRIMARY KEY, terminacionDni TEXT,fecha TEXT)";
    private String sqlCreateReqJoven = "CREATE TABLE requisitos_joven(_id INTEGER PRIMARY KEY, contenido TEXT,titulo TEXT,urlimagen TEXT,dirImagen TEXT)";
    private String sqlCreateReqProg = "CREATE TABLE requisitos_progresar(_id INTEGER PRIMARY KEY, contenido TEXT,titulo TEXT,urlimagen TEXT,dirImagen TEXT)";
    private String sqlCreateContacto = "CREATE TABLE contactos_syme(_id INTEGER PRIMARY KEY, contenido TEXT)";
    public DBTuOficinaDeEmpleo(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateNoticias);
        db.execSQL(sqlCreateCronProg);
        db.execSQL(sqlCreateCronJoven);
        db.execSQL(sqlCreateReqJoven);
        db.execSQL(sqlCreateReqProg);
        db.execSQL(sqlCreateContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
