package com.example.sergio.nuevo.vistas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.sergio.nuevo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWebView extends Fragment {


    public FragmentWebView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        WebView pagina = (WebView)getActivity().findViewById(R.id.paginaWeb);
        pagina.getSettings().setJavaScriptEnabled(true);
        pagina.loadUrl("http://181.14.240.59/Portal/");

        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

}
