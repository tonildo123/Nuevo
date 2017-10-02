package com.example.sergio.nuevo.vistas;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
    final int codigo_de_repuesta_escritura = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            codigo_de_repuesta_escritura);
                }
            }
        }
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
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case codigo_de_repuesta_escritura: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permisoEscritura();
                } else {
                    System.out.println("El usuario ha rechazado el permiso");
                }
                return;
            }
        }
    }
    public void permisoEscritura(){
        Intent permisos = new Intent(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Intent permisos2 = new Intent(Manifest.permission.READ_EXTERNAL_STORAGE);

        startActivity(permisos);
        startActivity(permisos2);

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
        if(cronProg.levantarNoticias() == null && ServicioPagEmpleo.getInstance().getUrls().size() > 0){
            cronProg.guardarNoticias(ServicioPagEmpleo.getInstance().obtenerCronogramaProg());
        }
        if(cronJoven.levantarNoticias() == null && ServicioPagEmpleo.getInstance().getUrls().size() > 0){
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
