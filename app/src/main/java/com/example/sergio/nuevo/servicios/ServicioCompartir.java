package com.example.sergio.nuevo.servicios;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;

/**
 * Created by Operador1 on 19/09/2017.
 */

public abstract class ServicioCompartir {
//    private static final ServicioCompartir servicio = new ServicioCompartir();

//    public static ServicioCompartir getInstance(){
//        return servicio;
//    }

    public static void compartir(Activity activity, String url, View v){
        PackageManager packageManager = activity.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String text = url;
        intent.putExtra(Intent.EXTRA_TEXT, text);
        activity.startActivity(Intent.createChooser(intent, "Compartir con"));
    }
}
