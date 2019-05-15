package com.app.recetaapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.recetaapp.POJO.Receta;
import com.app.recetaapp.data.DatabaseOperations;

import java.sql.SQLException;

public class NuevaRecetaActivity extends AppCompatActivity {

    private Context context;
    private EditText nombreEditText;
    private EditText personasEditText;
    private EditText descripcionEditText;
    private EditText preparacionEditText;
    private EditText favEditText;

    private TextInputLayout nombreLayout;
    private TextInputLayout personasLayout;
    private TextInputLayout descripcionLayout;
    private TextInputLayout preparacionLayout;
    private TextInputLayout favLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_nueva_receta );

        nombreEditText = findViewById( R.id.text_nombre);
        personasEditText = findViewById( R.id.text_personas);
        descripcionEditText = findViewById( R.id.text_descripcion);
        preparacionEditText = ((EditText) findViewById( R.id.text_preparacion));
        String image = "";
        favEditText = findViewById( R.id.text_fav);

        nombreLayout = findViewById( R.id.layout_nombre );
        personasLayout = findViewById( R.id.layout_personas );
        descripcionLayout = findViewById( R.id.layout_descripcion );
        preparacionLayout = findViewById( R.id.layout_preparacion );
        favLayout = findViewById( R.id.layout_fav );

        MaterialButton button = findViewById( R.id.add_button );


        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateFields())
                    return;
                addReceta();
            }
        } );

    }


    protected void addReceta()
    {
        String nombre = nombreEditText.getText().toString();
        int personas = Integer.parseInt(personasEditText.getText().toString());
        String descripcion = descripcionEditText.getText().toString();
        String preparacion = preparacionEditText.getText().toString();
        String image = "";
        int fav = Integer.parseInt(favEditText.getText().toString());

        Receta receta = new Receta();

        receta.nombre = nombre;
        receta.personas = personas;
        receta.descripcion = descripcion;
        receta.preparacion = preparacion;
        receta.image = image;
        receta.fav = fav;

        DatabaseOperations database = DatabaseOperations.getInstance( context );
        try
        {
            database.getDatabase().beginTransaction();
            if(database.insertReceta( receta ))
            {
                Toast.makeText( this, "Agregación exitosa", Toast.LENGTH_SHORT ).show();
                database.getDatabase().setTransactionSuccessful();
            }
            else
                Toast.makeText( this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();

        } finally {
            database.getDatabase().endTransaction();
        }

    }

    protected boolean validateFields()
    {
        if(nombreEditText != null && nombreEditText.getText().toString().isEmpty())
        {
            nombreLayout.setError( "¡Debe agregar el nombre!" );
            return false;
        }

        if(personasEditText != null && personasEditText.getText().toString().isEmpty())
        {
            personasLayout.setError( "¡Debe agregar la cantidad de personas!" );
            return false;
        }

        if(descripcionEditText != null && descripcionEditText.getText().toString().isEmpty())
        {
            descripcionLayout.setError( "¡Debe agregar la descripcion!" );
            return false;
        }

        if(preparacionEditText != null && preparacionEditText.getText().toString().isEmpty())
        {
            preparacionLayout.setError( "¡Debe agregar el modo de preparación!" );
            return false;
        }

        if(favEditText != null && favEditText.getText().toString().isEmpty())
        {
            favLayout.setError( "¡Debe asignar un num de favoritos!" );
            return false;
        }

        return true;
    }
}
