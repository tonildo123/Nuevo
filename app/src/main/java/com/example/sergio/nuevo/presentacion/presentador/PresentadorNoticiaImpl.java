package com.example.sergio.nuevo.presentacion.presentador;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorNoticia;
import com.example.sergio.nuevo.aplicacion.network.ServicioPagEmpleo;
import com.example.sergio.nuevo.aplicacion.patrones.Servicio;
import com.example.sergio.nuevo.dominio.Noticia;
import com.example.sergio.nuevo.persistencia.PersisNoticias;
import com.example.sergio.nuevo.presentacion.vistas.ViewFragment;

import java.util.ArrayList;

/**
 * Created by Operador1 on 18/10/2017.
 */

public class PresentadorNoticiaImpl implements IPresentador{
    private ViewFragment viewNoticia;
    private ArrayList<Noticia> noticias = new ArrayList<>();
    private PersisNoticias not;
    private SharedPreferences prefs;
    private static final String ACTIVITY_RESUMED = "activityResumed";

    public PresentadorNoticiaImpl(ViewFragment viewNoticia,Activity activity) {
        this.viewNoticia = viewNoticia;
        not = new PersisNoticias(activity);
        noticias = not.levantar();
        prefs = activity.getApplicationContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    @Override
    public void iniciar() {
        viewNoticia.setAdapter(noticias);
    }

    @Override
    public void onClick(Object[] o) {

    }

    @Override
    public void onResume() {
        prefs.edit().putBoolean(ACTIVITY_RESUMED, true).apply();
    }

    @Override
    public boolean onPause() {
        return prefs.getBoolean(ACTIVITY_RESUMED, false);
    }

    @Override
    public void onDestroy() {
        viewNoticia = null;
    }
}
