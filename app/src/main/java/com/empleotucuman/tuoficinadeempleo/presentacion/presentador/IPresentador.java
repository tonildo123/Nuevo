package com.empleotucuman.tuoficinadeempleo.presentacion.presentador;

import android.view.View;

/**
 * Created by Operador1 on 18/10/2017.
 */

public interface IPresentador {
    void onResume();
    boolean onPause();
    void onDestroy();

    void iniciar();
    void onClick(Object[] o);
}
