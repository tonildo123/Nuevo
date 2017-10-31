package com.example.sergio.nuevo.presentacion.presentador;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sergio.nuevo.dominio.Programa;
import com.example.sergio.nuevo.presentacion.vistas.MainView;

/**
 * Created by Operador1 on 31/10/2017.
 */

public interface PresentadorRequisitos extends IPresentador{

    FragmentStatePagerAdapter getViewPagerAdapter(FragmentManager fragmentManager);
    void setVista(MainView vista);
    Programa getRequisito();
}
