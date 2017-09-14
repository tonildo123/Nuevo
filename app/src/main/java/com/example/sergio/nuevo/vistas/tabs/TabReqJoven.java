package com.example.sergio.nuevo.vistas.tabs;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.Programa;
import com.example.sergio.nuevo.persistencia.PersisRequisitos;
import java.io.UnsupportedEncodingException;


public class TabReqJoven extends Fragment {
    private PersisRequisitos reqJoven;
    private Programa joven;
    private ImageView imagen;
    private WebView pagina;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_jovenes_requisitos, container, false);

        imagen = v.findViewById(R.id.imgRequisitos);
        reqJoven = new PersisRequisitos(this.getActivity());
        joven = reqJoven.levantarNoticias("requisitos_joven");
        imagen.setImageBitmap(joven.getImg());
        pagina = (WebView)v.findViewById(R.id.webProgJov);
        WebSettings settings = pagina.getSettings();
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setDefaultFontSize(14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            String base64 = null;
            try {
                base64 = Base64.encodeToString(joven.getContenido().getBytes("UTF-8"), Base64.DEFAULT);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            pagina.loadData(base64, "text/html; charset=UTF-8", "base64");
        } else {
            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
            pagina.loadData(header + joven.getContenido(), "text/html; charset=UTF-8", null);
        }
        return v;
    }
}
