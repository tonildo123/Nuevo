package com.example.sergio.nuevo.presentacion.presentador;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Operador1 on 18/10/2017.
 */

public interface PresentadorConsultaLiquidacion extends IPresentador{
    void cargarResultados();

    void guardarImagenResultado(Bitmap bitmap);

    ArrayList<Bitmap> mostrarImagenesResultados();
}
