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

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.network.Progresarm;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progresar_c,container,false);
        img = (ImageView)view.findViewById(R.id.ivCaptcha);
        etCuil = (EditText)view.findViewById(R.id.etCuil);
        etCaptcha = (EditText)view.findViewById(R.id.etCaptcha);

        consultar = (Button) view.findViewById(R.id.bConsulta);
        consultar.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
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
    }
    @Override
    public void onResume() {
        super.onResume();
        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK){
                    // handle back button's click listener
                    FragmentManager m = getActivity().getSupportFragmentManager();
                    m.beginTransaction().replace(R.id.contenedor, VistaNoticias.getInstance()).commit();
                    return true;
                }
                return false;
            }
        });
    }
}
