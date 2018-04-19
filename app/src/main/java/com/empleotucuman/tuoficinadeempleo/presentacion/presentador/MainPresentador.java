package com.empleotucuman.tuoficinadeempleo.presentacion.presentador;

import android.view.MenuItem;
import com.empleotucuman.tuoficinadeempleo.dominio.A;

/**
 * Created by Operador1 on 12/10/2017.
 */

public interface MainPresentador extends IPresentador{
    A onNavigationItemSelected(int item);
}
