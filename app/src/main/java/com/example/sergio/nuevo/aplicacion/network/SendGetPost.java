package com.example.sergio.nuevo.aplicacion.network;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operador1 on 26/09/2017.
 */

public class SendGetPost {
    private static List<String> cookies;
    private HttpURLConnection conn;
    private static CookieManager manager;
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/6.1.2909.1213 Safari/537.36";
    private static String usuario = "cdiaz";
    private static String contraseña = "Tony2015";
    private String captcha;
    private String cuil;
    private Document doc;
    private static final SendGetPost sendGetPost = new SendGetPost();
    private StringBuffer response;

    private SendGetPost() {
        manager = new CookieManager();
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(manager);
    }
    public static SendGetPost getInstance(){
        return sendGetPost;
    }

    public StringBuffer getResponse() {
        return response;
    }


    public void sendPost(String postParams, String url,String Referer) throws Exception {
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
        conn.setRequestProperty("Content-Length", String.valueOf(postParams.length()));
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        for (String cookie : this.cookies) {
            conn.addRequestProperty("Cookie", cookie);
        }
        conn.setRequestProperty("charset", "utf-8");

        conn.setRequestProperty("Host", "181.14.240.59:12223");
        conn.setRequestProperty("Origin", "http://181.14.240.59:12223");
        conn.setRequestProperty("Referer", Referer);
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
        String inputLine;
        StringBuffer response = new StringBuffer();

        switch(responseCode){
            case 200:
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream())); //to avoid FNFExeption
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                break;
            case 400:
                BufferedReader in1 = new BufferedReader(new InputStreamReader(conn.getErrorStream()))   ; //to avoid FNFExeption
                while ((inputLine = in1.readLine()) != null) {
                    response.append(inputLine);
                }
                in1.close();
                break;
            case 500:
                BufferedReader in2 = new BufferedReader(new InputStreamReader(conn.getInputStream())); //to avoid FNFExeption
                while ((inputLine = in2.readLine()) != null) {
                    response.append(inputLine);
                }
                in2.close();
                break;
        }
        this.response = response;
        conn.disconnect();
    }

    public StringBuffer sendGet(String url) throws Exception {
        URL obj = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

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
            for (String cookie : this.cookies) {
                conn.addRequestProperty("Cookie", cookie);
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
        conn.disconnect();

        return response;
    }
    public static StringBuilder getFormParams (String pag, String cuil, String captcha) throws UnsupportedEncodingException {
        int init = 0;
        int sesion = 0;
        int nm = 0;
        int nm1 = 0;
        Document doc = Jsoup.parse(pag);
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
                                    }else{
                                        if (key.equals("nmgp_opcao") && nm1 != 1 && !value.equals("")) {
                                            nm1 = 1;
                                            paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(value != null && key != null){
                if (!value.equals("") && !key.equals("") && !key.equals("script_case_session") && !key.equals("nm_form_submit") && !key.equals("script_case_init") && !key.equals("nmgp_opcao")) {
                    paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
                }
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
