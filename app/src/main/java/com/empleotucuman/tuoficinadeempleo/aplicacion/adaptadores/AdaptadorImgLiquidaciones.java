package com.empleotucuman.tuoficinadeempleo.aplicacion.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.nuevo.R;
import com.empleotucuman.tuoficinadeempleo.presentacion.caracteristicas.Transicion;
import com.empleotucuman.tuoficinadeempleo.servicios.ServicioCompartir;

import java.io.File;
import java.util.List;

/**
 * Created by Operador1 on 06/11/2017.
 */

public class AdaptadorImgLiquidaciones extends BaseAdapter {
    private Context context;
    private List<List> resource;
    private static SparseArray<Bitmap> imagenesEscaladas = new SparseArray<Bitmap>(7);

    public AdaptadorImgLiquidaciones(@NonNull Context context, @NonNull List<List> resource) {
        this.context = context;
        this.resource = resource;
    }

    class Holder
    {
        ImageView image;

        TextView textView;

        Button btnBorrarImg;

        Button btnCompartir;

        public Button getBtnBorrarImg() {
            return btnBorrarImg;
        }

        public void setBtnBorrarImg(Button btnBorrarImg) {
            this.btnBorrarImg = btnBorrarImg;
        }

        public Button getBtnCompartir() {
            return btnCompartir;
        }

        public void setBtnCompartir(Button btnCompartir) {
            this.btnCompartir = btnCompartir;
        }

        public ImageView getImage()
        {
            return image;
        }

        public void setImage(ImageView image)
        {
            this.image = image;
        }

        public TextView getTextView()
        {
            return textView;
        }

        public void setTextView(TextView textView)
        {
            this.textView = textView;
        }

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

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder = null;

        if (convertView == null)
        {
            holder = new Holder();
            LayoutInflater ltInflate = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView = ltInflate.inflate(R.layout.imagen_resultados_liquidaciones, null);

            holder.setTextView((TextView) convertView.findViewById(R.id.textView4));
            holder.setImage((ImageView) convertView.findViewById(R.id.imageView4));
            holder.setBtnBorrarImg((Button) convertView.findViewById(R.id.btnBorrarImg));
            holder.setBtnCompartir((Button) convertView.findViewById(R.id.btnCompartirImg));

            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }


        if (imagenesEscaladas.get(position) == null)
        {
            imagenesEscaladas.put(position, (Bitmap) resource.get(position).get(0));
        }

        holder.getImage().setImageBitmap(imagenesEscaladas.get(position));
        holder.getTextView().setText((String)resource.get(position).get(1));
        holder.getBtnBorrarImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File directorioimg = new File(Environment.getExternalStorageDirectory() + "/SSE/resultados/"+resource.get(position).get(1));
                directorioimg.delete();
                resource.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.getBtnCompartir().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File directorioimg = new File(Environment.getExternalStorageDirectory() + "/SSE/resultados/"+resource.get(position).get(1));
                ServicioCompartir.compartirImagen(directorioimg,context);
            }
        });


        return convertView;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

}
