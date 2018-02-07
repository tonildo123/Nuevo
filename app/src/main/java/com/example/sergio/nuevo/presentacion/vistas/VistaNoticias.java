package com.example.sergio.nuevo.presentacion.vistas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorNoticia;
import com.example.sergio.nuevo.dominio.A;
import com.example.sergio.nuevo.presentacion.caracteristicas.Transicion;
import com.example.sergio.nuevo.presentacion.presentador.PresentadorNoticiaImpl;

import java.util.ArrayList;


public class VistaNoticias extends Fragment implements A,ViewFragment {
    private static final VistaNoticias vnoticia = new VistaNoticias();
    private ListView listView;
    private AdaptadorNoticia listAdapter;
    private View v;
    private PresentadorNoticiaImpl presentadorNoticia;

    public static VistaNoticias getInstance(){return vnoticia;}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_noticias, container, false);
        listView = (ListView) v.findViewById(R.id.list);
        presentadorNoticia = new PresentadorNoticiaImpl(this,getActivity());
        presentadorNoticia.iniciar();
        // Inflate the layout for this fragment
        return v;
    }


    @Override
    public void setAdapter(ArrayList noticias) {
        if(noticias != null){
            listAdapter = new AdaptadorNoticia(getActivity(),noticias);
            listView.setAdapter(listAdapter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presentadorNoticia.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }
}
