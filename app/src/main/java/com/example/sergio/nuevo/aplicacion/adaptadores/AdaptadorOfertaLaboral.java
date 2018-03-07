package com.example.sergio.nuevo.aplicacion.adaptadores;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;

import com.example.sergio.nuevo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PROGRAMACION5 on 07/03/2018.
 */

public class AdaptadorOfertaLaboral extends ArrayAdapter<List<List>>{

    private Activity activity;
    ArrayList<String> lista;

    public AdaptadorOfertaLaboral(FragmentActivity activity, ArrayList<String>lista ) {
        super(activity, R.layout.ofertalaboral);




    }
}
