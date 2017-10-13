package com.example.sergio.nuevo.dominio;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.presentacion.ConsultaLiquidacion;
import com.example.sergio.nuevo.presentacion.CronogramaDePagos;
import com.example.sergio.nuevo.presentacion.Mapas;
import com.example.sergio.nuevo.presentacion.ModuloMIPyme;
import com.example.sergio.nuevo.presentacion.NumerosUtiles;
import com.example.sergio.nuevo.presentacion.OfertaCursos;
import com.example.sergio.nuevo.presentacion.OfertaLaboral;
import com.example.sergio.nuevo.presentacion.Requisitos;
import com.example.sergio.nuevo.presentacion.VistaNoticias;
import com.example.sergio.nuevo.presentacion.vistas.MainActivity;

/**
 * Created by Operador1 on 13/10/2017.
 */

public class CFactory {
    public A create(MenuItem item){
        switch (item.getItemId()){
            case R.id.nav_consulta:
                return new ConsultaLiquidacion();
            case R.id.nav_noticias:
                return VistaNoticias.getInstance();
            case R.id.nav_CRONOGRAMAtab:
                return new CronogramaDePagos();
            case R.id.nav_mapas:
                return new Mapas();
            case R.id.nav_reqtab:
                return new Requisitos();
            case R.id.nav_tres:
                return new NumerosUtiles();
            case R.id.nav_laboral:
                return new OfertaLaboral();
            case R.id.nav_mipyme:
                return new ModuloMIPyme();
            case R.id.nav_cursos:
                return new OfertaCursos();
            case R.id.nav_exit:
                System.exit(0);
        }
        return null;
    }
}
