package com.example.sergio.nuevo.vistas;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.sergio.nuevo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWebView extends Fragment {
    private String url;


    public FragmentWebView() {
        // Required empty public constructor
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_webview, container, false);
        WebView pagina = (WebView)v.findViewById(R.id.paginaWeb);
        pagina.getSettings().setJavaScriptEnabled(true);
        pagina.loadUrl("http://181.14.240.59/Portal/");

        return v;
    }

}
