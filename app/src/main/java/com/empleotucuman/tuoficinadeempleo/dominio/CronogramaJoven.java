package com.empleotucuman.tuoficinadeempleo.dominio;

import java.util.Date;

/**
 * Created by JESUS on 04/09/2017.
 */

public class CronogramaJoven {
    private String terminacionDni;
    private String fecha;

    public CronogramaJoven(String terminacionDni, String fecha) {
        this.terminacionDni = terminacionDni;
        this.fecha = fecha;
    }

    public String getTerminacionDni() {
        return terminacionDni;
    }

    public String getFecha() {
        return fecha;
    }
}
