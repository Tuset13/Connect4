package com.example.connect4.DDBB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PartidasSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Partidas " +
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "alias TEXT, " +
                        "date TEXT, " +
                        "grillSize TEXT, " +
                        "timeControl INTEGER, " +
                        "usedTime INTEGER, " +
                        "result TEXT)";
    public PartidasSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Partidas");
        db.execSQL(sqlCreate);
    }
}
