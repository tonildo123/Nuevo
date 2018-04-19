package com.empleotucuman.tuoficinadeempleo.dominio;

import android.graphics.Bitmap;

/**
 * Created by Operador1 on 05/09/2017.
 */

public class Programa {
    private String urlimagen;
    private String titulo;
    private Bitmap img;
    private String dirimagen;
    private String contenido;

    public Programa(String urlimagen, String titulo, Bitmap img, String contenido) {
        this.urlimagen = urlimagen;
        this.titulo = titulo;
        this.img = img;
        this.contenido = contenido;
    }

    public String getDirimagen() {
        return dirimagen;
    }

    public void setDirimagen(String dirimagen) {
        this.dirimagen = dirimagen;
    }

    public Bitmap getImg() {
        return img;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }
}
