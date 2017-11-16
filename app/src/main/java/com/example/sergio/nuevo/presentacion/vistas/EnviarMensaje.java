package com.example.sergio.nuevo.presentacion.vistas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.servicios.ServicioCompartir;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EnviarMensaje extends Fragment  {

    private Button enviar;
    private EditText nombre, apellido, mail, telefono, campoMensaje;
    private Spinner opcion;
    private String uri = "http://181.14.240.59/Portal/contacto-5/";
    private String capacitacion = "formacionprofesional@empleotucuman.gob.ar", intermediacionLaboral ="pasantias@empleotucuman.gob.ar",emprendimientos="contacto@mipyme.gob.ar", programadeEmpleo="pulidol@empleotucuman.gob.ar",informacionInstitucional="empleo@empleotucuman.gob.ar";
    private ArrayList formulario = new ArrayList();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enviarmail,container,false);
        nombre   = (EditText)view.findViewById(R.id.etNombre);
        apellido = (EditText)view.findViewById(R.id.etApellido);
        mail     = (EditText)view.findViewById(R.id.etEmail);
        telefono = (EditText)view.findViewById(R.id.etTelefono);
        campoMensaje = (EditText)view.findViewById(R.id.etMensaje);

        enviar = (Button) view.findViewById(R.id.bEnviarMensaje);
        opcion = (Spinner)view.findViewById(R.id.spinnerDestino);

        final String[] valores= new String[]{"Capacitacion","Intermediación Laboral","Emprendimientos","Programas de Empleo","Información Institucional"};
        ArrayAdapter<String> adaptadorSpinner = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, valores){};
        opcion.setAdapter(adaptadorSpinner);


        // Inflate the layout for this fragment
        return view;
    }



    public void limpiarCampos() {
        opcion.getSelectedItem().toString();
        apellido.setText("");
        nombre.setText("");
        mail.setText("");
        telefono.setText("");
        campoMensaje.setText("");

    }

}
