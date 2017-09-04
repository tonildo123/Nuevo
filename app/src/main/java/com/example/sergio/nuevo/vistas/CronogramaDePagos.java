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
import com.example.sergio.nuevo.vistas.tabs.TabDosProcro;
import com.example.sergio.nuevo.vistas.tabs.TabUnoJovcro;


public class CronogramaDePagos extends Fragment {
    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflamos la vista con este fragmento

        View view =inflater.inflate(R.layout.fragment_cronograma, container, false);
            // crea un acceso a la vista
        View contenedor = (View)container.getParent();
        appBar = (AppBarLayout) contenedor.findViewById(R.id.appbar);
        // retorna la vista del fragmento asociado
        tabs= new TabLayout(getActivity());
        tabs.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        // inserta el tab en el appbar
        appBar.addView(tabs);



        viewPager = (ViewPager)view.findViewById(R.id.pager);
        ViewPagerAdapter paginaAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(paginaAdapter);
        tabs.setupWithViewPager(viewPager);
    // retornamos la vista cargada
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        appBar.removeView(tabs);

    }
    public class ViewPagerAdapter extends FragmentStatePagerAdapter{
            public ViewPagerAdapter(FragmentManager fragmentManager){
                super(fragmentManager);

            }
        String[] titulo ={"CRONOGRAMA DE PAGO JOVENES","CRONOGRAMA DE PAGO PROGRESAR"};

        @Override
        public Fragment getItem(int position) {
            // instanciamos los fragmentos de la clase tabs para crear los objetos
            switch (position){

                case 0:
                    TabUnoJovcro tuno = new TabUnoJovcro();
                    return tuno;
                case 1:
                    TabDosProcro tdos = new TabDosProcro();
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
