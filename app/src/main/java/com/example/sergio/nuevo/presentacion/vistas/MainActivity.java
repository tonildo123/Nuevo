package com.example.sergio.nuevo.presentacion.vistas;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.presentacion.ContactosPagina;
import com.example.sergio.nuevo.presentacion.presentador.MainPresentador;
import com.example.sergio.nuevo.presentacion.presentador.MainPresentadorImpl;
import com.example.sergio.nuevo.servicios.ServicioCompartir;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
    final int codigo_de_repuesta_escritura = 0;
    final int codigo_de_repuesta_localizacion = 1000;
    private MainPresentador presentador;
    private LinearLayout linearLayout;
    private MaterialProgressBar progressBar;
    private TextView textView;
    private int fragments;
    private boolean isResume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        codigo_de_repuesta_escritura);
            }
        }

        // permisos y parametros necesario para mostar mi posicion

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(
                    this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        codigo_de_repuesta_localizacion);
            }
        }
        progressBar = (MaterialProgressBar) findViewById(R.id.material_design_progressbar);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#1E88E5")));
        progressBar.setVisibility(View.VISIBLE);
        textView = (TextView)findViewById(R.id.textView2);

        presentador = new MainPresentadorImpl(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case codigo_de_repuesta_escritura: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    System.out.println("El usuario ha rechazado el permiso");
                }
                return;
            }
            case codigo_de_repuesta_localizacion:
                if (permissions.length == 1
                        && permissions[0].equals(android.Manifest.permission.ACCESS_FINE_LOCATION)
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "permiso de localizacion aceptado", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void mostrarContenido() {
        LayoutInflater inflator = getLayoutInflater();
        View view = inflator.inflate(R.layout.activity_main, null);
        setContentView(view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        DrawerLayout drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(this.fragments != 0){
            getFragment(this.fragments);
            this.fragments = 0;
        }else{
            FragmentManager m = getSupportFragmentManager();
            m.beginTransaction().replace(R.id.contenedor, VistaNoticias.getInstance()).commit();
        }
    }

    @Override
    public void actualizarBarraProgreso(final int porcentaje) {
        textView.setText(porcentaje+"%");
        progressBar.setProgress(porcentaje);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        isResume = true;
        super.onResume();
        presentador.onResume();
        if(this.fragments != 0){
            getFragment(this.fragments);
            this.fragments = 0;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presentador.onPause();
    }

    @Override
    protected void onStop() {
        isResume = false;
        super.onStop();
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

        switch (item.getItemId()) {
            case R.id.action_settings:
                m.beginTransaction().replace(R.id.contenedor, new ContactosPagina()).commit();
                break;
            case R.id.action_wathsapp:// sección comunicarse por wathsapp ....
                ServicioCompartir.enviarWhatsapp(this);
                break;
            case R.id.action_email:// sección comunicarse por gmail ....
                ServicioCompartir.enviarGmail(this);
                break;
            case R.id.action_telefonouno:
                Intent llamar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:  4228408 "));
                startActivity(llamar);
                break;
            case R.id.action_telefonodos:
                Intent llamar1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 4218810 "));
                startActivity(llamar1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager m = getSupportFragmentManager();
        m.beginTransaction().replace(R.id.contenedor, (Fragment) presentador.onNavigationItemSelected(item.getItemId())).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START, true);

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent.getExtras() != null){
            this.fragments = intent.getExtras().getInt("Fragments");
            Snackbar.make(getCurrentFocus(),"Fragments "+intent.getExtras().getInt("Fragments"),Snackbar.LENGTH_LONG);
        }
    }
    private void getFragment(int fragment){
        FragmentManager m = getSupportFragmentManager();
        m.beginTransaction().replace(R.id.contenedor, (Fragment) presentador.onNavigationItemSelected(fragment)).commit();
    }
}
