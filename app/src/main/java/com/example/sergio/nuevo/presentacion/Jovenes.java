package com.example.sergio.nuevo.presentacion;

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
import com.example.sergio.nuevo.dominio.A;
import com.example.sergio.nuevo.presentacion.tabs.TabCronProgresar;
import com.example.sergio.nuevo.presentacion.tabs.TabCronJoven;
import com.example.sergio.nuevo.presentacion.tabs.TabReqJoven;


public class Jovenes extends Fragment implements A{
    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflamos la vista con este fragmento

        View view = inflater.inflate(R.layout.fragment_cronograma, container, false);
        // crea un acceso a la vista
        View contenedor = (View) container.getParent();
        appBar = (AppBarLayout) contenedor.findViewById(R.id.appbar);
        // retorna la vista del fragmento asociado
        tabs = new TabLayout(getActivity());
        tabs.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        tabs.setBackgroundColor(Color.parseColor("#42a5f5"));
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#bbdefb"));
        // inserta el tab en el appbar
        appBar.addView(tabs);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
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

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        String[] titulo = {"REQUISITOS", "CRONOGRAMA"};

        @Override
        public Fragment getItem(int position) {
            // instanciamos los fragmentos de la clase tabs para crear los objetos
            switch (position) {

                case 0:
                    TabReqJoven tdos = new TabReqJoven();
                    return tdos;
                case 1:
                    TabCronJoven tuno = new TabCronJoven();
                    return tuno;
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
