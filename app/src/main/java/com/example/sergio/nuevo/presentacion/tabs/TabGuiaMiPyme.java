package com.example.sergio.nuevo.presentacion.tabs;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.persistencia.PersisContactoYGuiaMipyme;

import java.io.UnsupportedEncodingException;


public class TabGuiaMiPyme extends Fragment {
    private PersisContactoYGuiaMipyme perContyGuia;
    private String guiamipyme;
    private TextView titulo;
    private WebView web;


    String titul = " GUÍA DE FINANCIAMIENTO ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_guia_mipyme, container, false);
        titulo = (TextView)v.findViewById(R.id.tvTitulo);


        perContyGuia = new PersisContactoYGuiaMipyme(this.getActivity());
        guiamipyme = perContyGuia.levantar("guiamipyme_syme");
        if(guiamipyme != null){
            cargarVista(v);
        }

        titulo.setText(titul);

        // Inlate the layout for this fragment
        return v;
    }

    private void cargarVista(View v) {

        web = (WebView)v.findViewById(R.id.webGuiaMipyme);
        WebSettings settings = web.getSettings();
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setDefaultFontSize(14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            String base64 = null;
            try {
                base64 = Base64.encodeToString(guiamipyme.getBytes("UTF-8"), Base64.DEFAULT);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            web.loadData(base64, "text/html; charset=UTF-8", "base64");
        } else {
            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
            web.loadData(header + guiamipyme, "text/html; charset=UTF-8", null);
        }
    }


}
