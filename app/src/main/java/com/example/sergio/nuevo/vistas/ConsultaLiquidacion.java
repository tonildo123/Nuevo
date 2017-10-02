package com.example.sergio.nuevo.vistas;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.ProgresarConsulta;
import com.example.sergio.nuevo.vistas.caracteristicas.Transicion;

import java.util.ArrayList;
import java.util.List;


public class ConsultaLiquidacion extends Fragment implements OnClickListener {

    private Button consultar;
    private EditText etCuil;
    private EditText etCaptcha;
    private static final ConsultaLiquidacion consulta = new ConsultaLiquidacion();
    private Button btnCaptcha;
    public static ConsultaLiquidacion getInstance(){
        return consulta;
    }
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progresar_consultaliq,container,false);
        etCuil = (EditText)view.findViewById(R.id.etCuil);
        etCaptcha = (EditText)view.findViewById(R.id.etCaptcha);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar2);

        ProgresarConsulta.getInstance().getCaptcha(getActivity().findViewById(android.R.id.content),progressBar);

        consultar = (Button) view.findViewById(R.id.bConsulta);
        consultar.setOnClickListener(this);
        btnCaptcha = (Button) view.findViewById(R.id.btnactcaptcha);
        btnCaptcha.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }
    public void cargarResultados(){
        ResultadoLiquidaciones res = new ResultadoLiquidaciones();
        FragmentManager m = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = m.beginTransaction().replace(R.id.contenedor, res);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
// Start the animated transition.
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bConsulta:
                ProgresarConsulta.getInstance().enviarDatos(etCaptcha.getText().toString(),etCuil.getText().toString(),this);
                break;
            case R.id.btnactcaptcha:
                ProgresarConsulta.getInstance().getCaptcha(getActivity().findViewById(android.R.id.content),progressBar);
                break;
        }
    }@Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }

}
