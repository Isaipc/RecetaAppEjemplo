package com.app.recetaapp.POJO;

import android.content.ContentValues;

import com.app.recetaapp.data.RecetaContract.RecetaEntry;

import java.util.UUID;

public class Receta
{

    public String id;
    public String nombre;
    public int personas;
    public String descripcion;
    public String preparacion;
    public String image;
    public int fav;


    public Receta()
    {
        this.id = UUID.randomUUID().toString();
    }

    public Receta(
            String nombre,
            int personas,
            String descripcion,
            String preparacion,
            String image,
            int fav) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.personas = personas;
        this.descripcion = descripcion;
        this.preparacion = preparacion;
        this.image = image;
        this.fav = fav;
    }

    public ContentValues toContentValues()
    {
        ContentValues values = new ContentValues(  );
        values.put( RecetaEntry.COLUMN_ID, id );
        values.put( RecetaEntry.COLUMN_NOMBRE, nombre);
        values.put( RecetaEntry.COLUMN_PERSONAS, personas);
        values.put( RecetaEntry.COLUMN_DESCRIPCION, descripcion);
        values.put( RecetaEntry.COLUMN_PREPARACION, preparacion);
        values.put( RecetaEntry.COLUMN_IMAGE, image );
        values.put( RecetaEntry.COLUMN_FAV, fav);
        return values;
    }
}

