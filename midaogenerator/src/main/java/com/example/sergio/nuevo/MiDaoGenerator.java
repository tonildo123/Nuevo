package com.example.sergio.nuevo;

import java.lang.Exception;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MiDaoGenerator {

    public static void main(String args[]) throws Exception {

        Schema schema = new Schema(1, "com.example.sergio.nuevo");

        Entity archivo = schema.addEntity("Archivo");

        archivo.addIdProperty();

        archivo.addStringProperty("Nombre").notNull();

        archivo.addBooleanProperty("Disponible");

        archivo.addDateProperty("Fecha");

        archivo.addStringProperty("Enlace");

        new DaoGenerator().generateAll(schema, ".");
    }
}
