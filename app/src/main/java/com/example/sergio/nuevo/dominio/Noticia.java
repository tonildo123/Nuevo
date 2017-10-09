package com.example.sergio.nuevo.dominio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import static android.os.Build.*;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Operador1 on 29/08/2017.
 */

public class Noticia {
    private int id;
    private String titulo;
    private String urlImagen;
    private Bitmap foto;
    private String urlParrafo;
    private String parrafo;



    public Noticia() {

    }

    public Noticia(int id, String titulo, String urlImagen, Bitmap foto, String urlParrafo, String parrafo) {
        this.id = id;
        this.titulo = titulo;
        this.urlImagen = urlImagen;
        this.foto = foto;
        this.urlParrafo = urlParrafo;
        this.parrafo = parrafo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlParrafo() {
        return urlParrafo;
    }

    public void setUrlParrafo(String urlParrafo) {
        this.urlParrafo = urlParrafo;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getParrafo() {
        return parrafo;
    }

    public void setParrafo(String parrafo) {
        this.parrafo = parrafo;
    }

}
