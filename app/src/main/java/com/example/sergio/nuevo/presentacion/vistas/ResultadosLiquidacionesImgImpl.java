package com.example.sergio.nuevo.presentacion.vistas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorImgLiquidaciones;
import com.example.sergio.nuevo.presentacion.caracteristicas.Transicion;
import com.example.sergio.nuevo.presentacion.presentador.PresentadorConsultaLiquidacion;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadosLiquidacionesImgImpl extends Fragment {
    private View view;
    private PresentadorConsultaLiquidacion presentadorConsultaLiquidacion;
    private List<List> imagenes;
    private GridView viewPager;
    private FloatingActionButton salir;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_resultados_liquidaciones_img_impl, container, false);
        viewPager = view.findViewById(R.id.gridView);
        salir = view.findViewById(R.id.FABSalir);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultaLiquidacion consultaLiquidacion = new ConsultaLiquidacion();
                FragmentManager m = getFragmentManager();
                m.beginTransaction().replace(R.id.contenedor,consultaLiquidacion).commit();
            }
        });

        imagenes = presentadorConsultaLiquidacion.mostrarImagenesResultados();
        if(imagenes.size() > 0){
            AdaptadorImgLiquidaciones adaptadorImgLiquidaciones = new AdaptadorImgLiquidaciones(getContext(),imagenes);
            viewPager.setAdapter(adaptadorImgLiquidaciones);
        }
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
        presentadorConsultaLiquidacion.onResume();
    }

    public void setPresentador(PresentadorConsultaLiquidacion presentador) {
        this.presentadorConsultaLiquidacion = presentador;
    }
}
