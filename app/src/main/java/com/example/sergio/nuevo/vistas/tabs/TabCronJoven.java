package com.example.sergio.nuevo.vistas.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorCronJoven;
import com.example.sergio.nuevo.aplicacion.patrones.Servicio;
import com.example.sergio.nuevo.dominio.CronogramaJoven;
import com.example.sergio.nuevo.aplicacion.servicios.ServicioPagEmpleo;
import com.example.sergio.nuevo.persistencia.PersisCronJoven;

import java.util.ArrayList;


public class TabCronJoven extends Fragment {
    private ArrayList<CronogramaJoven> cronJoven = new ArrayList<>();
    private Servicio s;
    private ListView listView;
    private AdaptadorCronJoven listAdapter;
    private PersisCronJoven joven;
    private Button boton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cronojov, container, false);
        s = new Servicio();
        s.Clase(ServicioPagEmpleo.getInstance());

        joven = new PersisCronJoven(this.getActivity());
        cronJoven = joven.levantarNoticias();

        listView = (ListView) v.findViewById(R.id.listCronJov);
        listAdapter = new AdaptadorCronJoven(this.getActivity(),cronJoven);
        listView.setAdapter(listAdapter);
        boton = (Button)getActivity().findViewById(R.id.btnLeerMas);

        // Inflate the layout for this fragment
        return v;
    }
}
