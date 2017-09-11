package com.example.sergio.nuevo.vistas.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorCronProg;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorReqJoven;
import com.example.sergio.nuevo.dominio.ProgramaJoven;
import com.example.sergio.nuevo.persistencia.PersisReqJoven;


public class TabReqJoven extends Fragment {
    private PersisReqJoven reqJoven;
    private AdaptadorReqJoven listAdapter;
    private ProgramaJoven joven;
    private ListView list;
    private TextView titulo;
    private TextView objetivo;
    private ImageView imagen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_jovenes_requisitos, container, false);
        titulo = v.findViewById(R.id.tituloReqJoven);
        objetivo = v.findViewById(R.id.objetivoReqJoven);
        list = (ListView) v.findViewById(R.id.listrequisitos);
        imagen = v.findViewById(R.id.imgRequisitos);
        reqJoven = new PersisReqJoven(this.getActivity());
        joven = reqJoven.levantarNoticias();
        titulo.setText(joven.getTitulo());
        objetivo.setText("OBJETIVO: " +
                ""+joven.getObjetivo());
        imagen.setImageBitmap(joven.getImg());
        listAdapter = new AdaptadorReqJoven(this.getActivity(),joven.getH3());
        list.setAdapter(listAdapter);
        return v;
    }
}
