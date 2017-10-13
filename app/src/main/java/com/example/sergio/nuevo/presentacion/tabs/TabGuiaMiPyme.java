package com.example.sergio.nuevo.presentacion.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.sergio.nuevo.R;


public class TabGuiaMiPyme extends Fragment {

    private TextView titulo;
    private WebView web;
    String titul = " GUÍA DE FINANCIAMIENTO ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_guia_mipyme, container, false);
        titulo = (TextView)v.findViewById(R.id.tvTitulo);
        titulo.setText(titul);
        web = (WebView)v.findViewById(R.id.webGuiaMipyme);



        // Inlate the layout for this fragment
        return v;
    }


}
