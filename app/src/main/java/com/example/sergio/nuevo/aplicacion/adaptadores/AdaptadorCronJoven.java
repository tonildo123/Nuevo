package com.example.sergio.nuevo.aplicacion.adaptadores;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.CronogramaJoven;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operador1 on 25/08/2017.
 */

public class AdaptadorCronJoven extends ArrayAdapter<List<List>> {
    private Activity activity;
    ArrayList<CronogramaJoven> cronJoven;

    public AdaptadorCronJoven(@NonNull Activity activity, @NonNull ArrayList<CronogramaJoven> cronJoven) {
        super(activity, R.layout.noticia);
        this.cronJoven = cronJoven;
        this.activity = activity;
    }
    static class ViewHolder {
        protected TextView dni;
        protected TextView fecha;
    }

    public int getCount() {
        return cronJoven.size();
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
        view = inflator.inflate(R.layout.listcronograma, null);
        final ViewHolder viewHolder = new ViewHolder();

        // *** instanciamos a los recursos
        viewHolder.dni = (TextView) view.findViewById(R.id.dnicronprog);
        viewHolder.fecha = (TextView)view.findViewById(R.id.fechacronprog);

        viewHolder.dni.setTextSize(16);
        viewHolder.fecha.setTextSize(16);

        // importante!!! establecemos el mensaje
        viewHolder.dni.setText(cronJoven.get(position).getTerminacionDni());
        viewHolder.fecha.setText(cronJoven.get(position).getFecha());

        return view;
    }

}
