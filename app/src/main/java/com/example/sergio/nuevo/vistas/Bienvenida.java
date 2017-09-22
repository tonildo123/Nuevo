package com.example.sergio.nuevo.vistas;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.network.ServicioRequisitos;
import com.example.sergio.nuevo.persistencia.PersisCronJoven;
import com.example.sergio.nuevo.persistencia.PersisCronProg;
import com.example.sergio.nuevo.persistencia.PersisNoticias;
import com.example.sergio.nuevo.aplicacion.network.ServicioPagEmpleo;
import com.example.sergio.nuevo.persistencia.PersisRequisitos;

import java.io.File;

public class Bienvenida extends AppCompatActivity {
    private PersisNoticias not = new PersisNoticias(this);
    private PersisCronProg cronProg = new PersisCronProg(this);
    private PersisCronJoven cronJoven = new PersisCronJoven(this);
    private PersisRequisitos requisitos = new PersisRequisitos(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
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
            obtenerNoticias();
            Thread hilo1 = new Thread(){
                @Override
                public void run() {
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
        requisitos.guardarNoticias(ServicioRequisitos.getInstance().getNovedades(ServicioRequisitos.getInstance().getUrlProgramaJoven()),"requisitos_joven");
        requisitos.levantarNoticias("requisitos_joven");
        requisitos.guardarNoticias(ServicioRequisitos.getInstance().getNovedades(ServicioRequisitos.getInstance().getUrlProgramaProgresar()),"requisitos_progresar");
        requisitos.levantarNoticias("requisitos_progresar");
    }

    private void obtenerNoticias() {
        ServicioPagEmpleo.getInstance().obtenerUrls();

        if(ServicioPagEmpleo.getInstance().getUrls().size() > 0){
            if(not.levantarNoticias() == null){
                not.guardarNoticias(ServicioPagEmpleo.getInstance().getNovedades());
            }else{
                ServicioPagEmpleo.getInstance().setNovedades(not.levantarNoticias());
                if(!ServicioPagEmpleo.getInstance().comparar()){
                    not.guardarNoticias(ServicioPagEmpleo.getInstance().getNovedades());
                }
            }
        }
    }
    private void obtenerCronogramas() {
        if(cronProg.levantarNoticias() == null){
            cronProg.guardarNoticias(ServicioPagEmpleo.getInstance().obtenerCronogramaProg());
        }
        if(cronJoven.levantarNoticias() == null){
            cronJoven.guardarNoticias(ServicioPagEmpleo.getInstance().obtenerCronogramaJoven());
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
