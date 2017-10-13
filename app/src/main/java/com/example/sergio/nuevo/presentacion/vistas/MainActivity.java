package com.example.sergio.nuevo.presentacion.vistas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.presentacion.ConsultaLiquidacion;
import com.example.sergio.nuevo.presentacion.ContactosPagina;
import com.example.sergio.nuevo.presentacion.CronogramaDePagos;
import com.example.sergio.nuevo.presentacion.Mapas;
import com.example.sergio.nuevo.presentacion.ModuloMIPyme;
import com.example.sergio.nuevo.presentacion.NumerosUtiles;
import com.example.sergio.nuevo.presentacion.OfertaCursos;
import com.example.sergio.nuevo.presentacion.OfertaLaboral;
import com.example.sergio.nuevo.presentacion.Requisitos;
import com.example.sergio.nuevo.presentacion.VistaNoticias;
import com.example.sergio.nuevo.presentacion.presentador.BienvenidaPresentador;
import com.example.sergio.nuevo.presentacion.presentador.BienvenidaPresentadorImpl;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,BienvenidaView {
    final int codigo_de_repuesta_escritura = 0;
    final int codigo_de_repuesta_localizacion = 1000;
    private BienvenidaPresentador presentador;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        linearLayout = (LinearLayout)findViewById(R.id.linearlayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            codigo_de_repuesta_escritura);
                }
            }
        }

        // permisos y parametros necesario para mostar mi posicion

        if(ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        }
        presentador = new BienvenidaPresentadorImpl(this);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case codigo_de_repuesta_escritura: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permisoEscritura();
                } else {
                    System.out.println("El usuario ha rechazado el permiso");
                }
                return;
            }
            case codigo_de_repuesta_localizacion:
                if(permissions.length== 1
                    && permissions[0].equals(android.Manifest.permission.ACCESS_FINE_LOCATION)
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "permiso de localizacion aceptado", Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void permisoEscritura() {
        Intent permisos = new Intent(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Intent permisos2 = new Intent(Manifest.permission.READ_EXTERNAL_STORAGE);

        startActivity(permisos);
        startActivity(permisos2);
    }

    @Override
    public void mostrarContenido() {
        LayoutInflater inflator = getLayoutInflater();
        View view = inflator.inflate(R.layout.activity_main,null);
        setContentView(view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        DrawerLayout drawer = (DrawerLayout)view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager m = getSupportFragmentManager();
        m.beginTransaction().replace(R.id.contenedor, VistaNoticias.getInstance()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        presentador.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presentador.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presentador.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            this.finish();
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
        FragmentManager m = getSupportFragmentManager();
        String[] email = {"empleo@empleotucuman.gob.ar"}; //Direcciones email  a enviar.

        switch (item.getItemId()){
            case R.id.action_settings:
                m.beginTransaction().replace(R.id.contenedor, new ContactosPagina()).commit();
                break;
            case R.id.action_email:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Tu Asunto...");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Enviar email."));
                }
                catch (android.content.ActivityNotFoundException e) {
                    Toast.makeText(this, "NO existe ningún cliente de email instalado!.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.action_telefonouno:
                Intent llamar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:  4228408 "));
                startActivity(llamar);
                break;
            case R.id.action_telefonodos:
                Intent llamar1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:  4228420 "));
                startActivity(llamar1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager m = getSupportFragmentManager();
        m.beginTransaction().replace(R.id.contenedor, (Fragment) presentador.onNavigationItemSelected(item)).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START,true);

        return true;
    }


}
