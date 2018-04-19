package com.empleotucuman.tuoficinadeempleo.presentacion.presentador;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.MenuItem;

import com.empleotucuman.tuoficinadeempleo.aplicacion.network.ServicioGuiaMipyme;
import com.empleotucuman.tuoficinadeempleo.aplicacion.network.ServicioPagEmpleo;
import com.empleotucuman.tuoficinadeempleo.aplicacion.network.ServicioRequisitos;
import com.empleotucuman.tuoficinadeempleo.dominio.A;
import com.empleotucuman.tuoficinadeempleo.dominio.CFactory;
import com.empleotucuman.tuoficinadeempleo.persistencia.PersisContactoYGuiaMipyme;
import com.empleotucuman.tuoficinadeempleo.persistencia.PersisCronJoven;
import com.empleotucuman.tuoficinadeempleo.persistencia.PersisCronProg;
import com.empleotucuman.tuoficinadeempleo.persistencia.PersisNoticias;
import com.empleotucuman.tuoficinadeempleo.persistencia.PersisRequisitos;
import com.empleotucuman.tuoficinadeempleo.persistencia.PersisToken;
import com.empleotucuman.tuoficinadeempleo.presentacion.caracteristicas.Transicion;
import com.empleotucuman.tuoficinadeempleo.presentacion.vistas.MainView;
import com.empleotucuman.tuoficinadeempleo.presentacion.vistas.Registro;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

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
    private PersisContactoYGuiaMipyme perContyGuia;
    private SharedPreferences prefs;
    private static final String ACTIVITY_RESUMED = "activityResumed";
    private PersisToken persisToken;

    public MainPresentadorImpl(MainView mainView) {
        this.mainView = mainView;
        this.act = (Activity) mainView;
        persisToken = new PersisToken(this.act);
        not = new PersisNoticias(act);
        cronProg = new PersisCronProg(act);
        cronJoven = new PersisCronJoven(act);
        requisitos = new PersisRequisitos(act);
        perContyGuia =  new PersisContactoYGuiaMipyme(act);
        prefs = this.act.getApplicationContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        this.execute();
    }
    @Override
    protected Void doInBackground(Object... objects) {
        crearCarpetas();
        publishProgress(10);
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
        publishProgress(20);
        if(actNetInfo != null && actNetInfo.isConnected() && ping){
            obtenerNoticias();
            publishProgress(70);
            obetenerContactos();
            publishProgress(80);
            obetenerguiamipyme();
            publishProgress(90);
            Thread hilo1 = new Thread(){
                @Override
                public void run() {
                    obtenerCronogramas();
                    publishProgress(100);
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
        if(persisToken.levantar() == null){
            Intent intent = new Intent(this.act, Registro.class);
            this.act.startActivity(intent);
        }
        mainView.mostrarContenido();
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        mainView.actualizarBarraProgreso((int)values[0]);
    }

    private void obtenerRequisitos() {
        Date fechaactual = new Date();
        if(requisitos.levantar("requisitos_joven") == null || (fechaactual.getTime()-requisitos.getModificacion("requisitos_joven").getTime()) > 604800000){
            requisitos.guardar(ServicioRequisitos.getInstance().getNovedades(ServicioRequisitos.getInstance().getUrlProgramaJoven()),"requisitos_joven");
        }
        if(requisitos.levantar("requisitos_progresar") == null || (fechaactual.getTime()-requisitos.getModificacion("contactos_syme").getTime()) > 604800000){
            requisitos.guardar(ServicioRequisitos.getInstance().getNovedades(ServicioRequisitos.getInstance().getUrlProgramaProgresar()),"requisitos_progresar");
        }
    }
    private void obetenerContactos(){
        Date fechaactual = new Date();
        if(perContyGuia.levantar("contactos_syme") == null || (fechaactual.getTime()-perContyGuia.getModificacion("contactos_syme").getTime()) > 604800000){
            perContyGuia.guardar("contactos_syme",ServicioPagEmpleo.getInstance().contacto());
        }
    }
    private void obetenerguiamipyme(){
        Date fechaactual = new Date();
        if(perContyGuia.levantar("guiamipyme_syme") == null || (fechaactual.getTime()-perContyGuia.getModificacion("guiamipyme_syme").getTime()) > 604800000){
            perContyGuia.guardar("guiamipyme_syme",ServicioGuiaMipyme.getInstance().guiamipyme(ServicioGuiaMipyme.getInstance().getUrlGuiaMipyme()));
        }
    }

    private void obtenerNoticias() {
        ServicioPagEmpleo.getInstance().obtenerUrls();
        publishProgress(30);
        if(ServicioPagEmpleo.getInstance().getUrls().size() > 0){
            publishProgress(40);
            if(not.levantar() == null){
                not.guardar(ServicioPagEmpleo.getInstance().getNovedades());
                publishProgress(50);
            }else{
                ServicioPagEmpleo.getInstance().setNovedades(not.levantar());
                publishProgress(60);
                if(ServicioPagEmpleo.getInstance().getNovedades() != null){
                    not.guardar(ServicioPagEmpleo.getInstance().getNoticias());
                }

            }
        }
    }
    private void obtenerCronogramas() {
        Date fechaactual = new Date();
        if(cronProg.levantar() == null && ServicioPagEmpleo.getInstance().getUrls().size() > 0 || (fechaactual.getTime()-cronProg.getModificacion().getTime()) > 604800000){
            cronProg.guardar(ServicioPagEmpleo.getInstance().obtenerCronogramaProg());
        }
        if(cronJoven.levantar() == null && ServicioPagEmpleo.getInstance().getUrls().size() > 0 || (fechaactual.getTime()-cronJoven.getModificacion().getTime()) > 604800000){
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
        f = new File(Environment.getExternalStorageDirectory() + "/SSE/resultados");
        if(!f.isDirectory()) {
            String newFolder = "/resultados"; //cualquierCarpeta es el nombre de la Carpeta que vamos a crear
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
    public void iniciar() {

    }

    @Override
    public void onClick(Object[] o) {

    }

    @Override
    public A onNavigationItemSelected(int item) {
        CFactory fact = new CFactory();
        return fact.create(item);
    }
}
