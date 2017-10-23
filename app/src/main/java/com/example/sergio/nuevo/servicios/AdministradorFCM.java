package com.example.sergio.nuevo.servicios;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.sergio.nuevo.presentacion.vistas.MainActivity;
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




    private void showNotification(String title, String text) {


        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.mipmap.sym_def_app_icon)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());


    }
}

