package com.empleotucuman.tuoficinadeempleo.presentacion.vistas;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.empleotucuman.tuoficinadeempleo.R;
import com.empleotucuman.tuoficinadeempleo.servicios.ServicioCompartir;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class NoticiaWebView extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> urls = new ArrayList<>();
    private ProgressBar progressBar;
    private WebView pagina;
    private FloatingActionButton fbwhatsapp;
    private FloatingActionButton btnsiguiente;
    private FloatingActionButton btnanterior;
    private FloatingActionButton btnsalir;
    private Activity activity = this;
    private int position;


    public NoticiaWebView() {
        // Required empty public constructor
    }

    public void setUrl(ArrayList url) {
        this.urls = url;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUrl(getIntent().getExtras().getStringArrayList("url"));
        this.position = getIntent().getExtras().getInt("posicion");
        setContentView(R.layout.noticiawebview);
        pagina = (WebView)findViewById(R.id.paginaWeb);

        pagina.setWebViewClient(new WebViewClient());
        //habilitamos javascript y el zoom
        pagina.getSettings().setJavaScriptEnabled(true);
        pagina.getSettings().setBuiltInZoomControls(true);

        pagina.loadUrl(urls.get(this.position));

        pagina.setWebViewClient(new WebViewClient()
        {
            // evita que los enlaces se abran fuera nuestra app en el navegador de android
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                return false;
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        pagina.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int progress)
            {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);
                NoticiaWebView.this.setProgress(progress * 1000);
                progressBar.incrementProgressBy(progress);
                if(progress == 100)
                {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fbwhatsapp = (FloatingActionButton)findViewById(R.id.fb_whatsapp);
        fbwhatsapp.setOnClickListener(this);
        btnsiguiente = (FloatingActionButton)findViewById(R.id.btnsiguiente);
        btnsiguiente.setOnClickListener(this);
        btnanterior = (FloatingActionButton)findViewById(R.id.btnanterior);
        btnanterior.setOnClickListener(this);
        btnsalir = (FloatingActionButton)findViewById(R.id.salir);
        btnsalir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fb_whatsapp:
                ServicioCompartir.compartir(activity,urls.get(position),view);
                break;
            case R.id.btnsiguiente:
                position++;
                if(position==urls.size()){
                    position=0;
                }
                pagina.loadUrl(urls.get(this.position));
                break;
            case R.id.btnanterior:
                position--;
                if(position==0){
                    position=(urls.size()-1);
                }
                pagina.loadUrl(urls.get(this.position));
                break;
            case R.id.salir:
                finish();
                break;
        }
    }
}
