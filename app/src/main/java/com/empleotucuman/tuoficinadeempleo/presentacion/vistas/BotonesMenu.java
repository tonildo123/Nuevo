package com.empleotucuman.tuoficinadeempleo.presentacion.vistas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.empleotucuman.tuoficinadeempleo.R;
import com.empleotucuman.tuoficinadeempleo.dominio.CFactory;
import com.empleotucuman.tuoficinadeempleo.presentacion.Mapas;
import com.empleotucuman.tuoficinadeempleo.presentacion.ModuloMIPyme;
import com.empleotucuman.tuoficinadeempleo.presentacion.tabs.TabCronJoven;
import com.empleotucuman.tuoficinadeempleo.presentacion.tabs.TabReqJoven;
import com.empleotucuman.tuoficinadeempleo.presentacion.tabs.TabReqProg;

/**
 * A simple {@link Fragment} subclass.
 */
public class BotonesMenu extends Fragment {
    private Button b1,b2,b3,b4,b5,b6;

    private static final BotonesMenu botones = new BotonesMenu();

    public static BotonesMenu getInstance(){
        return botones;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.botonesmenus, container, false);
        b1 =  (Button)vista.findViewById(R.id.bConsulta);
        b2 =  (Button)vista.findViewById(R.id.bjovenes);
        b3 =  (Button)vista.findViewById(R.id.bMapa);
        b4 =  (Button)vista.findViewById(R.id.bProresar);
        b5 =  (Button)vista.findViewById(R.id.bNoticias);
        b6 =  (Button)vista.findViewById(R.id.bMipyme);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarContenido(b1.getId());
            }});
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarContenido(b2.getId());
            }});
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarContenido(b3.getId());
            }});
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarContenido(b4.getId());
            }});
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarContenido(b5.getId());
            }});
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarContenido(b6.getId());
            }});

        return vista;

    }

    private void mostrarContenido(int boton) {
        CFactory factory = new CFactory();
        FragmentManager f = getFragmentManager();
        f.beginTransaction().replace(R.id.contenedor, (Fragment) factory.crearConMenu(boton)).commit();
    }


}
