package com.example.sergio.nuevo.vistas.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorCronProg;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorNoticia;
import com.example.sergio.nuevo.aplicacion.patrones.Servicio;
import com.example.sergio.nuevo.dominio.CronogramaProgresar;
import com.example.sergio.nuevo.dominio.Noticia;
import com.example.sergio.nuevo.dominio.PagEmpleo;
import com.example.sergio.nuevo.persistencia.ServicioCronProg;
import com.example.sergio.nuevo.persistencia.ServicioNoticias;

import java.util.ArrayList;


public class TabCronProgresar extends Fragment {
    private ArrayList<CronogramaProgresar> cronProg = new ArrayList<>();
    private Servicio s;
    private ListView listView;
    private AdaptadorCronProg listAdapter;
    private ServicioCronProg prog;
    private Button boton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cronopro, container, false);
        s = new Servicio();
        s.Clase(PagEmpleo.getInstance());

        prog = new ServicioCronProg(this.getActivity());
        cronProg = prog.levantarNoticias();

        listView = (ListView) v.findViewById(R.id.listCronProg);
        listAdapter = new AdaptadorCronProg(this.getActivity(),cronProg);
        listView.setAdapter(listAdapter);
        boton = (Button)getActivity().findViewById(R.id.btnLeerMas);

        // Inflate the layout for this fragment
        return v;
    }
}
