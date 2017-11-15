package com.example.sergio.nuevo.servicios;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sergio.nuevo.presentacion.vistas.MainActivity;

import java.io.File;

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
    public static void enviarGmail(Activity activity){
        Log.i("Send email", "");

        String[] TO = {" empleo@empleotucuman.gob.ar"};
        String[] CC = {"OTROMAIL@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "TU ASUNTO");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "ESCRIBE AQUI TU CORREO!!!");

        try {
            activity.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            activity.finish();
//            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    public static void enviarWhatsapp(Activity activity){
        //        PackageManager pm=getPackageManager();
        try {

//            Intent waIntent = new Intent(Intent.ACTION_SEND);
//            waIntent.setType("text/plain");
//            String text = "Tu texto aquí";
//
//            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
//            waIntent.setPackage("com.whatsapp");
//
//            waIntent.putExtra(Intent.EXTRA_TEXT, text);
//            startActivity(Intent.createChooser(waIntent, "Comunicarse con")); // tambien sirve para enviar wathsapp seleccionando contacto

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators("5493815442347")+"@s.whatsapp.net"); // envia wathsapp al numero
            activity.startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(activity, "WhatsApp no está instalado", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public static void compartirImagen(File directorioimg, Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(directorioimg));
        context.startActivity(Intent.createChooser(intent, "Compartir con"));
    }
}
