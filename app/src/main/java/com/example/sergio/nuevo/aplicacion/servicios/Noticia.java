package com.example.sergio.nuevo.aplicacion.servicios;

import com.example.sergio.nuevo.aplicacion.network.ObtImagen;
import com.example.sergio.nuevo.aplicacion.patrones.Strategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 27/07/2017.
 */

public class Noticia implements Strategy {


    private static final String url = "http://181.14.240.59/Portal/";
    private List<List<String>> urls = new ArrayList<List<String>>();
    private List<List> rec = new ArrayList<>();
    private Document doc;
    private Element selectorDiv;
    private Elements urlelements1;
    private Elements urlelements2;
    private String text;
    private boolean terminado = false;
    private static final Noticia not = new Noticia();
    private ArrayList<Thread> hilos = new ArrayList<>();
    private int i = 0;
    private ObtImagen img;

    public static Noticia getInstance() {
        return not;
    }

    public Noticia() {
        img = img.getInstance();
    }

    @Override
    public synchronized List<List> getNovedades() {
//        si las urls no estan listas entra al bucle y duerme el hilo de ejecución hasta que se notifique que ya
//        estan disponibles las urls.
        while (terminado == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getRecursos();
            return rec;
        }
        getRecursos();
        return rec;
    }

    @Override
    public synchronized void obtenerUrls() {
        try {
            //necesitará protocolo http
            //doc trae el html completo de la url que se le agregue
            this.doc = Jsoup.connect(url).userAgent("Mozilla").get();
//                    Utilizar Element para buscar un elemento en especial, en este caso un DIV cuya class es nivoSlider .
            selectorDiv = this.doc.select("div.nivoSlider").first();
            //Buscamos los objetos que posean el la etiqueta a.
            urlelements1 = selectorDiv.getElementsByTag("a");
            //Buscamos los objetos que posean el la etiqueta a.
            urlelements2 = selectorDiv.getElementsByTag("img");

//            recorremos sus elementos y los agregamos al array de urls.
            for (int i = 0; i < urlelements1.size(); i++) {
                urls.add(new ArrayList<String>());
            }
            int i = 0;
            for (Element cad : urlelements1) {
                text = cad.attr("href");
                urls.get(i).add(text);
                i++;
            }
            i = 0;
            for (Element cad : urlelements2) {
                text = cad.attr("src");
                urls.get(i).add(text);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        modificamos el estado de las urls
        terminado = true;
//        notificamos a los demas hilos que se termino la operación para que se despierten
        notifyAll();
    }

    public void getRecursos() {

        if (rec.size() == 0) {
            for (int j=0;j<urls.size();j++){
                rec.add(new ArrayList());
            }
            Thread hilopadre = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int k = 0; k < urls.size(); k++) {
                            Thread hilohijo = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        ArrayList array = new ArrayList();
                                        array.add(img.descargarImagen(urls.get(i).get(1), 750, 500));
                                        array.add(obtenerParrafo(urls.get(i).get(0)));
                                        array.add(obtenerTitulo());
                                        rec.set(i, array);
                                        i++;
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            };
                            hilos.add(hilohijo);
                        }
                        for (int j=0;j<urls.size();j++){
                            hilos.get(j).start();
                            hilos.get(j).join();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };
            hilopadre.start();
            try {
                hilopadre.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String obtenerParrafo(String s) {
        try {
            //necesitará protocolo http
            //doc trae el html completo de la url que se le agregue
            doc = Jsoup.connect(s).userAgent("Mozilla").get();

//                    Utilizar Element para buscar un elemento en especial, en este caso un DIV cuya class es postview_content .
            selectorDiv = doc.select("div.postview_content").first();
            //Buscamos los objetos que posean el la etiqueta a.
            urlelements1 = selectorDiv.getElementsByTag("p");

//            recorremos sus elementos y los agregamos al array de urls.
            text = "";
            text = urlelements1.get(1).text() + " " + urlelements1.get(2).text();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
    private String obtenerTitulo(){
        Element titulo = doc.getElementsByTag("h1").first();
        return titulo.text();
    }

}

