package com.example.sergio.nuevo.presentacion.vistas;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import com.getbase.floatingactionbutton.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.adaptadores.AdaptadorResLiquidaciones;
import com.example.sergio.nuevo.presentacion.caracteristicas.Transicion;
import com.example.sergio.nuevo.presentacion.presentador.PresentadorConsultaLiquidacion;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadoLiquidaciones extends Fragment implements OnClickListener{
    private List<List<String>> datos;
    private PresentadorConsultaLiquidacion presentadorConsultaLiquidacion;
    private FloatingActionButton btnGuardarImagen;
    private FloatingActionButton btnMostrarResultadosGuerdados;
    private View v;
    private ListView listview;

    public void setDatos(List<List<String>> datos) {
        this.datos = datos;
    }

    public void setPresentadorConsultaLiquidacion(PresentadorConsultaLiquidacion presentadorConsultaLiquidacion) {
        this.presentadorConsultaLiquidacion = presentadorConsultaLiquidacion;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_resultado_liquidaciones, container, false);
        listview = (ListView) v.findViewById(R.id.grilla);
        listview.setAdapter(new AdaptadorResLiquidaciones(this.getActivity(),datos));
        btnGuardarImagen = (FloatingActionButton)v.findViewById(R.id.FABguardarImagen);
        btnMostrarResultadosGuerdados = (FloatingActionButton)v.findViewById(R.id.FABMostrarResultados);

        btnGuardarImagen.setOnClickListener(this);
        btnMostrarResultadosGuerdados.setOnClickListener(this);
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
        presentadorConsultaLiquidacion.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.FABguardarImagen:
                listview.setDrawingCacheEnabled(true);
                listview.buildDrawingCache();
                listview.setDrawingCacheBackgroundColor(Color.WHITE);
                Bitmap bitmap = Bitmap.createBitmap(listview.getDrawingCache());
                listview.setDrawingCacheEnabled(false);
                presentadorConsultaLiquidacion.guardarImagenResultado(bitmap);
                break;
            case R.id.FABMostrarResultados:
                FragmentManager m = getFragmentManager();
                ResultadosLiquidacionesImgImpl resultadosLiquidacionesImg = new ResultadosLiquidacionesImgImpl(presentadorConsultaLiquidacion);
                m.beginTransaction().replace(R.id.contenedor,resultadosLiquidacionesImg).commit();
                break;
        }
    }
}
