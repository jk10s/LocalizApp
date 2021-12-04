package com.example.proyectociclo4.clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteConex extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "misionticv11.db";

    public SqliteConex(@Nullable Context c)
    {
        super(c, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombres VARCHAR (150) NOT NULL, apellidos VARCHAR (150) NOT NULL, email VARCHAR (120) NOT NULL, clave VARCHAR (8) NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE sitios (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre VARCHAR (80) NOT NULL, descripcion TEXT NOT NULL, tipo VARCHAR (20) NOT NULL, latitud DECIMAL (3, 9) NOT NULL, longitud DECIMAL (3, 9) NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE usuarios");
        sqLiteDatabase.execSQL("DROP TABLE sitios");
        onCreate(sqLiteDatabase);
    }
}
