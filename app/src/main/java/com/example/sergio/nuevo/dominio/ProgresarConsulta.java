package com.example.sergio.nuevo.dominio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sergio.nuevo.aplicacion.network.AsynctaskConsultaLiquidacion;
import com.example.sergio.nuevo.aplicacion.network.AsynctaskGetCaptcha;
import com.example.sergio.nuevo.aplicacion.network.ObtImagen;
import com.example.sergio.nuevo.aplicacion.network.SendGetPost;
import com.example.sergio.nuevo.vistas.ConsultaLiquidacion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sergio on 08/08/2017.
 */

public class ProgresarConsulta {
    private static final ProgresarConsulta instancia = new ProgresarConsulta();
    private String url = "http://181.14.240.59:12223/sistema/sec_Login/sec_Login.php";
    private String urlConsulta = "http://181.14.240.59:12223/sistema/progresaractdetall_gd/progresaractdetall_gd.php";
    private StringBuffer pagLog;
    private String usuario = "usuario";
    private String contraseña = "12345";
    private String captcha;
    private String cuil;

    private ProgresarConsulta() {
    }

    public static ProgresarConsulta getInstance() {
        return instancia;
    }

    public void enviarDatos(String captcha, String cuil, ConsultaLiquidacion consultaLiquidacion) {
        this.cuil = cuil;
        this.captcha = captcha;
        AsynctaskConsultaLiquidacion asynctaskConsultaLiq = new AsynctaskConsultaLiquidacion(consultaLiquidacion);
        asynctaskConsultaLiq.execute();
    }

    public List<List<String>> obtenerDatos() {
        List<List<String>> datos = new ArrayList<>();
        Document doc = Jsoup.parse(SendGetPost.getInstance().getResponse().toString());
        Element tabla = doc.getElementById("apl_progresaractdetall_gd#?#1");
        Elements filasimpares = tabla.getElementsByClass("scGridFieldOdd");
        Elements primerafila = filasimpares.get(0).getElementsByTag("span");

        for (int i = 0; i < 8; i++) {
            List<String> lista = new ArrayList();
            switch (i) {
                case 0:
                    lista.add("Cuil");
                    break;
                case 1:
                    lista.add("Nombre Completo");
                    break;
                case 2:
                    lista.add("Localidad");
                    break;
                case 3:
                    lista.add("Provincia");
                    break;
                case 4:
                    lista.add("Email");
                    break;
                case 5:
                    lista.add("Situacion");
                    break;
                case 6:
                    lista.add("Fecha de Cobro");
                    break;
                case 7:
                    lista.add("Boca de Pago");
                    break;
            }
            if (!primerafila.get(i).text().equals("")) {
                lista.add(primerafila.get(i).text());
                datos.add(lista);
            }
        }
        return datos;
    }

    public void getCaptcha(View view, ProgressBar progressBar) {
        AsynctaskGetCaptcha task = new AsynctaskGetCaptcha(view,progressBar);
        task.execute(this.url);
    }

    public void setPagLog(StringBuffer pagLog) {
        this.pagLog = pagLog;
    }

    public String getUrl() {
        return url;
    }

    public StringBuffer getPagLog() {
        return pagLog;
    }
    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCaptcha() {
        return captcha;
    }

    public String getCuil() {
        return cuil;
    }

    public String getUrlConsulta() {
        return urlConsulta;
    }
}
