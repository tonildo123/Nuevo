package com.example.sergio.nuevo.aplicacion.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.ProgresarConsulta;
import com.example.sergio.nuevo.vistas.ConsultaLiquidacion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by Operador1 on 27/09/2017.
 */

public class AsynctaskConsultaLiquidacion extends AsyncTask{
    private ProgressBar progressBar;
    private ConsultaLiquidacion consultaLiquidacion;

    public AsynctaskConsultaLiquidacion(ConsultaLiquidacion consultaLiquidacion) {
        progressBar = consultaLiquidacion.getView().findViewById(R.id.progressBar2);
        this.consultaLiquidacion = consultaLiquidacion;
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
        consultaLiquidacion.cargarResultados();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }
}
