package com.example.sergio.nuevo.vistas;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

        FragmentManager m = getSupportFragmentManager();
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


        Uri numero = Uri.parse("smsto:+3815442347");
        String[] email = {"empleo@empleotucuman.gob.ar"}; //Direcciones email  a enviar.


        if (id == R.id.action_email) {

            Intent emailIntent = new Intent(Intent.ACTION_SEND);

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
        }
        if (id == R.id.action_wathsapp) { // codigo para enviar wathsapp a un ususario especifico
            PackageManager pm=getPackageManager();

            try {

                Intent waIntent = new Intent(Intent.ACTION_SENDTO, numero);
                waIntent.setType("text/plain");
                String text = "Tu texto aquí";

                PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                waIntent.setPackage("com.whatsapp");

                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(waIntent, "Compartir con"));

            } catch (PackageManager.NameNotFoundException e) {
                Toast.makeText(this, "WhatsApp no está instalado", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        if (id == R.id.action_facebook) {
            String uri = "https://www.facebook.com/messages/t/MiPyMEyEmpleo";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri)); // intent para abrir el chat de face
            startActivity(intent);
        }
        if (id == R.id.action_telefonouno) {
            Intent llamar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:  4228408 "));
            startActivity(llamar);
        }
        if (id == R.id.action_telefonodos) {
            Intent llamar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:  4228420 "));
            startActivity(llamar);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager m = getSupportFragmentManager();
        switch (item.getItemId()){
            case R.id.nav_consulta:
                m.beginTransaction().replace(R.id.contenedor, ConsultaLiquidacion.getInstance()).commit();
                break;
            case R.id.nav_noticias:
                m.beginTransaction().replace(R.id.contenedor, VistaNoticias.getInstance()).commit();
                break;
            case R.id.nav_CRONOGRAMAtab:
                m.beginTransaction().replace(R.id.contenedor, new CronogramaDePagos()).commit();
                break;
            case R.id.nav_mapas:
                Intent pasar = new Intent(MainActivity.this, Mapas.class);
                startActivity(pasar);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                break;
            case R.id.nav_reqtab:
                m.beginTransaction().replace(R.id.contenedor, new Requisitos()).commit();
                break;
            case R.id.nav_tres:
                FragmentTransaction ft = m.beginTransaction().replace(R.id.contenedor, new NumerosUtiles());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                // Start the animated transition.
                ft.commit();
                break;
            case R.id.nav_laboral:
                m.beginTransaction().replace(R.id.contenedor, new OfertaLaboral()).commit();
                break;
//            case R.id.nac_cursos:
//                m.beginTransaction().replace(R.id.contenedor, new OfertaLaboral()).commit();
//                break;
            case R.id.nav_exit:
                System.exit(0);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START,true);
        return true;
    }

}
