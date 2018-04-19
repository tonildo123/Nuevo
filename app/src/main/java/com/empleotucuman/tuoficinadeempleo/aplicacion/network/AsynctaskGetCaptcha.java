package com.empleotucuman.tuoficinadeempleo.aplicacion.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.empleotucuman.tuoficinadeempleo.R;
import com.empleotucuman.tuoficinadeempleo.dominio.ProgresarConsulta;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by Operador1 on 27/09/2017.
 */

public class AsynctaskGetCaptcha extends AsyncTask{
    private View view;
    private ProgressBar progressBar;

    public AsynctaskGetCaptcha(View view, ProgressBar progressBar) {
        this.view = view;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Bitmap doInBackground(Object[] url) {
        StringBuffer pag = null;
        try {
            pag = SendGetPost.getInstance().sendGet(url[0].toString());
            ProgresarConsulta.getInstance().setPagLog(pag);
            Document doc = Jsoup.parse(pag.toString());

            Element srccaptcha = doc.select("#id_captcha_img").first();
            ObtImagen.getInstance().descargarCaptcha("http://181.14.240.59:12223/sistema/sec_Login/" + srccaptcha.attr("src"));

        } catch (Exception e) {
            Snackbar.make(view,"No se pudo establecer conexion, vuelva a intentarlo",Snackbar.LENGTH_LONG).show();
        }
        return BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/SSE/tmp/captcha.jpg");
    }

    @Override
    protected void onPostExecute(Object o) {
        ImageView imageView = (ImageView)view.findViewById(R.id.ivCaptcha);
        imageView.setImageBitmap((Bitmap)o);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }
}
