package com.example.sergio.nuevo.presentacion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorResLiquidaciones;
import com.example.sergio.nuevo.dominio.ProgresarConsulta;
import com.example.sergio.nuevo.presentacion.caracteristicas.Transicion;
import com.example.sergio.nuevo.presentacion.presentador.IPresentador;
import com.example.sergio.nuevo.presentacion.presentador.PresentadorConsultaLiquidacion;
import com.example.sergio.nuevo.presentacion.vistas.ConsultaLiquidacion;
import com.example.sergio.nuevo.presentacion.vistas.VistaNoticias;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadoLiquidaciones extends Fragment {
    private List<List<String>> datos;
    PresentadorConsultaLiquidacion presentadorConsultaLiquidacion;

    public List<List<String>> getDatos() {
        return datos;
    }

    public void setDatos(List<List<String>> datos) {
        this.datos = datos;
    }

    public PresentadorConsultaLiquidacion getPresentadorConsultaLiquidacion() {
        return presentadorConsultaLiquidacion;
    }

    public void setPresentadorConsultaLiquidacion(PresentadorConsultaLiquidacion presentadorConsultaLiquidacion) {
        this.presentadorConsultaLiquidacion = presentadorConsultaLiquidacion;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_resultado_liquidaciones, container, false);
        ListView listview = (ListView) v.findViewById(R.id.grilla);
        listview.setAdapter(new AdaptadorResLiquidaciones(this.getActivity(),datos));
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
        presentadorConsultaLiquidacion.onResume();
    }
}
