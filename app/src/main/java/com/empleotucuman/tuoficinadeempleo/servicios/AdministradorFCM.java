package com.empleotucuman.tuoficinadeempleo.servicios;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.MenuItem;

import com.empleotucuman.tuoficinadeempleo.R;
import com.empleotucuman.tuoficinadeempleo.presentacion.ModuloMIPyme;
import com.empleotucuman.tuoficinadeempleo.presentacion.vistas.MainActivity;
import com.empleotucuman.tuoficinadeempleo.presentacion.vistas.ResultadoLiquidaciones;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class AdministradorFCM extends FirebaseMessagingService {
    public AdministradorFCM() {
    }
// FCM = dCDSiKirhm8:APA91bEjvtkGp3TDaNnbcG1aEArvs8EeRyjXTfmZefZJMsjHrgasreQ0f_9tdjbTO_S-ZjJD5H0ZEgwQIN0tIXEd5QXGHlsbdCHBAzabr3CvcU5I04XwYDlqrfrEaDyQ9r8gknKWIxEo


    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String titulo = remoteMessage.getNotification().getTitle();
        String texto = remoteMessage.getNotification().getBody();
        Map map = remoteMessage.getData();

        Log.i("Notification Recibida", ":");
        Log.i("Título :   ", titulo);
        Log.i("Texto  :   ", texto);

        showNotification(titulo, texto);
    }
    private void showNotification(final String title, String text) {
        final Intent intent = new Intent(this, MainActivity.class);
        switch(title){
            case "Observatorio":
                intent.putExtra("Fragments",R.layout.fragment_observatorio);
                break;
            case "MyPyme":
                intent.putExtra("Fragments",R.id.bMipyme);
                break;
            case "Jovenes":
                intent.putExtra("Fragments",R.id.bjovenes);
                break;
            case "Progresar":
                intent.putExtra("Fragments",R.id.bProresar);
                break;
            case "Mapa":
                intent.putExtra("Fragments",R.id.bMapa);
                break;
            default:
                intent.putExtra("Fragments",R.id.bNoticias);
                break;
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String KEY_NOTIFICATION_GROUP = "grupo";

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setGroupSummary(true)
                .setGroup(KEY_NOTIFICATION_GROUP);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}

