package com.example.sergio.nuevo.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Operador1 on 30/08/2017.
 */

public class DBNoticias extends SQLiteOpenHelper {
    private String sqlCreate = "CREATE TABLE noticias(_id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT,urlimagen TEXT, dirImagen TEXT, urlparrafo TEXT, parrafo TEXT)";

    public DBNoticias(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
