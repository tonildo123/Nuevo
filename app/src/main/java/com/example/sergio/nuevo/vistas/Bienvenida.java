package com.example.sergio.nuevo.vistas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.servicios.ServicioNoticias;
import com.example.sergio.nuevo.aplicacion.servicios.ServicioPagEmpleo;
import com.example.sergio.nuevo.aplicacion.patrones.Servicio;
import com.example.sergio.nuevo.persistencia.DBNoticias;

import java.io.File;

public class Bienvenida extends AppCompatActivity {

    private Servicio s;
    private ServicioNoticias not = new ServicioNoticias(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        s = new Servicio();

//        DBNoticias noti = new DBNoticias(this, "DBnoticias",null,1);
//        SQLiteDatabase db = noti.getWritableDatabase();
//        if(db != null){
//            System.out.println("se creo correctamente");
//        }

    Thread hilo2 = new Thread(){
        @Override
        public void run() {
            try {
                iniciar();
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
        crearCarpetas();
        obtenerNoticias();
        obtenerCronogramas();
    }
    private void obtenerNoticias() {
        s.Clase(ServicioPagEmpleo.getInstance());
        s.obtenerUrls();
        if(not.levantarNoticias() == null){
            not.guardarNoticias(s.getNovedades());
            s.setNovedades(not.levantarNoticias());
        }else{
            s.setNovedades(not.levantarNoticias());
        }
        if(!s.comparar()){
            not.guardarNoticias(s.getNovedades());
        }
    }
    private void obtenerCronogramas() {

    }

    private void crearCarpetas() {
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
        f = new File(Environment.getExternalStorageDirectory() + "/SSE/images");
        if(!f.isDirectory()) {
            String newFolder = "/images"; //cualquierCarpeta es el nombre de la Carpeta que vamos a crear
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File myNewFolder = new File(extStorageDirectory+ "/SSE" + newFolder);
            myNewFolder.mkdir(); //creamos la carpeta
        }
    }
}
