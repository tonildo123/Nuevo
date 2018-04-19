package com.empleotucuman.tuoficinadeempleo.aplicacion.network;

import android.graphics.Bitmap;

import com.empleotucuman.tuoficinadeempleo.dominio.Programa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Operador1 on 05/09/2017.
 */

public class ServicioRequisitos {
    private Document doc;
    private Element selectorDiv;
    private Programa joven;
    private String urlProgramaJoven = "http://181.14.240.59/Portal/?page_id=116";
    private String urlProgramaProgresar = "http://181.14.240.59/Portal/?page_id=627";
    private static final ServicioRequisitos req = new ServicioRequisitos();

    public static ServicioRequisitos getInstance(){
        return req;
    }
    public Programa getNovedades(String url){
        Element contenido = new Element("body");
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            if(doc == null){
                return null;
            }
            selectorDiv = doc.getElementsByClass("text-content").first();
            Element titulo = selectorDiv.getElementsByTag("h1").first();
            Element urlimg = selectorDiv.getElementsByTag("img").first();
            Element select = selectorDiv.getElementsByClass("postview_content").first();
            contenido.append(titulo.toString());

            if(url.equals(urlProgramaProgresar)){
                Elements elements1 = select.getAllElements();
                for (Element element:elements1){

                    if(element.tagName()=="p" || element.tagName()=="li" ){
                        contenido.append(element.toString());
                    }

                }
                Bitmap imagen = ObtImagen.getInstance().descargarImagen(urlimg.attr("src"),700,200);
                joven = new Programa(urlimg.attr("src"),titulo.text(),imagen,contenido.toString());
            }else {
                Bitmap imagen = ObtImagen.getInstance().descargarImagen(urlimg.attr("src"),700,200);
                joven = new Programa(urlimg.attr("src"),titulo.text(),imagen,select.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return joven;
    }

    public String getUrlProgramaJoven() {
        return urlProgramaJoven;
    }

    public void setUrlProgramaJoven(String urlProgramaJoven) {
        this.urlProgramaJoven = urlProgramaJoven;
    }

    public String getUrlProgramaProgresar() {
        return urlProgramaProgresar;
    }

    public void setUrlProgramaProgresar(String urlProgramaProgresar) {
        this.urlProgramaProgresar = urlProgramaProgresar;
    }
}
