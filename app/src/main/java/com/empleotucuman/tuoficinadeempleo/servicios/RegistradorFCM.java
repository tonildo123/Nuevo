package com.empleotucuman.tuoficinadeempleo.servicios;

import android.app.Activity;
import android.util.Log;

import com.empleotucuman.tuoficinadeempleo.persistencia.PersisToken;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class RegistradorFCM extends FirebaseInstanceIdService {
    private PersisToken persisToken;
    private Activity activity;

    public RegistradorFCM() {
    }

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i("FCM",token); // me muestra el token por consola
        registrartoken(token);

    }

    public void registrartoken(final String token){
    }
}
