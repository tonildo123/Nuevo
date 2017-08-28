package com.example.sergio.nuevo.presentador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.nuevo.vistas.JovenesC;
import com.example.sergio.nuevo.vistas.JovenesR;
import com.example.sergio.nuevo.vistas.Mipyme;
import com.example.sergio.nuevo.vistas.Noticias;
import com.example.sergio.nuevo.vistas.OfertaLaboral;
import com.example.sergio.nuevo.vistas.ProgresarC;
import com.example.sergio.nuevo.vistas.ProgresarR;
import com.example.sergio.nuevo.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Mapas mp = new Mapas();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager m =  getSupportFragmentManager();
        m.beginTransaction().replace(R.id.contenedor, new Noticias()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager m =  getSupportFragmentManager();

        if (id == R.id.nav_consulta) {         m.beginTransaction().replace(R.id.contenedor,     new ProgresarC()).commit(); }
        else if (id == R.id.nav_noticias) {  m.beginTransaction().replace(R.id.contenedor,       new Noticias()).commit();}
        else if (id == R.id.nav_CRONOGRAMAtab) {   m.beginTransaction().replace(R.id.contenedor, new Mipyme()).commit();}
        else if (id == R.id.nav_mapas) {

            Intent pasar = new Intent(MainActivity.this, Mapas.class);
            startActivity(pasar);


        }
        else if (id == R.id.nav_reqtab) {  m.beginTransaction().replace(R.id.contenedor, new Mipyme()).commit(); }
        else if (id == R.id.nav_laboral){  m.beginTransaction().replace(R.id.contenedor, new JovenesR()).commit();   }
        else if (id == R.id.nav_exit) {    System.exit(0);}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
