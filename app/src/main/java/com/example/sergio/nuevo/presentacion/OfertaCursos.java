package com.example.sergio.nuevo.presentacion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorCursos;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorOfertaLaboral;
import com.example.sergio.nuevo.aplicacion.network.ServicioCursos;
import com.example.sergio.nuevo.aplicacion.network.servicioOfertaLaboral;
import com.example.sergio.nuevo.dominio.A;
import com.example.sergio.nuevo.persistencia.DBTuOficinaDeEmpleo;
import com.example.sergio.nuevo.presentacion.caracteristicas.Transicion;

import java.util.ArrayList;


public class OfertaCursos extends Fragment implements A {
    private Spinner localidad;
    private Button ir;

    private ServicioCursos serv;
    private ArrayList<String> lista = new ArrayList();
    private ListView listView;
    private AdaptadorCursos listAdapter;
    private DBTuOficinaDeEmpleo dbOferta;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_cursos, container, false);

        localidad = (Spinner)vista.findViewById(R.id.spinnerLocalidad);
        ir = (Button) vista.findViewById(R.id.butonIr);

        final String[] valores= new String[]{"San Miguel de Tucuman","Yerba Buena","Banda del Rio Sali","Lules","Tafi Viejo"};
        ArrayAdapter<String> adaptadorSpinner = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, valores){};
        localidad.setAdapter(adaptadorSpinner);


        ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return vista;
    }

    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }
}
