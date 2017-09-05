package com.example.sergio.nuevo.dominio;

import java.util.ArrayList;

/**
 * Created by Operador1 on 05/09/2017.
 */

public class h3 {
    private ArrayList<li> item = new ArrayList<>();
    private int id;
    private String subtitulo;

    public h3(ArrayList<li> item, int id, String subtitulo) {
        this.item = item;
        this.id = id;
        this.subtitulo = subtitulo;
    }

    public ArrayList<li> getItem() {
        return item;
    }

    public void setItem(ArrayList<li> item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
