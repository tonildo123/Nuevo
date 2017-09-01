package com.example.sergio.nuevo.aplicacion.patrones;

import com.example.sergio.nuevo.dominio.Noticia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 27/07/2017.
 */

public class Servicio {
    Strategy s;
    public Servicio() {

    }
    public void Clase(Strategy str){
        this.s = str;
    }

    public ArrayList getNovedades() {
        return s.getNovedades();
    }

    public void obtenerUrls() {
        s.obtenerUrls();
    }

    public void setNovedades(ArrayList array) {
    }

    public boolean comparar() {return s.comparar();
    }
}
