package com.empleotucuman.tuoficinadeempleo.presentacion.presentador;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.empleotucuman.tuoficinadeempleo.dominio.Programa;
import com.empleotucuman.tuoficinadeempleo.presentacion.vistas.MainView;

/**
 * Created by Operador1 on 31/10/2017.
 */

public interface PresentadorRequisitos extends IPresentador{

    FragmentStatePagerAdapter getViewPagerAdapter(FragmentManager fragmentManager);
    void setVista(MainView vista);
    Programa getRequisito();
}
