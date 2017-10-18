package com.example.sergio.nuevo.presentacion.vistas;

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

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.A;
import com.example.sergio.nuevo.presentacion.ResultadoLiquidaciones;
import com.example.sergio.nuevo.presentacion.caracteristicas.Transicion;
import com.example.sergio.nuevo.presentacion.presentador.IPresentador;
import com.example.sergio.nuevo.presentacion.presentador.PresentadorConsultaLiquidacion;
import com.example.sergio.nuevo.presentacion.presentador.PresentadorConsultaLiquidacionImpl;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progresar_consultaliq,container,false);
        etCuil = (EditText)view.findViewById(R.id.etCuil);
        etCaptcha = (EditText)view.findViewById(R.id.etCaptcha);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar2);
        consultar = (Button) view.findViewById(R.id.bConsulta);
        consultar.setOnClickListener(this);
        btnCaptcha = (Button) view.findViewById(R.id.btnactcaptcha);
        btnCaptcha.setOnClickListener(this);
        presentadorConsultaLiquidacion = new PresentadorConsultaLiquidacionImpl(getActivity(),this,progressBar);

        // Inflate the layout for this fragment
        return view;
    }
    public void cargarResultados(List datos){
        if(datos != null){
            ResultadoLiquidaciones res = new ResultadoLiquidaciones(datos,presentadorConsultaLiquidacion);
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

    }@Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
        presentadorConsultaLiquidacion.onResume();
    }

    @Override
    public void setAdapter(ArrayList list) {

    }
}
