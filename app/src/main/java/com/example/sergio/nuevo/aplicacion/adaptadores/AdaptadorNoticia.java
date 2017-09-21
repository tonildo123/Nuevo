package com.example.sergio.nuevo.aplicacion.adaptadores;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.Noticia;
import com.example.sergio.nuevo.vistas.NoticiaWebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operador1 on 25/08/2017.
 */

public class AdaptadorNoticia extends ArrayAdapter<List<List>> {
    private Activity activity;
    private Context mainactivity;
    ArrayList<Noticia> noticias;

    public AdaptadorNoticia(@NonNull Activity activity, @NonNull ArrayList<Noticia> noticias,Context mainActivity) {
        super(activity, R.layout.noticia);
        this.noticias = noticias;
        this.activity = activity;
        this.mainactivity = mainActivity;
    }
    static class ViewHolder {
        protected ImageView image;
        protected TextView parrafo;
        protected TextView titulo;
        protected Button vermas;
        protected LinearLayout layoutAnimado;
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
        viewHolder.layoutAnimado = (LinearLayout)view.findViewById(R.id.animado);

        // importante!!! establecemos el mensaje
        viewHolder.titulo.setText(noticias.get(position).getTitulo());
        viewHolder.image.setImageBitmap((Bitmap)noticias.get(position).getFoto());
        viewHolder.parrafo.setText(noticias.get(position).getParrafo());
        viewHolder.vermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoticiaWebView fweb = new NoticiaWebView();
                fweb.setUrl(noticias.get(position).getUrlParrafo());
                Intent pasar = new Intent(mainactivity.getApplicationContext(), fweb.getClass());
                pasar.putExtra("url", noticias.get(position).getUrlParrafo());
                mainactivity.startActivity(pasar);
            }
        });
        if(view.getScrollY() == 0){
            if (position % 2 == 0) {
                animar(true,viewHolder,(float)-1.0);
            }else{
                animar(true,viewHolder,(float)1.0);
            }

        }
        return view;
    }

    private void animar(boolean mostrar,ViewHolder viewHolder,float i) {
        AnimationSet set = new AnimationSet(true);
        Animation animation = null;
        if (mostrar)
        {
            //desde la esquina inferior derecha a la superior izquierda
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, i, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        }
        //duración en milisegundos
        animation.setDuration(400);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);

        viewHolder.layoutAnimado.setLayoutAnimation(controller);
        viewHolder.layoutAnimado.startAnimation(animation);
    }

}
