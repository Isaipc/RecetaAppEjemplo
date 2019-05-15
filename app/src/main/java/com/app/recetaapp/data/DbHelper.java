package com.app.recetaapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.recetaapp.POJO.Receta;
import com.app.recetaapp.data.RecetaContract.RecetaEntry;
import com.app.recetaapp.data.RecetaContract;

public class DbHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "Receta.db";
    public static final int DATABASE_VERSION = 1;


    public DbHelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( RecetaContract.SQL_CREATE_RECETA_TABLE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( RecetaContract.SQL_DELETE_RECETA_TABLE );
        onCreate( db );
    }


}
