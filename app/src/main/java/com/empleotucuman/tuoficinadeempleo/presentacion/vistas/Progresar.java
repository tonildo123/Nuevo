package com.empleotucuman.tuoficinadeempleo.presentacion.vistas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.empleotucuman.tuoficinadeempleo.R;
import com.empleotucuman.tuoficinadeempleo.dominio.A;
import com.empleotucuman.tuoficinadeempleo.presentacion.presentador.PresentadorRequisitos;
import com.empleotucuman.tuoficinadeempleo.presentacion.presentador.PresentadorRequisitosImpl;


public class Progresar extends Fragment implements A,MainView {
    private AppBarLayout appBar;
    private TabLayout tabsRequisitos;
    private ViewPager viewPagerRequisitos;
    private PresentadorRequisitos presentadorRequisitos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_requisitos, container, false);

        View contenedor = (View)container.getParent();
        appBar = (AppBarLayout) contenedor.findViewById(R.id.appbar);
        // returna la vista del fragmento asociado
        tabsRequisitos= new TabLayout(getActivity());
        tabsRequisitos.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        tabsRequisitos.setBackgroundColor(Color.parseColor("#42a5f5"));
        tabsRequisitos.setSelectedTabIndicatorColor(Color.parseColor("#bbdefb"));
        // inserta el tab en el appbar
        appBar.addView(tabsRequisitos);

        viewPagerRequisitos = (ViewPager)view.findViewById(R.id.pagerRequisitos);
        presentadorRequisitos = PresentadorRequisitosImpl.getInstance();
        presentadorRequisitos.setVista(this);
        FragmentStatePagerAdapter paginaAdapter = presentadorRequisitos.getViewPagerAdapter(getFragmentManager());
        viewPagerRequisitos.setAdapter(paginaAdapter);
        tabsRequisitos.setupWithViewPager(viewPagerRequisitos);
        // retornamos la viosta cargada

        return view;
    }
    public void onDestroy() {
        super.onDestroy();
        appBar.removeView(tabsRequisitos);
        presentadorRequisitos.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        presentadorRequisitos.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presentadorRequisitos.onPause();
    }

    @Override
    public void mostrarContenido() {

    }

    @Override
    public void actualizarBarraProgreso(int porcentaje) {

    }
}
