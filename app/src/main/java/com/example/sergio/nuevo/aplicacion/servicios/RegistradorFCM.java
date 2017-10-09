package com.example.sergio.nuevo.aplicacion.servicios;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class RegistradorFCM extends FirebaseInstanceIdService {

    public RegistradorFCM() {
    }

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i("FCM",token); // me muestra el token por consola
        registrartoken(token);

    }

    public void registrartoken(String token){
        // registra el token en la base de datos del servidor  de app firebase
    }
}
