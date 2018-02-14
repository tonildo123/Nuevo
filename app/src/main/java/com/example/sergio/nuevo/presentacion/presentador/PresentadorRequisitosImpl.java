package com.example.sergio.nuevo.presentacion.presentador;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sergio.nuevo.dominio.Programa;
import com.example.sergio.nuevo.persistencia.PersisRequisitos;
import com.example.sergio.nuevo.presentacion.tabs.TabCronProgresar;
import com.example.sergio.nuevo.presentacion.vistas.MainView;
import com.example.sergio.nuevo.presentacion.tabs.TabReqJoven;
import com.example.sergio.nuevo.presentacion.tabs.TabReqProg;

/**
 * Created by Operador1 on 31/10/2017.
 */

public class PresentadorRequisitosImpl implements PresentadorRequisitos{
    private MainView vista;
    private static final PresentadorRequisitosImpl presentador = new PresentadorRequisitosImpl();
    private Fragment fragment;
    private PersisRequisitos reqJoven;
    private Programa requisito;

    public static PresentadorRequisitosImpl getInstance(){return presentador;}

    public void setVista(MainView vista){
        this.vista = vista;
        this.fragment = (Fragment)vista;
    }

    private PresentadorRequisitosImpl() {
    }

    @Override
    public void onResume() {

    }

    @Override
    public boolean onPause() {
        return false;
    }

    @Override
    public void onDestroy() {
        vista = null;
    }

    @Override
    public void iniciar() {
        switch (vista.getClass().toString()){
            case "class com.example.sergio.nuevo.presentacion.tabs.TabReqJoven":
                mostrarContenidoRequisitoJoven();
                break;
            case "class com.example.sergio.nuevo.presentacion.tabs.TabReqProg":
                mostrarContenidoRequisitoProg();
                break;
        }
    }
    @Override
    public void onClick(Object[] o) {

    }

    @Override
    public FragmentStatePagerAdapter getViewPagerAdapter(FragmentManager fragmentManager) {
        return new FragmentStatePagerAdapter(fragmentManager) {
            String[] titulo ={" REQUISITOS "," CRONOGRAMA "};
            @Override
            public int getCount() {
                return titulo.length;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        TabReqProg tuno = new TabReqProg();
                        return tuno;
                    case 1:
                        TabCronProgresar tdos = new TabCronProgresar();
                        return tdos;
                }
                return null;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return titulo[position];
            }
        };
    }
    private void mostrarContenidoRequisitoJoven(){
        reqJoven = new PersisRequisitos(this.fragment.getActivity());
        requisito = reqJoven.levantar("requisitos_joven");
        if(requisito != null){
            vista.mostrarContenido();
        }
    }
    private void mostrarContenidoRequisitoProg() {
        reqJoven = new PersisRequisitos(this.fragment.getActivity());
        requisito = reqJoven.levantar("requisitos_progresar");
        if(requisito != null){
            vista.mostrarContenido();
        }
    }
    public Programa getRequisito(){
        return requisito;
    }
}
