package com.example.sergio.nuevo.aplicacion.adaptadores;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.Noticia;
import com.example.sergio.nuevo.vistas.FragmentWebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operador1 on 25/08/2017.
 */

public class AdaptadorNoticia extends ArrayAdapter<List<List>> {
    private Activity activity;
    ArrayList<Noticia> noticias;

    public AdaptadorNoticia(@NonNull Activity activity, @NonNull ArrayList<Noticia> noticias) {
        super(activity, R.layout.noticia);
        this.noticias = noticias;
        this.activity = activity;
    }
    static class ViewHolder {
        protected ImageView image;
        protected TextView parrafo;
        protected TextView titulo;
        protected Button vermas;
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
        viewHolder.vermas = (Button)view.findViewById(R.id.btnLeerMas);

        // importante!!! establecemos el mensaje
        viewHolder.titulo.setText(noticias.get(position).getTitulo());
        viewHolder.image.setImageBitmap((Bitmap)noticias.get(position).getFoto());
        viewHolder.parrafo.setText(noticias.get(position).getParrafo());
        viewHolder.vermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentWebView fweb = new FragmentWebView();
                android.app.FragmentManager m = activity.getFragmentManager();
                m.beginTransaction().replace(R.id.contenedor, fweb).commit();
            }
        });

        return view;
    }

}
