package com.example.sergio.nuevo.dominio;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.presentacion.vistas.ConsultaLiquidacion;
import com.example.sergio.nuevo.presentacion.CronogramaDePagos;
import com.example.sergio.nuevo.presentacion.Mapas;
import com.example.sergio.nuevo.presentacion.ModuloMIPyme;
import com.example.sergio.nuevo.presentacion.NumerosUtiles;
import com.example.sergio.nuevo.presentacion.OfertaCursos;
import com.example.sergio.nuevo.presentacion.OfertaLaboral;
import com.example.sergio.nuevo.presentacion.vistas.Requisitos;
import com.example.sergio.nuevo.presentacion.vistas.VistaNoticias;

/**
 * Created by Operador1 on 13/10/2017.
 */

public class CFactory {
    public A create(int item){
        switch (item){
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
            case R.layout.fragment_observatorio:
                return new ModuloMIPyme();
            case R.id.nav_exit:
                System.exit(0);
        }
        return null;
    }
    public A crearConMenu(int cards){
        switch (cards){
            case R.id.bConsulta:
                return new ConsultaLiquidacion();
            case R.id.bMapa:
                return new Mapas();
            case R.id.bNoticias:
                return VistaNoticias.getInstance();
            case R.id.bMipyme:
                return new ModuloMIPyme();
        }
        return null;
    }
}
