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


    private Button[] button = new Button[6];  // b1, b2, b3, b4, b5, b6;
    FragmentTransaction transaction = getFragmentManager().beginTransaction();

    public BotonesMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.botonesmenus, container, false);
        button[0] =  (Button)vista.findViewById(R.id.bMipyme);
        button[1] =  (Button)vista.findViewById(R.id.bjovenes );
        button[2] =  (Button)vista.findViewById(R.id.bConsulta);
        button[3] =  (Button)vista.findViewById(R.id.bProresar);
        button[4] =  (Button)vista.findViewById(R.id.bNoticias);
        button[5] =  (Button)vista.findViewById(R.id.bMapa);

        mostrarContenido(button);

        return vista;

    }

    private void mostrarContenido( Button[] button) {

        button[0].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                transaction.replace(R.id.contenedor, new ModuloMIPyme()).commit();}});
        button[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contenedor, new TabReqJoven()).commit();}});
        button[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contenedor, new ConsultaLiquidacion()).commit(); }});
        button[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contenedor, new TabReqProg()).commit(); }});
        button[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contenedor, new VistaNoticias()).commit();}});
        button[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contenedor, new Mapas()).commit();}});    }


}
