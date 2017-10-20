package com.example.sergio.nuevo.presentacion.vistas;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.presentacion.ContactosPagina;
import com.example.sergio.nuevo.presentacion.presentador.MainPresentador;
import com.example.sergio.nuevo.presentacion.presentador.MainPresentadorImpl;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MainView {
    final int codigo_de_repuesta_escritura = 0;
    final int codigo_de_repuesta_localizacion = 1000;
    private MainPresentador presentador;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        linearLayout = (LinearLayout)findViewById(R.id.linearlayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck !=  PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        codigo_de_repuesta_escritura);
            }
        }

        // permisos y parametros necesario para mostar mi posicion

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ActivityCompat.checkSelfPermission(
                    this, android.Manifest.permission.ACCESS_FINE_LOCATION)!=
                    PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                        codigo_de_repuesta_localizacion);
            }
        }

        presentador = new MainPresentadorImpl(this);
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
            case R.id.action_wathsapp:// sección comunicarse por wathsapp ....
                enviaMensajeWhatsApp();
                break;
            case R.id.action_email:// sección comunicarse por gmail ....
                enviaMensajeGmail("");
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

    public void enviaMensajeGmail(String s) {
        Log.i("Send email", "");

        String[] TO = {" empleo@empleotucuman.gob.ar"};
        String[] CC = {"OTROMAIL@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "TU ASUNTO");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "ESCRIBE AQUI TU CORREO!!!");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
//            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }

    public void enviaMensajeWhatsApp() {
//        PackageManager pm=getPackageManager();
        try {

//            Intent waIntent = new Intent(Intent.ACTION_SEND);
//            waIntent.setType("text/plain");
//            String text = "Tu texto aquí";
//
//            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
//            waIntent.setPackage("com.whatsapp");
//
//            waIntent.putExtra(Intent.EXTRA_TEXT, text);
//            startActivity(Intent.createChooser(waIntent, "Comunicarse con")); // tambien sirve para enviar wathsapp seleccionando contacto

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators("5493815442347")+"@s.whatsapp.net"); // envia wathsapp al numero
            startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(this, "WhatsApp no está instalado", Toast.LENGTH_SHORT)
                    .show();
        }
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
