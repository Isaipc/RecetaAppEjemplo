package com.app.recetaapp.adapter;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.recetaapp.POJO.Receta;
import com.app.recetaapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter < RecetaAdapter.ViewHolder >
{

    private Context context;
    public List <Receta> recetaList;

    public RecetaAdapter( Context context, List<Receta> recetaList  ) {
        this.context = context;
        this.recetaList = recetaList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from( viewGroup.getContext() )
                .inflate( R.layout.item_receta, viewGroup, false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        viewHolder.receta = recetaList.get( i );
        viewHolder.nombreView.setText( recetaList.get( i ).nombre );
        viewHolder.personasView.setText("Para "+ recetaList.get( i ).personas  + " personas");
        viewHolder.descripcionView.setText( recetaList.get( i ).descripcion);
        viewHolder.preparacionView.setText( recetaList.get( i ).preparacion);

        Glide.with( viewHolder.itemView.getContext())
                .load( viewHolder.receta.image )
                .thumbnail( 0.1f )
                .centerCrop()
                .into( viewHolder.imageView );

        switch(viewHolder.receta.fav)
        {
            case 0:
                viewHolder.favView.setImageResource(R.drawable.baseline_favorite_24 );
                break;
            case 1:
                viewHolder.favView.setImageResource(R.drawable.baseline_favorite_border_24 );
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(recetaList == null)
            return 0;
        return recetaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView imageView;
        ImageView favView;

        TextView nombreView;
        TextView personasView;
        TextView descripcionView;
        TextView preparacionView;

        Receta receta;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            cardView = itemView.findViewById( R.id.item_card);
            imageView = itemView.findViewById( R.id.image_receta);
            nombreView = itemView.findViewById( R.id.nombre_receta);
            personasView= itemView.findViewById( R.id.personas_receta);
            descripcionView = itemView.findViewById( R.id.descripcion_receta);
            preparacionView = itemView.findViewById( R.id.preparacion_receta);
            favView = itemView.findViewById( R.id.fav_receta );
        }
    }
}
