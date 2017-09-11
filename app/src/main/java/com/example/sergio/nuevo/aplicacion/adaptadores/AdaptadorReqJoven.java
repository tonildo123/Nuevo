package com.example.sergio.nuevo.aplicacion.adaptadores;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.CronogramaJoven;
import com.example.sergio.nuevo.dominio.h3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operador1 on 25/08/2017.
 */

public class AdaptadorReqJoven extends ArrayAdapter<List<List>> {
    private Activity activity;
    private ArrayList<h3> h3s;
    private AdaptadorH3 adaptadorH3;

    public AdaptadorReqJoven(@NonNull Activity activity, @NonNull ArrayList<h3> cronJoven) {
        super(activity, R.layout.noticia);
        this.h3s = cronJoven;
        this.activity = activity;
    }
    static class ViewHolder {
        protected TextView h3;
        protected ListView list;
    }

    public int getCount() {
        return h3s.size();
    }

    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public View getView(final int position, View convertView,
                        final ViewGroup parent) {
        // inflamos nuestra vista con el layout
        View view = null;
        LayoutInflater inflator = activity.getLayoutInflater();
        view = inflator.inflate(R.layout.list_requisitos, null);
        final ViewHolder viewHolder = new ViewHolder();

        // *** instanciamos a los recursos
        viewHolder.h3 = (TextView) view.findViewById(R.id.subtituloh3);
        viewHolder.list = (ListView) view.findViewById(R.id.listh3);

        viewHolder.h3.setTextSize(16);

        // importante!!! establecemos el mensaje
        viewHolder.h3.setText(h3s.get(position).getSubtitulo());
        adaptadorH3 = new AdaptadorH3(activity,h3s.get(position).getItem());
        viewHolder.list.setAdapter(adaptadorH3);

        return view;
    }

}
