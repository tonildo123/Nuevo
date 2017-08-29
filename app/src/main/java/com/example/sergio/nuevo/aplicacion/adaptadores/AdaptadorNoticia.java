package com.example.sergio.nuevo.aplicacion.adaptadores;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.nuevo.R;

import java.util.List;

/**
 * Created by Operador1 on 25/08/2017.
 */

public class AdaptadorNoticia extends ArrayAdapter<List<List>> {
    private Activity activity;
    List<List> noticias;

    public AdaptadorNoticia(@NonNull Activity activity, @NonNull List<List> noticias) {
        super(activity, R.layout.noticia);
        this.noticias = noticias;
        this.activity = activity;
    }
    static class ViewHolder {
        protected ImageView image;
        protected TextView parrafo;
        protected TextView titulo;
    }

    public int getCount() {
        return noticias.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView,
                        final ViewGroup parent) {
        // inflamos nuestra vista con el layout
        View view = null;
        LayoutInflater inflator = activity.getLayoutInflater();
        view = inflator.inflate(R.layout.noticia, null);
        final ViewHolder viewHolder = new ViewHolder();

        // *** instanciamos a los recursos
        viewHolder.image = (ImageView) view.findViewById(R.id.imagen);
        viewHolder.parrafo = (TextView) view
                .findViewById(R.id.parrafo);
        viewHolder.titulo = (TextView)view.findViewById(R.id.titulo);

        // importante!!! establecemos el mensaje
        viewHolder.titulo.setText(noticias.get(position).get(2).toString());
        viewHolder.image.setImageBitmap((Bitmap)noticias.get(position).get(0));
        viewHolder.parrafo.setText(noticias.get(position).get(1).toString());

        return view;
    }

}
