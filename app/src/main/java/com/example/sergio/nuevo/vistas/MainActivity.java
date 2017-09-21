package com.example.sergio.nuevo.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.sergio.nuevo.R;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Mapas mp = new Mapas();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager m =  getSupportFragmentManager();
        m.beginTransaction().replace(R.id.contenedor, VistaNoticias.getInstance()).commit();

        String token = FirebaseInstanceId.getInstance().getToken();
//        Log.i("FCM",token);
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

        if (id == R.id.nav_consulta) {
            FragmentTransaction ft = m.beginTransaction().replace(R.id.contenedor, ConsultaLiquidacion.getInstance());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
// Start the animated transition.
            ft.commit();
        }
        else if (id == R.id.nav_noticias) {
            FragmentTransaction ft = m.beginTransaction().replace(R.id.contenedor, VistaNoticias.getInstance());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
// Start the animated transition.
            ft.commit();
        }
        else if (id == R.id.nav_CRONOGRAMAtab) {
            FragmentTransaction ft = m.beginTransaction().replace(R.id.contenedor, new CronogramaDePagos());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
// Start the animated transition.
            ft.commit();
        }
        else if (id == R.id.nav_mapas) {
            Intent pasar = new Intent(MainActivity.this, Mapas.class);
            startActivity(pasar);
            overridePendingTransition(R.anim.zoom_back_in,R.anim.zoom_back_out);
        }
        else if (id == R.id.nav_reqtab) {
            FragmentTransaction ft = m.beginTransaction().replace(R.id.contenedor, new Requisitos());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
// Start the animated transition.
            ft.commit();
        }
        else if (id == R.id.nav_tres)   {
            FragmentTransaction ft = m.beginTransaction().replace(R.id.contenedor, new NumerosUtiles());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
// Start the animated transition.
            ft.commit();
        }
        else if (id == R.id.nav_laboral){
            FragmentTransaction ft = m.beginTransaction().replace(R.id.contenedor, new OfertaLaboral());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
// Start the animated transition.
            ft.commit();
        }
        else if (id == R.id.nav_exit)   {  System.exit(0);}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
