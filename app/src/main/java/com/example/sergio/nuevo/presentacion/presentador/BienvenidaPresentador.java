package com.example.sergio.nuevo.presentacion.presentador;

import android.view.MenuItem;
import com.example.sergio.nuevo.dominio.A;

/**
 * Created by Operador1 on 12/10/2017.
 */

public interface BienvenidaPresentador {
    void onResume();
    boolean onPause();
    void onDestroy();
    A onNavigationItemSelected(MenuItem item);
}
