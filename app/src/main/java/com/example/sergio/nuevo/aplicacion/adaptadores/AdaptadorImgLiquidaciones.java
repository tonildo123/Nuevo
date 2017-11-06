package com.example.sergio.nuevo.aplicacion.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.os.Environment;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.sergio.nuevo.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Operador1 on 06/11/2017.
 */

public class AdaptadorImgLiquidaciones extends ArrayAdapter {
    private Activity activity;
    private ArrayList<Bitmap> resource;

    public AdaptadorImgLiquidaciones(@NonNull Activity activity, @NonNull ArrayList<Bitmap> resource) {
        super(activity, R.layout.imagen_resultados_liquidaciones);
        this.activity = activity;
        this.resource = resource;

    }

    static class ViewHolder{
        protected ImageView imageView;
    }

    @Override
    public int getCount() {
        return resource.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = null;
        LayoutInflater inflator = activity.getLayoutInflater();
        view = inflator.inflate(R.layout.imagen_resultados_liquidaciones, null);
        final ViewHolder viewHolder = new ViewHolder();

        viewHolder.imageView = view.findViewById(R.id.imageView4);

        viewHolder.imageView.setImageBitmap(resource.get(position));

        return view;
    }

}
