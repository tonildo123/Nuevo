package com.example.sergio.nuevo.presentacion.presentador;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.ProgresarConsulta;
import com.example.sergio.nuevo.presentacion.vistas.EnviarMensaje;
import com.example.sergio.nuevo.presentacion.vistas.MainView;
import com.example.sergio.nuevo.presentacion.vistas.ViewConsultaLiquidacion;

import java.util.List;

/**
 * Created by Operador1 on 18/10/2017.
 */

public class PresentadorEnviarMensajeImpl implements IPresentador{
    private Activity activity;
    private MainView enviarMensaje;


    public PresentadorEnviarMensajeImpl(Activity activity, MainView enviarMensaje) {
        this.activity = activity;
        this.enviarMensaje = enviarMensaje;

    }

    @Override
    public void onResume() {

    }

    @Override
    public boolean onPause() {
        return false;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void iniciar() {

    }

    @Override
    public void onClick(Object[] o) {
        View v            = (View)         o[0];
        String opcion    = (String)      o[1];
        String nombre   = (String)     o[2];
        String Apellido = (String)     o[3];
        String Mail     = (String)     o[4];
        String Telefono = (String)     o[5];
        String campoMensaje = (String) o[6];



    }

    @Override
    public void cargarResultados() {

    }


}
