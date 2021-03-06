package com.empleotucuman.tuoficinadeempleo.presentacion.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.empleotucuman.tuoficinadeempleo.R;
import com.empleotucuman.tuoficinadeempleo.aplicacion.adaptadores.AdaptadorCronJoven;
import com.empleotucuman.tuoficinadeempleo.dominio.CronogramaJoven;
import com.empleotucuman.tuoficinadeempleo.persistencia.PersisCronJoven;
import com.empleotucuman.tuoficinadeempleo.presentacion.caracteristicas.Transicion;

import java.util.ArrayList;


public class TabCronJoven extends Fragment {
    private ArrayList<CronogramaJoven> cronJoven = new ArrayList<>();
    private ListView listView;
    private AdaptadorCronJoven listAdapter;
    private PersisCronJoven joven;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cronojov, container, false);

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearlayout);
        Transicion.getInstance().animarLinearLayout(linearLayout, 0);

        joven = new PersisCronJoven(this.getActivity());
        cronJoven = joven.levantar();

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
