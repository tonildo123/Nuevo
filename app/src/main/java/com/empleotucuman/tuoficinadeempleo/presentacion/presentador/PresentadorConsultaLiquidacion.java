package com.empleotucuman.tuoficinadeempleo.presentacion.presentador;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Operador1 on 18/10/2017.
 */

public interface PresentadorConsultaLiquidacion extends IPresentador{
    void cargarResultados();

    void guardarImagenResultado(Bitmap bitmap);

    List<List> mostrarImagenesResultados();
}
