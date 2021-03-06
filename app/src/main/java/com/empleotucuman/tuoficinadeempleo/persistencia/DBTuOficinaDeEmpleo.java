package com.empleotucuman.tuoficinadeempleo.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Operador1 on 30/08/2017.
 */

public class DBTuOficinaDeEmpleo extends SQLiteOpenHelper {
    private String sqlCreateNoticias          = "CREATE TABLE noticias(_id INTEGER PRIMARY KEY, titulo TEXT,urlimagen TEXT, dirImagen TEXT, urlparrafo TEXT, parrafo TEXT)";
    private String sqlCreateCronProg          = "CREATE TABLE cronograma_progresar(_id INTEGER PRIMARY KEY,terminacionDni TEXT,fecha TEXT,modificacion TEXT)";
    private String sqlCreateCronJoven         = "CREATE TABLE cronograma_joven(_id INTEGER PRIMARY KEY,terminacionDni TEXT,fecha TEXT,modificacion TEXT)";
    private String sqlCreateReqJoven          = "CREATE TABLE requisitos_joven(_id INTEGER PRIMARY KEY,contenido TEXT,titulo TEXT,urlimagen TEXT,dirImagen TEXT,modificacion TEXT)";
    private String sqlCreateReqProg           = "CREATE TABLE requisitos_progresar(_id INTEGER PRIMARY KEY,contenido TEXT,titulo TEXT,urlimagen TEXT,dirImagen TEXT,modificacion TEXT)";
    private String sqlCreateContacto          = "CREATE TABLE contactos_syme(_id INTEGER PRIMARY KEY,contenido TEXT,modificacion TEXT)";
    private String sqlCreateGuiaMipyme        = "CREATE TABLE guiamipyme_syme(_id INTEGER PRIMARY KEY,contenido TEXT,modificacion TEXT)";
    private String sqlCreateOfertaLaboral     = "CREATE TABLE oferta_laboral(_id INTEGER PRIMARY KEY,puesto TEXT, localidad TEXT,urlimagen, requisitos TEXT,modificacion TEXT)";
    private String sqlCreateCursoCapacitacion = "CREATE TABLE curso_capacitacion(_id INTEGER PRIMARY KEY,tematica TEXT,urlimagen TEXT, modificacion TEXT)";
    private String sqlCreateToken             = "CREATE TABLE token(_id INTEGER PRIMARY KEY,token TEXT)";

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
        db.execSQL(sqlCreateGuiaMipyme);
        db.execSQL(sqlCreateOfertaLaboral);
        db.execSQL(sqlCreateCursoCapacitacion);
        db.execSQL(sqlCreateToken);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
