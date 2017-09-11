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
import com.example.sergio.nuevo.dominio.h3;
import com.example.sergio.nuevo.dominio.li;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operador1 on 25/08/2017.
 */

public class AdaptadorH3 extends ArrayAdapter<List<List>> {
    private Activity activity;
    ArrayList<li> item;

    public AdaptadorH3(@NonNull Activity activity, @NonNull ArrayList<li> item) {
        super(activity, R.layout.noticia);
        this.item = item;
        this.activity = activity;
    }
    static class ViewHolder {
        protected TextView li;
    }

    public int getCount() {
        return item.size();
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
        view = inflator.inflate(R.layout.text_view, null);
        final ViewHolder viewHolder = new ViewHolder();

        // *** instanciamos a los recursos
        viewHolder.li = (TextView) view.findViewById(R.id.item);

        viewHolder.li.setTextSize(14);

        // importante!!! establecemos el mensaje
        viewHolder.li.setText(item.get(position).getItem());

        return view;
    }

}
