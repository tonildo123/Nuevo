package com.example.sergio.nuevo;

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


public class Mipyme extends Fragment {
//    private AppBarLayout appBar;
//    private TabLayout tabs;
//    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista =inflater.inflate(R.layout.fragment_mipyme, container, false);
        View cont = (View)container.getParent();
//        appBar = (AppBarLayout)cont.findViewById(R.id.appbar);
//        tabs= new TabLayout(getActivity());
//        tabs.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
//        appBar.addView(tabs);
//
//        viewPager = (ViewPager)vista.findViewById(R.id.paginatabs);
//        ViewPagerAdapter paginaAdapter = new ViewPagerAdapter(getFragmentManager());
//        viewPager.setAdapter(paginaAdapter);
//        tabs.setupWithViewPager(viewPager);
//


        return vista;
    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        appBar.removeView(tabs);
//
//    }
//    public class ViewPagerAdapter extends FragmentStatePagerAdapter{
//            public ViewPagerAdapter(FragmentManager fragmentManager){
//                super(fragmentManager);
//
//            }
//        String[] titulo ={"MI PYME TABS 1","MI PYME TABS 2"};
//
//        @Override
//        public Fragment getItem(int position) {
//
//            switch (position){
//
//                case 0: return new MipymeTab1();
//                case 1: return new MipymeTab2();
//
//            }
//            return null;
//        }
//
//        @Override
//        public int getCount() {
//            return 2;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return titulo[position];
//        }
//    }



}
