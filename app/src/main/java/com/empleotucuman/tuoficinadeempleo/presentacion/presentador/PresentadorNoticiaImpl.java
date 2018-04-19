package com.empleotucuman.tuoficinadeempleo.presentacion.presentador;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.empleotucuman.tuoficinadeempleo.dominio.Noticia;
import com.empleotucuman.tuoficinadeempleo.persistencia.PersisNoticias;
import com.empleotucuman.tuoficinadeempleo.presentacion.vistas.ViewFragment;

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
