package com.example.sergio.nuevo.presentacion.presentador;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.MenuItem;

import com.example.sergio.nuevo.aplicacion.network.ServicioPagEmpleo;
import com.example.sergio.nuevo.aplicacion.network.ServicioRequisitos;
import com.example.sergio.nuevo.dominio.A;
import com.example.sergio.nuevo.dominio.CFactory;
import com.example.sergio.nuevo.persistencia.PersisContactoYGuiaMipyme;
import com.example.sergio.nuevo.persistencia.PersisCronJoven;
import com.example.sergio.nuevo.persistencia.PersisCronProg;
import com.example.sergio.nuevo.persistencia.PersisNoticias;
import com.example.sergio.nuevo.persistencia.PersisRequisitos;
import com.example.sergio.nuevo.presentacion.vistas.MainView;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Operador1 on 11/10/2017.
 */

public class MainPresentadorImpl extends AsyncTask<Object, Object, Void> implements MainPresentador {
    private MainView mainView;
    private Activity act;

    private PersisNoticias not;
    private PersisCronProg cronProg;
    private PersisCronJoven cronJoven;
    private PersisRequisitos requisitos;
    private PersisContactoYGuiaMipyme contactosPagina;
    private SharedPreferences prefs;
    private static final String ACTIVITY_RESUMED = "activityResumed";

    public MainPresentadorImpl(MainView mainView) {
        this.mainView = mainView;
        this.act = (Activity) mainView;
        not = new PersisNoticias(act);
        cronProg = new PersisCronProg(act);
        cronJoven = new PersisCronJoven(act);
        requisitos = new PersisRequisitos(act);
        contactosPagina =  new PersisContactoYGuiaMipyme(act);
        prefs = this.act.getApplicationContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        this.execute();
    }
    @Override
    protected Void doInBackground(Object... objects) {
        crearCarpetas();
        ConnectivityManager connectivityManager = (ConnectivityManager)
                this.act.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
        boolean ping = false;
        try {
            String  address = InetAddress.getByName("www.google.com").getHostAddress();
            ping = true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if(actNetInfo != null && actNetInfo.isConnected() && ping){
            obtenerNoticias();
            obetenerContactos();
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
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mainView.mostrarContenido();
    }

    private void obtenerRequisitos() {
        requisitos.guardar(ServicioRequisitos.getInstance().getNovedades(ServicioRequisitos.getInstance().getUrlProgramaJoven()),"requisitos_joven");
        requisitos.guardar(ServicioRequisitos.getInstance().getNovedades(ServicioRequisitos.getInstance().getUrlProgramaProgresar()),"requisitos_progresar");
    }
    private void obetenerContactos(){
        if(ServicioPagEmpleo.getInstance().getPagEmpleo() != null){
            contactosPagina.guardar("contactos_syme",ServicioPagEmpleo.getInstance().contacto());
        }
    }

    private void obtenerNoticias() {
        ServicioPagEmpleo.getInstance().obtenerUrls();
        if(ServicioPagEmpleo.getInstance().getUrls().size() > 0){
            if(not.levantar() == null){
                not.guardar(ServicioPagEmpleo.getInstance().getNovedades());
            }else{
                ServicioPagEmpleo.getInstance().setNovedades(not.levantar());
                not.guardar(ServicioPagEmpleo.getInstance().getNovedades());
            }
        }
    }
    private void obtenerCronogramas() {
        if(cronProg.levantar() == null && ServicioPagEmpleo.getInstance().getUrls().size() > 0){
            cronProg.guardar(ServicioPagEmpleo.getInstance().obtenerCronogramaProg());
        }
        if(cronJoven.levantar() == null && ServicioPagEmpleo.getInstance().getUrls().size() > 0){
            cronJoven.guardar(ServicioPagEmpleo.getInstance().obtenerCronogramaJoven());
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

    @Override
    public void onResume() {
        prefs.edit().putBoolean(ACTIVITY_RESUMED, true).apply();
    }
    @Override
    public boolean onPause() {
        return prefs.getBoolean(ACTIVITY_RESUMED, false);
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public A onNavigationItemSelected(MenuItem item) {
        CFactory fact = new CFactory();
        return fact.create(item);
    }
}
