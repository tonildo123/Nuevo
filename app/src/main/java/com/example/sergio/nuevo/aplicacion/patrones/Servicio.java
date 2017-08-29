package com.example.sergio.nuevo.aplicacion.patrones;

import java.util.List;

/**
 * Created by Sergio on 27/07/2017.
 */

public class Servicio {
    Strategy s;
    public Servicio(Strategy str) {
        this.s = str;
    }

    public List getNovedades() {
        return s.getNovedades();
    }

    public void obtenerUrls() {
        s.obtenerUrls();
    }
}
