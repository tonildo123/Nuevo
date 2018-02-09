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
    private Button[] button=  new Button[6];

        private int i= Integer.parseInt(null);
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
        button[0] =  (Button)vista.findViewById(R.id.bConsulta);
        button[1] =  (Button)vista.findViewById(R.id.bjovenes);
        button[2] =  (Button)vista.findViewById(R.id.bMapa);
        button[3] =  (Button)vista.findViewById(R.id.bProresar);
        button[4] =  (Button)vista.findViewById(R.id.bNoticias);
        button[5] =  (Button)vista.findViewById(R.id.bMipyme);

        button[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { mostrarContenido(button); }});

            return vista; }

    private void mostrarContenido(Button[] button) {

        CFactory factory = new CFactory();
        FragmentManager f = getFragmentManager();
        f.beginTransaction().replace(R.id.contenedor, (Fragment) factory.crearConMenu(button)).commit();

    }


}
