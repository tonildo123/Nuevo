package com.example.sergio.nuevo.vistas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorNoticia;
import com.example.sergio.nuevo.aplicacion.patrones.Servicio;
import com.example.sergio.nuevo.aplicacion.servicios.ServicioNoticias;
import com.example.sergio.nuevo.dominio.PagEmpleo;
import com.example.sergio.nuevo.dominio.Noticia;

import java.util.ArrayList;


public class VistaNoticias extends Fragment {
    private ArrayList<Noticia> noticias = new ArrayList<>();
    private Servicio s;
    private ListView listView;
    private AdaptadorNoticia listAdapter;
    private ServicioNoticias not;
    private Button boton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_noticias, container, false);
        s = new Servicio();
        s.Clase(PagEmpleo.getInstance());

        not = new ServicioNoticias(this.getActivity());
        noticias = not.levantarNoticias();

        listView = (ListView) v.findViewById(R.id.list);
        listAdapter = new AdaptadorNoticia(this.getActivity(),noticias);
        listView.setAdapter(listAdapter);
        boton = (Button)getActivity().findViewById(R.id.btnLeerMas);

        // Inflate the layout for this fragment
        return v;
    }

    public void llamarNoticia(){
        FragmentWebView fweb = new FragmentWebView();
        FragmentManager m = getActivity().getSupportFragmentManager();
        m.beginTransaction().replace(R.id.contenedor, fweb).commit();

    }

    }
