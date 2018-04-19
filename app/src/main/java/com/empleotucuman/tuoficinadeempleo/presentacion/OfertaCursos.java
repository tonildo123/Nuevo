package com.empleotucuman.tuoficinadeempleo.presentacion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.empleotucuman.tuoficinadeempleo.R;
import com.empleotucuman.tuoficinadeempleo.aplicacion.adaptadores.AdaptadorCursos;
import com.empleotucuman.tuoficinadeempleo.aplicacion.adaptadores.AdaptadorOfertaLaboral;
import com.empleotucuman.tuoficinadeempleo.aplicacion.network.ServicioCursos;
import com.empleotucuman.tuoficinadeempleo.aplicacion.network.servicioOfertaLaboral;
import com.empleotucuman.tuoficinadeempleo.dominio.A;
import com.empleotucuman.tuoficinadeempleo.persistencia.DBTuOficinaDeEmpleo;
import com.empleotucuman.tuoficinadeempleo.presentacion.caracteristicas.Transicion;

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
        ir = (Button) vista.findViewById(R.id.butonIR);

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
