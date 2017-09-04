package com.example.sergio.nuevo.aplicacion.adaptadores;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.CronogramaProgresar;
import com.example.sergio.nuevo.dominio.Noticia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operador1 on 25/08/2017.
 */

public class AdaptadorCronProg extends ArrayAdapter<List<List>> {
    private Activity activity;
    ArrayList<CronogramaProgresar> cronProg;

    public AdaptadorCronProg(@NonNull Activity activity, @NonNull ArrayList<CronogramaProgresar> cronProg) {
        super(activity, R.layout.noticia);
        this.cronProg = cronProg;
        this.activity = activity;
    }
    static class ViewHolder {
        protected TextView dni;
        protected TextView fecha;
    }

    public int getCount() {
        return cronProg.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView,
                        final ViewGroup parent) {
        // inflamos nuestra vista con el layout
        View view = null;
        LayoutInflater inflator = activity.getLayoutInflater();
        view = inflator.inflate(R.layout.listcronprogresar, null);
        final ViewHolder viewHolder = new ViewHolder();

        // *** instanciamos a los recursos
        viewHolder.dni = (TextView) view
                .findViewById(R.id.dnicronprog);
        viewHolder.fecha = (TextView)view.findViewById(R.id.fechacronprog);

        // importante!!! establecemos el mensaje
        viewHolder.dni.setText(cronProg.get(position).getTerminacionDni());
        viewHolder.fecha.setText(cronProg.get(position).getFecha());

        return view;
    }

}
