package com.empleotucuman.tuoficinadeempleo.presentacion.vistas;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.empleotucuman.tuoficinadeempleo.R;
import com.empleotucuman.tuoficinadeempleo.dominio.A;
import com.empleotucuman.tuoficinadeempleo.presentacion.caracteristicas.Transicion;
import com.empleotucuman.tuoficinadeempleo.presentacion.presentador.PresentadorConsultaLiquidacion;
import com.empleotucuman.tuoficinadeempleo.presentacion.presentador.PresentadorConsultaLiquidacionImpl;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ConsultaLiquidacion extends Fragment implements OnClickListener,A,ViewConsultaLiquidacion {

    private Button consultar;
    private EditText etCuil;
    private EditText etCaptcha;
    private static final ConsultaLiquidacion consulta = new ConsultaLiquidacion();
    private Button btnCaptcha;
    public static ConsultaLiquidacion getInstance(){
        return consulta;
    }
    private ProgressBar progressBar;
    private PresentadorConsultaLiquidacion presentadorConsultaLiquidacion;
    private FloatingActionButton btnMostrarResultadosGuerdados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progresar_consultaliq,container,false);
        etCuil = view.findViewById(R.id.etCuil);
        etCaptcha = view.findViewById(R.id.etCaptcha);
        progressBar = view.findViewById(R.id.progressBar2);
        consultar = view.findViewById(R.id.bConsulta);
        consultar.setOnClickListener(this);
        btnCaptcha = view.findViewById(R.id.btnactcaptcha);
        btnCaptcha.setOnClickListener(this);
        btnMostrarResultadosGuerdados = view.findViewById(R.id.FABMostrarResultados);
        btnMostrarResultadosGuerdados.setOnClickListener(this);
        presentadorConsultaLiquidacion = new PresentadorConsultaLiquidacionImpl(getActivity(),this,progressBar);

        // Inflate the layout for this fragment
        return view;
    }
    public void cargarResultados(List datos){
        if(datos != null){
            ResultadoLiquidaciones res = new ResultadoLiquidaciones();
            res.setDatos(datos);
            res.setPresentadorConsultaLiquidacion(presentadorConsultaLiquidacion);
            FragmentManager m = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = m.beginTransaction().replace(R.id.contenedor, res);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
// Start the animated transition.
            ft.commit();
        }else{
            Snackbar.make(getActivity().findViewById(android.R.id.content),"Revise que todos los datos ingresados esten correctos e intente mas tarde", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        Object[] o = new Object[4];
        o[0] = view;
        o[1] = etCaptcha;
        o[2] = etCuil;
        o[3] = progressBar;
        presentadorConsultaLiquidacion.onClick(o);
        switch (view.getId()){
            case R.id.FABMostrarResultados:
                FragmentManager m = getFragmentManager();
                ResultadosLiquidacionesImgImpl resultadosLiquidacionesImg = new ResultadosLiquidacionesImgImpl();
                resultadosLiquidacionesImg.setPresentador(presentadorConsultaLiquidacion);
                m.beginTransaction().replace(R.id.contenedor,resultadosLiquidacionesImg).commit();
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
        presentadorConsultaLiquidacion.onResume();
    }

    @Override
    public void setAdapter(ArrayList list) {

    }
}
