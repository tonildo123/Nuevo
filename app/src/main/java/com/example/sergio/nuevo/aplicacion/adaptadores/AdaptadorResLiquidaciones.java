package com.example.sergio.nuevo.aplicacion.adaptadores;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sergio.nuevo.R;

import java.util.List;

/**
 * Created by Operador1 on 25/08/2017.
 */

public class AdaptadorResLiquidaciones extends ArrayAdapter<List<List>> {
    private Activity activity;
    List<List<String>> result;

    public AdaptadorResLiquidaciones(@NonNull Activity activity, @NonNull List<List<String>> result) {
        super(activity, R.layout.datosbeneficiario);
        this.result = result;
        this.activity = activity;
    }
    static class ViewHolder {
        protected TextView tv1;
        protected TextView tv2;
    }

    public int getCount() {
        return result.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView,
                        final ViewGroup parent) {
        View view = null;
            // inflamos nuestra vista con el layout

            LayoutInflater inflator = activity.getLayoutInflater();
            view = inflator.inflate(R.layout.datosbeneficiario, null);
            final ViewHolder viewHolder = new ViewHolder();

            // *** instanciamos a los recursos
            viewHolder.tv1 = (TextView) view.findViewById(R.id.atributo);
            viewHolder.tv2 = (TextView) view.findViewById(R.id.valor);

            // importante!!! establecemos el mensaje
            viewHolder.tv1.setText(result.get(position).get(0));
            viewHolder.tv2.setText(result.get(position).get(1));

        return view;
    }

}
