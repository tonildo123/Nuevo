package com.example.sergio.nuevo.vistas.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorCronJoven;
import com.example.sergio.nuevo.aplicacion.patrones.Servicio;
import com.example.sergio.nuevo.dominio.CronogramaJoven;
import com.example.sergio.nuevo.aplicacion.network.ServicioPagEmpleo;
import com.example.sergio.nuevo.persistencia.PersisCronJoven;
import com.example.sergio.nuevo.vistas.VistaNoticias;
import com.example.sergio.nuevo.vistas.caracteristicas.Transicion;

import java.util.ArrayList;


public class TabCronJoven extends Fragment {
    private ArrayList<CronogramaJoven> cronJoven = new ArrayList<>();
    private Servicio s;
    private ListView listView;
    private AdaptadorCronJoven listAdapter;
    private PersisCronJoven joven;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cronojov, container, false);
        s = new Servicio();
        s.Clase(ServicioPagEmpleo.getInstance());

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearlayout);
        Transicion.getInstance().animarLinearLayout(linearLayout, 0);

        joven = new PersisCronJoven(this.getActivity());
        cronJoven = joven.levantarNoticias();

        if(cronJoven != null){
            listView = (ListView) v.findViewById(R.id.listCronJov);
            listAdapter = new AdaptadorCronJoven(this.getActivity(), cronJoven);
            listView.setAdapter(listAdapter);
        }
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }
}
