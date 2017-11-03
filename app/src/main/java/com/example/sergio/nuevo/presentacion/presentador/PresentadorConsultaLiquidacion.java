package com.example.sergio.nuevo.presentacion.presentador;

import android.graphics.Bitmap;

/**
 * Created by Operador1 on 18/10/2017.
 */

public interface PresentadorConsultaLiquidacion extends IPresentador{
    void cargarResultados();

    void guardarImagenResultado(Bitmap bitmap);
}
