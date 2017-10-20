package com.example.sergio.nuevo.presentacion.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorCronProg;
import com.example.sergio.nuevo.persistencia.PersisCronProg;
import com.example.sergio.nuevo.presentacion.caracteristicas.Transicion;

import java.util.ArrayList;


public class TabCronProgresar extends Fragment {
//    private ArrayList<CronogramaProgresar> cronProg = new ArrayList<>();
    private ListView listView;
    private AdaptadorCronProg listAdapter;
    private PersisCronProg prog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cronopro, container, false);

        prog = new PersisCronProg(this.getActivity());
        ArrayList progresar = prog.levantar();

        if(progresar != null){
            listView = (ListView) v.findViewById(R.id.listCronProg);
            listAdapter = new AdaptadorCronProg(this.getActivity(),progresar);
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
