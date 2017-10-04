package com.example.sergio.nuevo.vistas.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorCronJoven;
import com.example.sergio.nuevo.aplicacion.network.ServicioPagEmpleo;
import com.example.sergio.nuevo.aplicacion.patrones.Servicio;
import com.example.sergio.nuevo.dominio.CronogramaJoven;
import com.example.sergio.nuevo.persistencia.PersisCronJoven;
import com.example.sergio.nuevo.vistas.NoticiaWebView;
import com.example.sergio.nuevo.vistas.caracteristicas.Transicion;

import java.util.ArrayList;


public class TabObservatorio extends Fragment {
    private WebView obse;
    String url = "http://mipyme.gob.ar/oepet/";
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_observatorio, container, false);
        obse = (WebView)v.findViewById(R.id.webObservatorio);

        obse.loadUrl(url);
        obse.getSettings().setJavaScriptEnabled(true);
        obse.setWebViewClient(new WebViewClient());
        progressBar = (ProgressBar)v.findViewById(R.id.progressbar);
        obse.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int progress)
            {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);

                progressBar.incrementProgressBy(progress);
                if(progress == 100)
                {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

               // Inlate the layout for this fragment
        return v;
    }


}
