package com.example.sergio.nuevo.vistas;

import android.content.Context;
import android.icu.util.RangeValueIterator;
import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity.*;
import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.presentador.MainActivity;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Noticias extends Fragment {
    private String url="http://181.14.240.59/Portal/wp-content/uploads/2017/08/Imagen11.jpeg", descripcion;
    private ImageView img;
    private TextView tvd;
    private Element tomarDiv;
    private Elements parrafos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        img = (ImageView)getActivity().findViewById(R.id.imagen);
        tvd = (TextView)getActivity().findViewById(R.id.tvDescripcion);

//        Picasso.with(getActivity().getApplication()).
//                load(url).into(img);
//

        return inflater.inflate(R.layout.fragment_noticias, container, false);
    }


    }
