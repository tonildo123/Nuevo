package com.example.sergio.nuevo.vistas;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.sergio.nuevo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiaWebView extends Activity {
    private String url = "http://181.14.240.59/Portal/";
    private ProgressBar progressBar;
    private WebView pagina;


    public NoticiaWebView() {
        // Required empty public constructor
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUrl(getIntent().getExtras().getString("url"));
        setContentView(R.layout.noticiawebview);
        pagina = (WebView)findViewById(R.id.paginaWeb);
        pagina.setWebViewClient(new WebViewClient());
        //habilitamos javascript y el zoom
        pagina.getSettings().setJavaScriptEnabled(true);
        pagina.getSettings().setBuiltInZoomControls(true);

        pagina.loadUrl(url);

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

}