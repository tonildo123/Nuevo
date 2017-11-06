package com.example.sergio.nuevo.presentacion.vistas;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorImgLiquidaciones;
import com.example.sergio.nuevo.presentacion.presentador.PresentadorConsultaLiquidacion;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadosLiquidacionesImgImpl extends Fragment {
    private View view;
    private ListView listView;
    private PresentadorConsultaLiquidacion presentadorConsultaLiquidacion;

    public ResultadosLiquidacionesImgImpl(PresentadorConsultaLiquidacion presentadorConsultaLiquidacion) {
        this.presentadorConsultaLiquidacion = presentadorConsultaLiquidacion;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_resultados_liquidaciones_imp_impl, container, false);
        listView = view.findViewById(R.id.listImgResultadosLiquidaciones);
        ArrayList<Bitmap> imagenes = presentadorConsultaLiquidacion.mostrarImagenesResultados();
        if(imagenes.size() > 0){
            AdaptadorImgLiquidaciones liquidaciones = new AdaptadorImgLiquidaciones(getActivity(),imagenes);
            listView.setAdapter(liquidaciones);
        }
        // Inflate the layout for this fragment
        return view;
    }

}
