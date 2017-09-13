package com.example.sergio.nuevo.vistas.tabs;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView objetivo;
    private ImageView imagen;
    private WebView pagina;
    private Elements elements1 = new Elements();
    private Elements elements2 = new Elements();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_jovenes_requisitos, container, false);

        titulo = v.findViewById(R.id.tituloReqJoven);
        objetivo = v.findViewById(R.id.objetivoReqJoven);
//        list = (ListView) v.findViewById(R.id.listrequisitos);
        imagen = v.findViewById(R.id.imgRequisitos);
        reqJoven = new PersisReqJoven(this.getActivity());
            joven = reqJoven.levantarNoticias();
        titulo.setText(joven.getTitulo());
        objetivo.setText("OBJETIVO: " +joven.getObjetivo());
        imagen.setImageBitmap(joven.getImg());
        int k=0;
        for (int i=0; i<joven.getH3().size();i++){
            elements2.add(i,new Element("h3"));
            elements2.append(joven.getH3().get(i).getSubtitulo());
            elements1.add(i,new Element("ul"));
            for (int j=0; j<joven.getH3().get(i).getItem().size();j++){
                elements1.get(i).append("<li>"+joven.getH3().get(i).getItem().get(j).getItem()+"</li>");
                k++;

            }
            elements2.get(i).append(elements1.get(i).toString());
        }
        pagina = (WebView)v.findViewById(R.id.webProgJov);
        byte[] s = new byte[0];
        try {
            s = elements2.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String html = null;
        try {
            html = new String(s,"ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String mime = "text/html";
        String encoding = "ISO-8859-1";
        pagina.loadData(elements2.toString(), mime, encoding);
        return v;
    }
}
