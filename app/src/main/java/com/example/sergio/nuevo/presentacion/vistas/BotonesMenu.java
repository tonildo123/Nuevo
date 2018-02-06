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
import com.example.sergio.nuevo.presentacion.Mapas;
import com.example.sergio.nuevo.presentacion.ModuloMIPyme;
import com.example.sergio.nuevo.presentacion.tabs.TabCronJoven;
import com.example.sergio.nuevo.presentacion.tabs.TabReqJoven;
import com.example.sergio.nuevo.presentacion.tabs.TabReqProg;

/**
 * A simple {@link Fragment} subclass.
 */
public class BotonesMenu extends Fragment {


    private Button b1, b2, b3, b4, b5, b6;

    public BotonesMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.botonesmenus, container, false);
        b1 =  (Button)vista.findViewById(R.id.bMipyme);
        b2 =  (Button)vista.findViewById(R.id.bjovenes );
        b3 =  (Button)vista.findViewById(R.id.bConsulta);
        b4 =  (Button)vista.findViewById(R.id.bProresar);
        b5 =  (Button)vista.findViewById(R.id.bNoticias);
        b6 =  (Button)vista.findViewById(R.id.bMapa);

        mostrarContenido(b1, b2, b3, b4, b5, b6);

        return vista;

    }

    private void mostrarContenido(Button b1, Button b2, Button b3, Button b4, Button b5, Button b6) {
         b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedor, new ModuloMIPyme()).commit();}});
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, new TabReqJoven()).commit();}});
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, new ConsultaLiquidacion()).commit(); }});
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, new TabReqProg()).commit(); }});
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, new VistaNoticias()).commit();}});
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, new Mapas()).commit();}});

    }


}
