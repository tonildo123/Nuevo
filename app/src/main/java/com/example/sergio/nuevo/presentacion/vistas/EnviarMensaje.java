package com.example.sergio.nuevo.presentacion.vistas;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.sergio.nuevo.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class EnviarMensaje extends Fragment  {

    private Button enviar;
    private EditText nombre, apellido, mail, telefono, campoMensaje;
    private Spinner opcion;
    private String uri = "http://181.14.240.59/Portal/contacto-5/";
    private String capacitacion = "formacionprofesional@empleotucuman.gob.ar", intermediacionLaboral ="pasantias@empleotucuman.gob.ar",emprendimientos="contacto@mipyme.gob.ar", programadeEmpleo="pulidol@empleotucuman.gob.ar",informacionInstitucional="empleo@empleotucuman.gob.ar";
    private String correo="";

    String gmail ="";



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

        enviar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(opcion.getSelectedItem().toString()=="Capacitacion"){
                    enviarMail(capacitacion);
                }
                if(opcion.getSelectedItem().toString()=="Intermediación Laboral"){
                    enviarMail(intermediacionLaboral);
                }
                if(opcion.getSelectedItem().toString()=="Emprendimientos"){
                    enviarMail(emprendimientos);
                }
                if(opcion.getSelectedItem().toString()=="Programas de Empleo"){
                    enviarMail(programadeEmpleo);
                }
                if(opcion.getSelectedItem().toString()=="Información Institucional"){
                    enviarMail(informacionInstitucional);
                }

            }

        });




        // Inflate the layout for this fragment
        return view;
    }



    public  void enviarMail(String correo) {

//        List<ApplicationInfo> inf = getActivity().getPackageManager().getInstalledApplications(0);
//        List<String> inf1 = new ArrayList<>();
//
//        for (int i =0; i < inf.size(); i++ ){
//            if(inf.get(i).className != null){
//                inf1.add(inf.get(i).className);
//
//            }
//
//        }
//        Collections.sort(inf1);

        String[] to = {correo}; //aquí pon tu correo


        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
//        emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);


        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "TU ASUNTO");
        emailIntent.putExtra(Intent.EXTRA_TEXT, nombre.getText() + "\n" + apellido.getText() +
                "\n" + mail.getText() + "\n"+ telefono.getText() + "\n" + campoMensaje.getText() );

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

//            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext().getApplicationContext(),
                    "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
        }

        limpiarCampos();

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
