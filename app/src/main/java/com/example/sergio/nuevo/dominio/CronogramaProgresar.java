package com.example.sergio.nuevo.dominio;

/**
 * Created by JESUS on 04/09/2017.
 */

public class CronogramaProgresar {
    private String terminacionDni;
    private String fecha;

    public CronogramaProgresar(String terminacionDni, String fecha) {
        this.terminacionDni = terminacionDni;
        this.fecha = fecha;
    }

    public String getTerminacionDni() {
        return terminacionDni;
    }

    public void setTerminacionDni(String terminacionDni) {
        this.terminacionDni = terminacionDni;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
