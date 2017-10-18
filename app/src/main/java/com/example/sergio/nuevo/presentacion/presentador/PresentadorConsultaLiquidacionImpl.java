package com.example.sergio.nuevo.presentacion.presentador;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.ProgresarConsulta;
import com.example.sergio.nuevo.presentacion.vistas.ViewConsultaLiquidacion;
import com.example.sergio.nuevo.presentacion.vistas.ViewFragment;

import java.util.List;

/**
 * Created by Operador1 on 18/10/2017.
 */

public class PresentadorConsultaLiquidacionImpl implements PresentadorConsultaLiquidacion{
    private Activity activity;
    private ViewConsultaLiquidacion consultaliquidacion;

    public PresentadorConsultaLiquidacionImpl(Activity activity, ViewConsultaLiquidacion consultaliquidacion,ProgressBar progressBar) {
        this.activity = activity;
        this.consultaliquidacion = consultaliquidacion;
        ProgresarConsulta.getInstance().getCaptcha(activity.findViewById(android.R.id.content),progressBar);
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
        View v = (View) o[0];
        EditText etCaptcha = (EditText) o[1];
        EditText etCuil = (EditText) o[2];
        ProgressBar progressBar = (ProgressBar) o[3];
        switch (v.getId()){
            case R.id.bConsulta:
                if(etCaptcha.getText().toString().length() == 3 && etCuil.getText().toString().length() == 11){
                    ProgresarConsulta.getInstance().enviarDatos(etCaptcha.getText().toString(),etCuil.getText().toString(),this,progressBar);
                }else{
                    Snackbar.make(activity.findViewById(android.R.id.content),"Revise que los datos ingresados tengan la longitud correcta", Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.btnactcaptcha:
                ProgresarConsulta.getInstance().getCaptcha(activity.findViewById(android.R.id.content),progressBar);
                break;
        }
    }

    @Override
    public void cargarResultados() {
        List<List<String>> datos = ProgresarConsulta.getInstance().obtenerDatos();
        consultaliquidacion.cargarResultados(datos);
    }
}
