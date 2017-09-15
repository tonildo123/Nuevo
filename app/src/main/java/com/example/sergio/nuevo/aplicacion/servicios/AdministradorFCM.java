package com.example.sergio.nuevo.aplicacion.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AdministradorFCM extends Service {
    public AdministradorFCM() {
    }
// FCM = dCDSiKirhm8:APA91bEjvtkGp3TDaNnbcG1aEArvs8EeRyjXTfmZefZJMsjHrgasreQ0f_9tdjbTO_S-ZjJD5H0ZEgwQIN0tIXEd5QXGHlsbdCHBAzabr3CvcU5I04XwYDlqrfrEaDyQ9r8gknKWIxEo
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
