package com.example.sergio.nuevo.presentador;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.servicios.Noticia;
import com.example.sergio.nuevo.aplicacion.servicios.Progresarm;
import com.example.sergio.nuevo.aplicacion.patrones.Servicio;

import java.io.File;

public class Bienvenida extends AppCompatActivity {

    private Servicio s;
    private Noticia not;
    private Progresarm prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

    Thread hilo2 = new Thread(){
        @Override
        public void run() {
            try {
                iniciar();
                s = new Servicio(not.getInstance());
                s.obtenerUrls();
                s.getNovedades();
                Intent pasar = new Intent(Bienvenida.this, MainActivity.class );
                startActivity(pasar);
                finish();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    };
    hilo2.start();
    }

    private void iniciar() {
        File f = new File(Environment.getExternalStorageDirectory() + "/SSE");
// Comprobamos si la carpeta está ya creada

// Si la carpeta no está creada, la creamos.

        if(!f.isDirectory()) {
            String newFolder = "/SSE"; //cualquierCarpeta es el nombre de la Carpeta que vamos a crear
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File myNewFolder = new File(extStorageDirectory + newFolder);
            myNewFolder.mkdir(); //creamos la carpeta
        }
        f = new File(Environment.getExternalStorageDirectory() + "/SSE/tmp");
        if(!f.isDirectory()) {
            String newFolder = "/tmp"; //cualquierCarpeta es el nombre de la Carpeta que vamos a crear
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File myNewFolder = new File(extStorageDirectory+ "/SSE" + newFolder);
            myNewFolder.mkdir(); //creamos la carpeta
        }
    }
}
