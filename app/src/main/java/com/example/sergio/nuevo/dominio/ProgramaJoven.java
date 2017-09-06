package com.example.sergio.nuevo.dominio;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Operador1 on 05/09/2017.
 */

public class ProgramaJoven {
    private String urlimagen;
    private String titulo;
    private String objetivo;
    private ArrayList<h3> h3 = new ArrayList<>();
    private Bitmap img;
    private String dirimagen;

    public ProgramaJoven(String urlimagen, String titulo, String objetivo, ArrayList<com.example.sergio.nuevo.dominio.h3> h3, Bitmap img) {
        this.urlimagen = urlimagen;
        this.titulo = titulo;
        this.objetivo = objetivo;
        this.h3 = h3;
        this.img = img;
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

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public ArrayList<com.example.sergio.nuevo.dominio.h3> getH3() {
        return h3;
    }

    public void setH3(ArrayList<com.example.sergio.nuevo.dominio.h3> h3) {
        this.h3 = h3;
    }
}
