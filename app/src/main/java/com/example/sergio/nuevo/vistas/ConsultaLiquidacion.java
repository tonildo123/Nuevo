package com.example.sergio.nuevo.vistas;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.network.Progresarm;
import com.example.sergio.nuevo.vistas.caracteristicas.Transicion;

import java.util.ArrayList;
import java.util.List;


public class ConsultaLiquidacion extends Fragment implements OnClickListener {

    private Button consultar;
    private ImageView img;
    private Bitmap bit;
    private EditText etCuil;
    private EditText etCaptcha;
    private List<List<String>> resultado = new ArrayList<>();
    private static final ConsultaLiquidacion consulta = new ConsultaLiquidacion();
    private Button btnCaptcha;
    public static ConsultaLiquidacion getInstance(){
        return consulta;
    }

    public void cargarCaptcha(){
        Thread hilo1 = new Thread(){
            @Override
            public void run() {
                try {
                    bit = Progresarm.getInstance().getCaptcha(getActivity().findViewById(android.R.id.content));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        hilo1.start();
        try {
            hilo1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        img.setImageBitmap(bit);
        etCaptcha.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progresar_c,container,false);
        img = (ImageView)view.findViewById(R.id.ivCaptcha);
        etCuil = (EditText)view.findViewById(R.id.etCuil);
        etCaptcha = (EditText)view.findViewById(R.id.etCaptcha);
        cargarCaptcha();

        consultar = (Button) view.findViewById(R.id.bConsulta);
        consultar.setOnClickListener(this);
        btnCaptcha = (Button) view.findViewById(R.id.btnactcaptcha);
        btnCaptcha.setOnClickListener(this);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearlayout);
        Transicion.getInstance().animarLinearLayout(linearLayout,0);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bConsulta:
                Thread hilo1 = new Thread(){
                    @Override
                    public void run() {
                        try {
                            Progresarm.getInstance().enviarDatos(etCaptcha.getText().toString(),etCuil.getText().toString());
                            resultado = Progresarm.getInstance().obtenerDatos();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                };
                hilo1.start();
                try {
                    hilo1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(resultado.size() > 0){
                    ResultadoLiquidaciones res = new ResultadoLiquidaciones();
                    FragmentManager m = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = m.beginTransaction().replace(R.id.contenedor, res);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
// Start the animated transition.
                    ft.commit();
                }
                break;
            case R.id.btnactcaptcha:
                cargarCaptcha();
                break;
        }
    }@Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }

}
