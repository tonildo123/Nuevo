package com.example.sergio.nuevo.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Operador1 on 30/08/2017.
 */

public class DBCronProg extends SQLiteOpenHelper {
    private String sqlCreate = "CREATE TABLE cronograma_progresar(_id INTEGER PRIMARY KEY, terminacionDni INTEGER,fecha TEXT)";

    public DBCronProg(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
