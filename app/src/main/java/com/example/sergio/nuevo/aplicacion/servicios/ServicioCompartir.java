package com.example.sergio.nuevo.aplicacion.servicios;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.example.sergio.nuevo.vistas.NoticiaWebView;

/**
 * Created by Operador1 on 19/09/2017.
 */

public abstract class ServicioCompartir {
//    private static final ServicioCompartir servicio = new ServicioCompartir();

//    public static ServicioCompartir getInstance(){
//        return servicio;
//    }

    public static void compartirWhatsapp(Activity activity, String url, View v){
        PackageManager packageManager = activity.getPackageManager();
        try {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String text = url;

            PackageInfo info =activity.getPackageManager().getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            intent.setPackage("com.whatsapp");

            intent.putExtra(Intent.EXTRA_TEXT, text);
            activity.startActivity(Intent.createChooser(intent, "Compartir con"));
        } catch (PackageManager.NameNotFoundException e) {
            Snackbar.make(v,"WhatsApp no esta instalado", Snackbar.LENGTH_LONG).show();
//            Toast.makeText(activity.getApplicationContext(), "WhatsApp no esta instalado", Toast.LENGTH_SHORT).show();
        }
    }
}
