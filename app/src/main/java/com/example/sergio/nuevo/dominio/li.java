package com.example.sergio.nuevo.dominio;

import java.util.ArrayList;

/**
 * Created by Operador1 on 05/09/2017.
 */

public class li {
    private int id;
    private String item;

    public li(int id, String item) {
        this.id = id;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
