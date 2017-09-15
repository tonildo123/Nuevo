package com.example.sergio.nuevo.vistas;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.servicios.ServicioRequisitos;
import com.example.sergio.nuevo.persistencia.PersisCronJoven;
import com.example.sergio.nuevo.persistencia.PersisCronProg;
import com.example.sergio.nuevo.persistencia.PersisNoticias;
import com.example.sergio.nuevo.aplicacion.servicios.ServicioPagEmpleo;
import com.example.sergio.nuevo.aplicacion.patrones.Servicio;
import com.example.sergio.nuevo.persistencia.PersisRequisitos;

import java.io.File;

public class Bienvenida extends AppCompatActivity {

    private Servicio s;
    private PersisNoticias not = new PersisNoticias(this);
    private PersisCronProg cronProg = new PersisCronProg(this);
    private PersisCronJoven cronJoven = new PersisCronJoven(this);
    private PersisRequisitos reqJoven = new PersisRequisitos(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        s = new Servicio();

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
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
        if(actNetInfo != null && actNetInfo.isConnected()){
            Thread hilo1 = new Thread(){
                @Override
                public void run() {
                    obtenerNoticias();
                    obtenerCronogramas();
                }
            };
            Thread hilo2 = new Thread(){
                @Override
                public void run() {
                    obtenerRequisitos();
                }
            };
            hilo1.start();hilo2.start();

            try {
                hilo1.join();
                hilo2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void obtenerRequisitos() {
        reqJoven.guardarNoticias(ServicioRequisitos.getInstance().getNovedades(ServicioRequisitos.getInstance().getUrlProgramaJoven()),"requisitos_joven");
        reqJoven.levantarNoticias("requisitos_joven");
        reqJoven.guardarNoticias(ServicioRequisitos.getInstance().getNovedades(ServicioRequisitos.getInstance().getUrlProgramaProgresar()),"requisitos_progresar");
        reqJoven.levantarNoticias("requisitos_progresar");
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
        if(cronProg.levantarNoticias() == null){
            cronProg.guardarNoticias(s.obtenerCronogramaProg());
        }
        if(cronJoven.levantarNoticias() == null){
            cronJoven.guardarNoticias(s.obtenerCronogramaJoven());
        }
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
