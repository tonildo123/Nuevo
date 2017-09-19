package com.example.sergio.nuevo.vistas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorNoticia;
import com.example.sergio.nuevo.aplicacion.patrones.Servicio;
import com.example.sergio.nuevo.persistencia.PersisNoticias;
import com.example.sergio.nuevo.aplicacion.network.ServicioPagEmpleo;
import com.example.sergio.nuevo.dominio.Noticia;

import java.util.ArrayList;


public class VistaNoticias extends Fragment {
    private static final VistaNoticias vnoticia = new VistaNoticias();
    private ArrayList<Noticia> noticias = new ArrayList<>();
    private Servicio s;
    private ListView listView;
    private AdaptadorNoticia listAdapter;
    private PersisNoticias not;
    private Activity activityMain;

    public static VistaNoticias getInstance(){return vnoticia;}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_noticias, container, false);
//        Intent intent = new Intent(getActivity(), );
        s = new Servicio();
        s.Clase(ServicioPagEmpleo.getInstance());

        not = new PersisNoticias(this.getActivity());
        noticias = not.levantarNoticias();

        listView = (ListView) v.findViewById(R.id.list);
        listAdapter = new AdaptadorNoticia(getActivity(),noticias,this.getContext());
        listView.setAdapter(listAdapter);

        // Inflate the layout for this fragment
        return v;
    }
    public void setActivityMain(Activity activityMain) {
        this.activityMain = activityMain;
    }

    }
