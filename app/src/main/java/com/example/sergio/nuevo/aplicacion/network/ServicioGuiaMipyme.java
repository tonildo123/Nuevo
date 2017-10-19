package com.example.sergio.nuevo.aplicacion.network;

import android.graphics.Bitmap;

import com.example.sergio.nuevo.dominio.Programa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Operador1 on 05/09/2017.
 */

public class ServicioGuiaMipyme {
    private Document doc;
    private Element selectorDiv;
    private String  s="";;

    private String urlGuiaMipyme = "http://181.14.240.59/Portal/mipyme/guia-de-financiamiento/";
    private static final ServicioGuiaMipyme guia = new ServicioGuiaMipyme();

    public static ServicioGuiaMipyme getInstance(){
        return guia;
    }

    public String guiamipyme(String url){
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            if(doc == null){
                return null;
            }

            Element select = doc.getElementsByClass("postview_content").first();
            Elements element3 = doc.getElementsByTag("h1");
            Elements elements1 = select.getElementsByTag("p");
            Elements elements2 = select.getElementsByTag("li");


            if(url.equals(urlGuiaMipyme)){
                s = s.concat(element3.toString());

                for (Element element:elements1){
                    s = s.concat(element.toString());


                }
                for (Element element:elements2){
                    s = s.concat(element.toString());

                }


            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return s;
    }



    public String getUrlGuiaMipyme() {
        return urlGuiaMipyme;
    }

    public void setUrlGuiaMipyme(String urlGuiaMipyme) {
        this.urlGuiaMipyme = urlGuiaMipyme;
    }
}
