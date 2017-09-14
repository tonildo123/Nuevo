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
import com.example.sergio.nuevo.dominio.ProgramaJoven;
import com.example.sergio.nuevo.persistencia.PersisReqJoven;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class TabReqJoven extends Fragment {
    private PersisReqJoven reqJoven;
    private ProgramaJoven joven;
    private TextView titulo;
    private ImageView imagen;
    private WebView pagina;
    private Elements elements1 = new Elements();
    private Elements elements2 = new Elements();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_jovenes_requisitos, container, false);

        titulo = v.findViewById(R.id.tituloReqJoven);
        imagen = v.findViewById(R.id.imgRequisitos);
        reqJoven = new PersisReqJoven(this.getActivity());
            joven = reqJoven.levantarNoticias();
        titulo.setText(joven.getTitulo());
//        objetivo.setText("OBJETIVO: " +joven.getObjetivo());
        imagen.setImageBitmap(joven.getImg());
        int k=0;
        int i=0;
        elements2.add(i, new Element("h2"));
        elements2.get(i).append("Objetivos: ");
        i++;
        elements2.add(i, new Element("p"));
        elements2.get(i).append(joven.getObjetivo());
        i++;
        for (; (i-2)<joven.getH3().size();i++){
            elements2.add(i,new Element("h3"));
            elements1.add(i-2,new Element("ul"));
            for (int j=0; j<joven.getH3().get(i-2).getItem().size();j++){
                elements1.get(i-2).append("<li>"+joven.getH3().get(i-2).getItem().get(j).getItem()+"</li>");
                k++;
            }
            elements2.get(i).append(joven.getH3().get(i-2).getSubtitulo());
            elements2.get(i).append(elements1.get(i-2).toString());
        }
        pagina = (WebView)v.findViewById(R.id.webProgJov);
        WebSettings settings = pagina.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setDefaultFontSize(14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            String base64 = Base64.encodeToString(elements2.toString().getBytes(), Base64.DEFAULT);
            pagina.loadData(base64, "text/html; charset=utf-8", "base64");
        } else {
            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
            pagina.loadData(header + elements2.toString(), "text/html; charset=UTF-8", null);
        }
        return v;
    }
}
