package com.example.sergio.nuevo.aplicacion.servicios;

import android.graphics.Bitmap;

import com.example.sergio.nuevo.aplicacion.network.ObtImagen;
import com.example.sergio.nuevo.dominio.Programa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Operador1 on 05/09/2017.
 */

public class ServicioRequisitos {
    private Document doc;
    private Element selectorDiv;
    private Programa joven;
    private String urlProgramaJoven = "http://181.14.240.59/Portal/programas-de-empleo/programa-jovenes-con-mas-y-mejor-trabajo/";
    private String urlProgramaProgresar = "http://181.14.240.59/Portal/programas-de-empleo/progresar/";
    private static final ServicioRequisitos req = new ServicioRequisitos();

    public static ServicioRequisitos getInstance(){
        return req;
    }
    public Programa getNovedades(String url){
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            if(doc == null){
                return null;
            }
            selectorDiv = doc.getElementsByClass("text-content").first();
            Element titulo = selectorDiv.getElementsByTag("h1").first();
            Element urlimg = selectorDiv.getElementsByTag("img").first();
            Element select = selectorDiv.getElementsByClass("postview_content").first();
            Element contenido = new Element("body");
            if(url.equals(urlProgramaProgresar)){
                Elements elements1 = select.getAllElements();
                for (Element element:elements1){
                    if(element.tagName()=="h3" || element.tagName()=="p" || element.tagName()=="ul"){
                        contenido.append(element.toString());
                    }
                    if(element.tagName()=="h3"){
                        break;
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
