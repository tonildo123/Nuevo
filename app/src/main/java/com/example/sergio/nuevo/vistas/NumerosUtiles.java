package com.example.sergio.nuevo.vistas;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.Marcadores;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumerosUtiles extends Fragment {


    private Button b1, b2, b3, b4;
   String numero;

    public NumerosUtiles() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista =  inflater.inflate(R.layout.fragment_numeros_utiles, container, false);

        b1 = (Button)vista.findViewById(R.id.buttonPolicia);
        b2 = (Button)vista.findViewById(R.id.buttonAmbulancia);
        b3 = (Button)vista.findViewById(R.id.buttonBomberos);
        b4 = (Button)vista.findViewById(R.id.buttonSecretaria);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent llamar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 911 "));
                    startActivity(llamar);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent llamar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 107 "));
                    startActivity(llamar);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent llamar = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 100 "));
                    startActivity(llamar);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent llamar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 911 "));
                    startActivity(llamar);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // Inflate the layout for this fragment
        return vista;
    }


}
