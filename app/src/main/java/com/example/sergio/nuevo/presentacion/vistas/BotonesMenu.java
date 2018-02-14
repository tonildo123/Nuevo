package com.example.sergio.nuevo.presentacion.vistas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.CFactory;
import com.example.sergio.nuevo.presentacion.Mapas;
import com.example.sergio.nuevo.presentacion.ModuloMIPyme;
import com.example.sergio.nuevo.presentacion.tabs.TabCronJoven;
import com.example.sergio.nuevo.presentacion.tabs.TabReqJoven;
import com.example.sergio.nuevo.presentacion.tabs.TabReqProg;

/**
 * A simple {@link Fragment} subclass.
 */
public class BotonesMenu extends Fragment {
    private Button b1,b2,b3,b4,b5,b6;

    private static final BotonesMenu botones = new BotonesMenu();

    public static BotonesMenu getInstance(){
        return botones;
    }

    private BotonesMenu() {
        // Required empty public constructor
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
