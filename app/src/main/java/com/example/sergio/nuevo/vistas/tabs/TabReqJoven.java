package com.example.sergio.nuevo.vistas.tabs;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.ProgramaJoven;
import com.example.sergio.nuevo.persistencia.PersisReqJoven;


public class TabReqJoven extends Fragment {
    private PersisReqJoven reqJoven;
    private ProgramaJoven joven;
    private TextView titulo;
    private TextView objetivo;
    private ImageView imagen;
    private WebView pagina;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_jovenes_requisitos, container, false);

        titulo = v.findViewById(R.id.tituloReqJoven);
        objetivo = v.findViewById(R.id.objetivoReqJoven);
//        list = (ListView) v.findViewById(R.id.listrequisitos);
        imagen = v.findViewById(R.id.imgRequisitos);
        reqJoven = new PersisReqJoven(this.getActivity());
        joven = reqJoven.levantarNoticias();
        titulo.setText(joven.getTitulo());
        objetivo.setText("OBJETIVO: " +
                ""+joven.getObjetivo());
        imagen.setImageBitmap(joven.getImg());
        pagina = (WebView)v.findViewById(R.id.webProgJov) ;
        String html = "<body><div class=\"postview_content\">\n" +
                "<h3 align=\"left\">Destinatarios</h3>\n" +
                "<ul>\n" +
                "<li>Jóvenes en situación de vulnerabilidad entre 18 y 24 años.</li>\n" +
                "<li>Jóvenes sin terminalidad educativa.</li>\n" +
                "<li>Jóvenes desocupados.</li>\n" +
                "</ul>\n" +
                "<h3 align=\"left\">Beneficios</h3>\n" +
                "<ul>\n" +
                "<li>Acceso a la información sobre el mercado laboral.</li>\n" +
                "<li>Actividades de orientación profesional.</li>\n" +
                "<li>Asistencia y tutorías para completar sus estudios primarios y/o secundarios con modalidades especiales.</li>\n" +
                "<li>Acceso a prácticas laborales en empresas del medio.</li>\n" +
                "<li>Desarrollo de micro-emprendimientos o experiencias de autoempleo.</li>\n" +
                "<li>Acceso a conocimientos informáticos y formación en diferentes oficios.</li>\n" +
                "<li>Asignación por contraprestación mensual no remunerativa de $1050, según prestaciones que el participante realiza.</li>\n" +
                "<li>Cobertura del programa por un lapso de 36 meses.</li>\n" +
                "</ul>\n" +
                "<h3 align=\"left\">Modalidad</h3>\n" +
                "<ul>\n" +
                "<li>Se&nbsp; vinculan los intereses del beneficiario con las necesidades contextuales.</li>\n" +
                "<li>Se promueve el desarrollo de capacidades tanto profesionales como emprendedoras.</li>\n" +
                "<li>Se asiste al beneficiario para que pueda concluir sus estudios primarios y secundarios.</li>\n" +
                "</ul>\n" +
                "\t\t\t</div></body>";
        String mime = "text/html";
        String encoding = "utf-8";
        pagina.loadData(html, mime, encoding);

        return v;
    }
}
