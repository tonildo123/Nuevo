package com.example.sergio.nuevo.vistas;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.aplicacion.servicios.Progresarm;

import java.util.ArrayList;
import java.util.List;


public class ProgresarC extends Fragment implements OnClickListener {

    private Button consultar;
    private ImageView img;
    private Progresarm prog;
    private Bitmap bit;
    private EditText etCuil;
    private EditText etCaptcha;
    private List<List<String>> resultado = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progresar_c,container,false);
        img = (ImageView)view.findViewById(R.id.ivCaptcha);
        etCuil = (EditText)view.findViewById(R.id.etCuil);
        etCaptcha = (EditText)view.findViewById(R.id.etCaptcha);


        Thread hilo1 = new Thread(){
            @Override
            public void run() {
                try {
                    prog = Progresarm.getInstance();
                    bit = prog.getCaptcha();
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
                    prog.enviarDatos(etCaptcha.getText().toString(),etCuil.getText().toString());
                    resultado = prog.obtenerDatos();
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
            m.beginTransaction().replace(R.id.contenedor, res).commit();
        }
    }
}
