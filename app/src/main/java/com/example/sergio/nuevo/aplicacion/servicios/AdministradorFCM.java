package com.example.sergio.nuevo.aplicacion.servicios;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class AdministradorFCM extends FirebaseMessagingService {
    public AdministradorFCM() {
    }
// FCM = dCDSiKirhm8:APA91bEjvtkGp3TDaNnbcG1aEArvs8EeRyjXTfmZefZJMsjHrgasreQ0f_9tdjbTO_S-ZjJD5H0ZEgwQIN0tIXEd5QXGHlsbdCHBAzabr3CvcU5I04XwYDlqrfrEaDyQ9r8gknKWIxEo


@Override
public void onMessageReceived(RemoteMessage remoteMessage) {

    String titulo = remoteMessage.getNotification().getTitle();
    String texto = remoteMessage.getNotification().getBody();

    Log.i("Notification Recibida", ":");
    Log.i("Título :   ", titulo       );
    Log.i("Texto  :   ", texto        );

}
    private void showNotification(String title, String text) {

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setContentTitle(title)
                        .setContentText(text);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
