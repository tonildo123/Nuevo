package com.example.sergio.nuevo.aplicacion.network;

import com.example.sergio.nuevo.aplicacion.patrones.Strategy;
import com.example.sergio.nuevo.dominio.CronogramaJoven;
import com.example.sergio.nuevo.dominio.CronogramaProgresar;
import com.example.sergio.nuevo.dominio.Noticia;

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

public class ServicioPagEmpleo implements Strategy {


    private static final String url = "http://181.14.240.59/Portal";
    private Document doc;
    private Document pagEmpleo;
    private Element selectorDiv;
    private Elements elements1;
    private Elements elements2;
    private String text;
    private boolean terminado = false;
    private static final ServicioPagEmpleo not = new ServicioPagEmpleo();
    private ArrayList<Thread> hilos = new ArrayList<>();
    private int i = 0;
    private ObtImagen img;
    private ArrayList<Noticia> noticias = new ArrayList<>();
    private List<List<String>> urls = new ArrayList<>();
    private String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/7.0.6.1618 Safari/537.36";

    public static ServicioPagEmpleo getInstance() {
        return not;
    }

    public ServicioPagEmpleo() {
        img = img.getInstance();
    }

    @Override
    public ArrayList<Noticia> getNovedades() {
        getNoticias();
        return noticias;
    }

    @Override
    public synchronized void obtenerUrls() {
        try {
            //necesitará protocolo http
            //doc trae el html completo de la url que se le agregue
            this.pagEmpleo = Jsoup.connect(url).userAgent(AGENT).get();
//                    Utilizar Element para buscar un elemento en especial, en este caso un DIV cuya class es nivoSlider .
            selectorDiv = this.pagEmpleo.select("div.nivoSlider").first();
            //Buscamos los objetos que posean el la etiqueta a.
            elements1 = selectorDiv.getElementsByTag("a");
            //Buscamos los objetos que posean el la etiqueta a.
            elements2 = selectorDiv.getElementsByTag("img");

            for (int i = 0; i < elements1.size(); i++) {
                urls.add(new ArrayList<String>());
                noticias.add(new Noticia());
            }
            int i = 0;
            for (Element cad : elements1) {
                urls.get(i).add(cad.attr("href"));
                noticias.get(i).setUrlParrafo(cad.attr("href"));
                i++;
            }
            i = 0;
            for (Element cad : elements2) {
                urls.get(i).add(cad.attr("src"));
                noticias.get(i).setUrlImagen(cad.attr("src"));
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

    @Override
    public void setNovedades(ArrayList array) {
        this.noticias = array;
    }

    @Override
    public boolean comparar() {
        boolean b = true;
        for (Noticia not : noticias) {
            for (List list : urls) {
                b = false;
                if (list.get(1).equals(not.getUrlImagen())) {
                    b = true;
                    break;
                }
            }
            if (b == false) {
                break;
            }
            if(not.getParrafo() == null || not.getTitulo() == null){
                b = false;
                break;
            }
        }
        return b;
    }

    public void getNoticias() {

        if (!comparar()) {
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
                                                noticias.get(i).setId(i);
                                                noticias.get(i).setUrlImagen(urls.get(i).get(1));
                                                noticias.get(i).setFoto(img.descargarImagen(urls.get(i).get(1), 650, 350));
                                            }
                                        };
                                        Thread hilonieto2 = new Thread() {
                                            @Override
                                            public void run() {
                                                noticias.get(i).setUrlParrafo(urls.get(i).get(0));
                                                noticias.get(i).setParrafo(obtenerParrafo(urls.get(i).get(0)));
                                                noticias.get(i).setTitulo(obtenerTitulo());
                                            }
                                        };
                                        hilonieto1.start();
                                        hilonieto2.start();
                                        hilonieto1.join();
                                        hilonieto2.join();
                                        i++;
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            };
                            hilos.add(hilohijo);
                        }
                        for (int j = 0; j < noticias.size(); j++) {
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
            elements1 = selectorDiv.getElementsByTag("p");

//            recorremos sus elementos y los agregamos al array de urls.
            text = "";
            text = elements1.get(1).text() + " " + elements1.get(2).text();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    private String obtenerTitulo() {
        Element titulo = doc.getElementsByTag("h1").first();
        return titulo.text();
    }

    public ArrayList<CronogramaProgresar> obtenerCronogramaProg() {
        ArrayList<CronogramaProgresar> cron = new ArrayList<>();

        if(pagEmpleo != null){
            Document doc = Jsoup.parse(this.pagEmpleo.toString());

            selectorDiv = doc.getElementById("sse-cronogramas-pago-8");
            elements1 = selectorDiv.getElementsByTag("tr");

            for (Element tr : elements1) {
                elements2 = tr.getElementsByTag("td");
                if (elements2.size() != 0) {
                    cron.add(new CronogramaProgresar(elements2.get(0).text(), elements2.get(1).text()));
                } else {
                    elements2 = tr.getElementsByTag("th");
                    cron.add(new CronogramaProgresar(elements2.get(0).text(), elements2.get(1).text()));
                }
            }
            return cron;
        }else {
            return null;
        }
    }

    public ArrayList<CronogramaJoven> obtenerCronogramaJoven() {
        ArrayList<CronogramaJoven> cron = new ArrayList<>();
        if(pagEmpleo != null) {
            Document doc = Jsoup.parse(this.pagEmpleo.toString());

            selectorDiv = doc.getElementById("sse-cronogramas-pago-6");
            elements1 = selectorDiv.getElementsByTag("tr");

            for (Element tr : elements1) {
                elements2 = tr.getElementsByTag("td");
                if (elements2.size() != 0) {
                    cron.add(new CronogramaJoven(elements2.get(0).text(), elements2.get(1).text()));
                } else {
                    elements2 = tr.getElementsByTag("th");
                    cron.add(new CronogramaJoven(elements2.get(0).text(), elements2.get(1).text()));
                }
            }
            return cron;
        }else {
            return null;
        }
    }

    public List<List<String>> getUrls() {
        return urls;
    }
}

