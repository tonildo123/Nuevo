package com.example.sergio.nuevo.aplicacion.network;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.ProgresarConsulta;
import com.example.sergio.nuevo.presentacion.presentador.IPresentador;
import com.example.sergio.nuevo.presentacion.presentador.PresentadorConsultaLiquidacion;
import com.example.sergio.nuevo.presentacion.vistas.ConsultaLiquidacion;

/**
 * Created by Operador1 on 27/09/2017.
 */

public class AsynctaskConsultaLiquidacion extends AsyncTask{
    private ProgressBar progressBar;
    private PresentadorConsultaLiquidacion presentador;

    public AsynctaskConsultaLiquidacion(PresentadorConsultaLiquidacion presentador,ProgressBar progressBar) {
        this.progressBar = progressBar;
        this.presentador = presentador;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Object doInBackground(Object[] o) {
        String pagLog = ProgresarConsulta.getInstance().getPagLog().toString();
        String usuario = ProgresarConsulta.getInstance().getUsuario();
        String contraseña = ProgresarConsulta.getInstance().getContraseña();
        String cuil = ProgresarConsulta.getInstance().getCuil();
        String captcha = ProgresarConsulta.getInstance().getCaptcha();
        StringBuffer pagConsulta;

        try {
            StringBuilder postParams = SendGetPost.getFormParams(pagLog,usuario,contraseña,null,null);
            SendGetPost.getInstance().sendPost(postParams.toString(), ProgresarConsulta.getInstance().getUrl());
            pagConsulta = SendGetPost.getInstance().sendGet(ProgresarConsulta.getInstance().getUrlConsulta());

            postParams = SendGetPost.getInstance().getFormParams(pagConsulta.toString(),usuario,contraseña,cuil,captcha);
            SendGetPost.getInstance().sendPost(postParams.toString(), ProgresarConsulta.getInstance().getUrlConsulta());
            postParams = SendGetPost.getInstance().getFormParams(SendGetPost.getInstance().getResponse().toString(),usuario,contraseña,null,null);
            SendGetPost.getInstance().sendPost(postParams.toString(), ProgresarConsulta.getInstance().getUrlConsulta());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        presentador.cargarResultados();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }
}
