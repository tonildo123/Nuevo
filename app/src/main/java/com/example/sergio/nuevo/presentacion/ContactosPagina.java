package com.example.sergio.nuevo.presentacion;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.network.ServicioPagEmpleo;
import com.example.sergio.nuevo.persistencia.PersisContactoYGuiaMipyme;
import com.example.sergio.nuevo.presentacion.caracteristicas.Transicion;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactosPagina extends Fragment {
    private String contact = "";
    private PersisContactoYGuiaMipyme perContyGuia;
    private String contactGuia;

    public ContactosPagina() {
        // Required empty public constructor
    }

    private Button b1, b2, b3;
    private TextView tcontacto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_contactos_pagina, container, false);
        b1 = (Button)vista.findViewById(R.id.bTel1);
        b2 = (Button)vista.findViewById(R.id.bTel2);
        b3 = (Button)vista.findViewById(R.id.bTel3);

        perContyGuia = new PersisContactoYGuiaMipyme(this.getActivity());
        contactGuia = perContyGuia.levantar("contactos_syme");
        if(contactGuia != null){
            cargarVista(vista);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {  Intent llamar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 4228420 "));
                    startActivity(llamar);
                }catch (Exception e){
                    e.printStackTrace();  }            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {  Intent llamar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 4228408 "));
                    startActivity(llamar);
                }catch (Exception e){
                    e.printStackTrace(); }            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {Intent llamar = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 4218810 "));
                    startActivity(llamar);
                }catch (Exception e){
                    e.printStackTrace(); }            }
        });
        // Inflate the layout for this fragment
        return vista;
    }
    private void cargarVista(View vista) {

        tcontacto = (TextView)vista.findViewById(R.id.tvContactos);
        contact = ServicioPagEmpleo.getInstance().contacto();

        tcontacto.setText(contact);

    }
    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }
}
