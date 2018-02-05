package com.example.sergio.nuevo.presentacion.vistas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.nuevo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BotonesMenu extends Fragment {


    public BotonesMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.botonesmenus, container, false);
    }

}
