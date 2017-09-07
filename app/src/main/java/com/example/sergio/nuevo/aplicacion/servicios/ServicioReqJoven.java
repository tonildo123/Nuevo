package com.example.sergio.nuevo.aplicacion.servicios;

import android.graphics.Bitmap;

import com.example.sergio.nuevo.aplicacion.network.ObtImagen;
import com.example.sergio.nuevo.dominio.ProgramaJoven;
import com.example.sergio.nuevo.dominio.h3;
import com.example.sergio.nuevo.dominio.li;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Operador1 on 05/09/2017.
 */

public class ServicioReqJoven {
    private Document doc;
    private Element selectorDiv;
    private Elements elements1;
    private Elements elements2;
    private ProgramaJoven joven;
    private String url = "http://181.14.240.59/Portal/programas-de-empleo/programa-jovenes-con-mas-y-mejor-trabajo/";
    private static final ServicioReqJoven req = new ServicioReqJoven();

    public static ServicioReqJoven getInstance(){
        return req;
    }
    public ProgramaJoven getNovedades(){
        try {
            Bitmap img;
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            selectorDiv = doc.getElementsByClass("text-content").first();
            Element titulo = selectorDiv.getElementsByTag("h1").first();
            Element urlimg = selectorDiv.getElementsByTag("img").first();
            Element objetivo = selectorDiv.getElementsByTag("blockquote").first();
            objetivo = objetivo.getElementsByTag("p").first();

            ArrayList<h3> h3 = new ArrayList<>();

            elements1 = selectorDiv.getElementsByTag("h3");
            elements2 = selectorDiv.getElementsByTag("ul");
            int j = 1;
            for (Element cad: elements1) {
                ArrayList<li> li = new ArrayList<>();
                int i=1;
                Elements el = elements2.get(j-1).getElementsByTag("li");
                for (Element cad1:el) {
                    li.add(new li(1,cad1.text()));
                    i++;
                }
                h3.add(new h3(j,cad.text()));
                h3.get(j-1).setItem(li);
                j++;
            }
            Bitmap imagen = ObtImagen.getInstance().descargarImagen(urlimg.attr("src"),500,200);
            joven = new ProgramaJoven(urlimg.attr("src"),titulo.text(),objetivo.text(),imagen);
            joven.setH3(h3);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return joven;
    }

}
