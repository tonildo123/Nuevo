package com.example.sergio.nuevo.aplicacion.patrones;

import com.example.sergio.nuevo.dominio.Noticia;

import java.util.ArrayList;

/**
 * Created by Sergio on 27/07/2017.
 */

public interface Strategy {
    public ArrayList getNovedades();
    public void obtenerUrls();
    public void setNovedades(ArrayList array);
    public boolean comparar();
}
