package com.example.sergio.nuevo.vistas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorResLiquidaciones;
import com.example.sergio.nuevo.aplicacion.network.Progresarm;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadoLiquidaciones extends Fragment {
    private Progresarm prog = Progresarm.getInstance();


    public ResultadoLiquidaciones() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_resultado_liquidaciones, container, false);
        ListView listview = (ListView) v.findViewById(R.id.grilla);
        listview.setAdapter(new AdaptadorResLiquidaciones(this.getActivity(),prog.obtenerDatos()));
        // Inflate the layout for this fragment
        return v;
    }

}
