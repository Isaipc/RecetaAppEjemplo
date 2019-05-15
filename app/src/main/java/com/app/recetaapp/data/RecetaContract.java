package com.app.recetaapp.data;

import android.provider.BaseColumns;

import com.app.recetaapp.POJO.Receta;

public class RecetaContract
{

    public static class RecetaEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "receta";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_PERSONAS = "personas";
        public static final String COLUMN_DESCRIPCION = "descripcion";
        public static final String COLUMN_PREPARACION = "preparacion";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_FAV = "fav";
    }

    public static final String SQL_CREATE_RECETA_TABLE =
            "CREATE TABLE " + RecetaEntry.TABLE_NAME + " ( " +
                    RecetaEntry.COLUMN_ID + " TEXT PRIMARY KEY NOT NULL, " +
                    RecetaEntry.COLUMN_NOMBRE + " TEXT NOT NULL, " +
                    RecetaEntry.COLUMN_PERSONAS + " INTEGER NOT NULL, " +
                    RecetaEntry.COLUMN_DESCRIPCION + " TEXT NOT NULL, " +
                    RecetaEntry.COLUMN_PREPARACION + " TEXT NOT NULL, " +
                    RecetaEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +
                    RecetaEntry.COLUMN_FAV + " TEXT NOT NULL) ";

    public static final String SQL_DELETE_RECETA_TABLE =
            "DROP TABLE IF EXISTS " + RecetaEntry.TABLE_NAME;


    public static final String SQL_FILTER_QUERY_RECETA =
            " (" + RecetaEntry.COLUMN_ID + " = ? OR " + RecetaEntry.COLUMN_ID + " = '') AND " +
            " (" + RecetaEntry.COLUMN_NOMBRE  + " = ? OR " + RecetaEntry.COLUMN_NOMBRE + " = '') AND " +
            " (" + RecetaEntry.COLUMN_PERSONAS  + " = ? OR " + RecetaEntry.COLUMN_PERSONAS + " = 0) AND " +
            " (" + RecetaEntry.COLUMN_DESCRIPCION  + " = ? OR " + RecetaEntry.COLUMN_DESCRIPCION + " = '') AND " +
            " (" + RecetaEntry.COLUMN_PREPARACION + " = ? OR " + RecetaEntry.COLUMN_PREPARACION + " = '') AND " +
            " (" + RecetaEntry.COLUMN_IMAGE + " = ? OR " + RecetaEntry.COLUMN_IMAGE + " = '') AND " +
            " (" + RecetaEntry.COLUMN_FAV + " = ? OR " + RecetaEntry.COLUMN_FAV + " = 0)";

    public static final String [] SQL_PROJECTION_RECETA = {
            RecetaEntry.COLUMN_ID,
            RecetaEntry.COLUMN_NOMBRE,
            RecetaEntry.COLUMN_PERSONAS,
            RecetaEntry.COLUMN_DESCRIPCION,
            RecetaEntry.COLUMN_PREPARACION,
            RecetaEntry.COLUMN_IMAGE,
            RecetaEntry.COLUMN_FAV
        };


}
