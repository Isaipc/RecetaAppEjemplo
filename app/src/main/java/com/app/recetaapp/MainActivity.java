package com.app.recetaapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.recetaapp.POJO.Receta;
import com.app.recetaapp.adapter.RecetaAdapter;
import com.app.recetaapp.data.DatabaseOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerRecetas;
    RecetaAdapter recetaAdapter;
    DatabaseOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        ///CREATEING RECYCLER ...
        recyclerRecetas = findViewById( R.id.recycler_recetas );

        ///SETING A LINEAR LAYOUT IN RECYCLER ...
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerRecetas.setLayoutManager( linearLayoutManager );


        ///GETTING DATABASE...
        //getApplicationContext().deleteDatabase( "Receta.db" );
        dbOperations = DatabaseOperations.getInstance( getApplicationContext() );

        ///CREATNG DATA SAMPLE ...
        dbOperations.createSampleData();

        ///CREATING AND SETTING ADAPTER FOR RECETAS ...
        updateAdapter();

        ///CREATING FAB FOR MAIN ACTIVITY
        FloatingActionButton fab = findViewById( R.id.fab );

        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this,
                        NuevaRecetaActivity.class );
                startActivity( intent );
            }
        } );

        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(
                    @NonNull RecyclerView recyclerView,
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    int i) {

                int position = viewHolder.getAdapterPosition();
                RecetaAdapter recetaAdapter = (RecetaAdapter) recyclerRecetas.getAdapter();
                assert recetaAdapter != null;
                if(dbOperations.deleteReceta( recetaAdapter.recetaList.get( position ).id ))
                {
                    Toast.makeText(
                            MainActivity.this,
                            "Se ha borrado exitosamente",
                            Toast.LENGTH_LONG).show();
                    updateAdapter();
                }
                else
                    Toast.makeText(
                            MainActivity.this,
                            "Ha ocurrido un error",
                            Toast.LENGTH_LONG).show();

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper( simpleCallback );
        itemTouchHelper.attachToRecyclerView( recyclerRecetas );
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }

    protected void updateAdapter()
    {
        recetaAdapter = new RecetaAdapter( this, dbOperations.getListRecetas() );
        recyclerRecetas.setAdapter( recetaAdapter );
    }
}
