package com.example.sergio.nuevo.vistas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.vistas.tabs.TabReqJoven;
import com.example.sergio.nuevo.vistas.tabs.TabReqProg;


public class Requisitos extends Fragment {
    private AppBarLayout appBar;
    private TabLayout tabsRequisitos;
    private ViewPager viewPagerRequisitos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_requisitos, container, false);

        View contenedor = (View)container.getParent();
        appBar = (AppBarLayout) contenedor.findViewById(R.id.appbar);
        // returna la vista del fragmento asociado
        tabsRequisitos= new TabLayout(getActivity());
        tabsRequisitos.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        // inserta el tab en el appbar
        appBar.addView(tabsRequisitos);



        viewPagerRequisitos = (ViewPager)view.findViewById(R.id.pagerRequisitos);
        ViewPagerAdapter paginaAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPagerRequisitos.setAdapter(paginaAdapter);
        tabsRequisitos.setupWithViewPager(viewPagerRequisitos);
        // retornamos la viosta cargada

        return view;
    }
    public void onDestroy() {
        super.onDestroy();
        appBar.removeView(tabsRequisitos);

    }
    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);

        }
        String[] titulo ={"Requisitos JOVENES","Riquisitos PROGRESAR"};

        @Override
        public Fragment getItem(int position) {
            // instanciamos los fragmentos de la clase tabs para crear los objetos
            switch (position){

                case 0:
                    TabReqJoven tuno = new TabReqJoven();
                    return tuno;
                case 1:
                    TabReqProg tdos = new TabReqProg();
                    return tdos;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulo[position];
        }
    }

}
