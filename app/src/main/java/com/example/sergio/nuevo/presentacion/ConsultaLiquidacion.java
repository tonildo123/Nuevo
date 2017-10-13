package com.example.sergio.nuevo.presentacion;

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
import com.example.sergio.nuevo.dominio.ProgresarConsulta;
import com.example.sergio.nuevo.presentacion.caracteristicas.Transicion;

import java.util.List;

public class ConsultaLiquidacion extends Fragment implements OnClickListener,A {

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
        List<List<String>> datos = ProgresarConsulta.getInstance().obtenerDatos();
        if(datos != null){
            ResultadoLiquidaciones res = new ResultadoLiquidaciones(datos);
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
        switch (view.getId()){
            case R.id.bConsulta:
                if(etCaptcha.getText().toString().length() == 3 && etCuil.getText().toString().length() == 11){
                    ProgresarConsulta.getInstance().enviarDatos(etCaptcha.getText().toString(),etCuil.getText().toString(),this);
                }else{
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Revise que los datos ingresados tengan la longitud correcta", Snackbar.LENGTH_LONG).show();
                }
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
