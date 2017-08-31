package com.example.sergio.nuevo.aplicacion.servicios;

import com.example.sergio.nuevo.aplicacion.network.ObtImagen;
import com.example.sergio.nuevo.aplicacion.patrones.Strategy;
import com.example.sergio.nuevo.dominio.Noticia;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sergio on 27/07/2017.
 */

public class ServicioPagEmpleo implements Strategy {


    private static final String url = "http://181.14.240.59/Portal/";
    private Document doc;
    private Element selectorDiv;
    private Elements urlelements1;
    private Elements urlelements2;
    private String text;
    private boolean terminado = false;
    private static final ServicioPagEmpleo not = new ServicioPagEmpleo();
    private ArrayList<Thread> hilos = new ArrayList<>();
    private int i = 0;
    private ObtImagen img;
    private ArrayList<Noticia> noticias = new ArrayList<>();

    public static ServicioPagEmpleo getInstance() {
        return not;
    }

    public ServicioPagEmpleo() {
        img = img.getInstance();
    }

    @Override
    public synchronized ArrayList<Noticia> getNovedades() {
//        si las urls no estan listas entra al bucle y duerme el hilo de ejecución hasta que se notifique que ya
//        estan disponibles las urls.
        while (terminado == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getRecursos();
            return noticias;
        }
        getRecursos();
        return noticias;
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
                noticias.add(new Noticia());
            }
            int i = 0;
            for (Element cad : urlelements1) {
                text = cad.attr("href");
                noticias.get(i).setUrlParrafo(text);
                i++;
            }
            i = 0;
            for (Element cad : urlelements2) {
                text = cad.attr("src");
                noticias.get(i).setUrlImagen(text);
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

        if (noticias.get(0).getFoto() == null) {
            Thread hilopadre = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int k = 0; k < noticias.size(); k++) {
                            Thread hilohijo = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        Thread hilonieto1 = new Thread() {
                                            @Override
                                            public void run() {
                                                noticias.get(i).setFoto(img.descargarImagen(noticias.get(i).getUrlImagen(), 750, 500));
                                            }};
                                        Thread hilonieto2 = new Thread() {
                                            @Override
                                            public void run() {
                                                noticias.get(i).setParrafo(obtenerParrafo(noticias.get(i).getUrlParrafo()));
                                                noticias.get(i).setTitulo(obtenerTitulo());
                                            }};
                                        hilonieto1.start();hilonieto2.start();hilonieto1.join();hilonieto2.join();
                                        i++;
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            };
                            hilos.add(hilohijo);
                        }
                        for (int j=0;j<noticias.size();j++){
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

