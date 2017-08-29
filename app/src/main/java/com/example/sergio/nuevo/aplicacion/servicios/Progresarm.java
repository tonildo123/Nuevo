package com.example.sergio.nuevo.aplicacion.servicios;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.sergio.nuevo.aplicacion.network.ObtImagen;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sergio on 08/08/2017.
 */

public class Progresarm {
    private static final Progresarm instancia = new Progresarm();
    private List<String> cookies;
    private HttpURLConnection conn;
    private static CookieManager manager;
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/6.1.2909.1213 Safari/537.36";
    private String url = "http://181.14.240.59:12223/sistema/sec_Login/sec_Login.php";
    private String urlConsulta = "http://181.14.240.59:12223/sistema/progresaractdetall_gd/progresaractdetall_gd.php";
    private Document doc;
    private StringBuffer pagLog;
    private StringBuffer pagConsulta;
    private String usuario = "usuario";
    private String contraseña = "12345";
    private String captcha;
    private String cuil;
    private ObtImagen img;
    private StringBuffer response;

    private Progresarm() {
        manager = new CookieManager();
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(manager);
        try {
            pagLog = sendGet(this.url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Progresarm getInstance() {
        return instancia;
    }

    public void enviarDatos(String captcha, String cuil) {
        this.captcha = captcha;
        this.cuil = cuil;
        try {
            StringBuilder postParams = getFormParams(pagLog);
            sendPost(postParams.toString(), this.url);
            pagConsulta = sendGet(urlConsulta);

            postParams = getFormParams(pagConsulta);
            sendPost(postParams.toString(), urlConsulta);
            postParams = getFormParams(response);
            sendPost(postParams.toString(), urlConsulta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<List<String>> obtenerDatos() {
        List<List<String>> datos = new ArrayList<>();
        doc = Jsoup.parse(response.toString());
        Element tabla = doc.getElementById("apl_progresaractdetall_gd#?#1");
        Elements filasimpares = tabla.getElementsByClass("scGridFieldOdd");
        Elements primerafila = filasimpares.get(0).getElementsByTag("span");
        boolean b = true;

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

    public Bitmap getCaptcha() {
        doc = Jsoup.parse(pagLog.toString());
        Bitmap bit = null;
        Element srccaptcha = doc.select("#id_captcha_img").first();
        img = img.getInstance();
        img.descargarCaptcha("http://181.14.240.59:12223/sistema/sec_Login/" + srccaptcha.attr("src"));
        bit = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/SSE/tmp/captcha.jpg");
        return bit;
    }

    private void sendPost(String postParams, String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
//        conn.setReadTimeout(10000);

        // Acts like a browser
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("Accept-Language", "es-419,es;q=0.8");
        conn.setRequestProperty("Cache-Control", "max-age=0");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        for (String cookie : this.cookies) {
            conn.addRequestProperty("Cookie", cookie);
        }
        conn.setRequestProperty("charset", "utf-8");

        for (String cookie : getCookies()) {
            conn.addRequestProperty("Cookie", cookie);
        }
        conn.setRequestProperty("Host", "181.14.240.59:12223");
        conn.setRequestProperty("Origin", "http://181.14.240.59:12223");
        conn.setRequestProperty("Referer", "http://181.14.240.59:12223/sistema/sec_Login/sec_Login.php");
        conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
        conn.setRequestProperty("User-Agent", USER_AGENT);

        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Send post request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        this.response = response;
    }

    private StringBuffer sendGet(String url) throws Exception {
        URL obj = new URL(url);

        conn = (HttpURLConnection) obj.openConnection();

        // default is GET
        conn.setRequestMethod("GET");

        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "es-419,es;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");

        conn.setRequestProperty("Cache-Control", "max-age=0");
        if (getCookies() != null) {
            for (String cookie : getCookies()) {
                conn.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
            }
        }
        conn.setRequestProperty("Host", "181.14.240.59:12223");
        conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
        conn.setRequestProperty("User-Agent", USER_AGENT);


        int responseCode = conn.getResponseCode();

        // Get the response cookies
        if (getCookies() == null) {
            setCookies(conn.getHeaderFields().get("Set-Cookie"));
        }


        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response;
    }

    private StringBuilder getFormParams(StringBuffer pag) throws UnsupportedEncodingException {
        int init = 0;
        int sesion = 0;
        int nm = 0;
        doc = Jsoup.parse(pag.toString());
        Elements inputElements = doc.getElementsByTag("input");
        List<String> paramList = new ArrayList<String>();
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if (key.equals("login")) {
                value = usuario;
            } else {
                if (key.equals("pswd")) {
                    value = contraseña;
                } else {
                    if (key.equals("captcha_code")) {
                        value = captcha;
                    } else {
                        if (key.equals("cuil")) {
                            value = cuil;
                        } else {
                            if (key.equals("script_case_session") && sesion != 1 && !value.equals("")) {
                                sesion = 1;
                                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
                            } else {
                                if (key.equals("script_case_init") && init != 1 && !value.equals("")) {
                                    init = 1;
                                    paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
                                } else {
                                    if (key.equals("nm_form_submit") && nm != 1 && !value.equals("")) {
                                        nm = 1;
                                        paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!value.equals("") && !key.equals("") && !key.equals("script_case_session") && !key.equals("nm_form_submit") && !key.equals("script_case_init")) {
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            }
        }
        // build parameters list
        StringBuilder result = new StringBuilder();
        for (String param : paramList) {
            if (result.length() == 0) {
                result.append(param);
            } else {
                result.append("&" + param);
            }
        }
        return result;
    }

    private List<String> getCookies() {
        return cookies;
    }

    private void setCookies(List<String> cookies) {
        if (cookies != null)
            this.cookies = cookies;
    }
}
