package com.example.sergio.nuevo.vistas;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.sergio.nuevo.dominio.Marcadores;
import com.example.sergio.nuevo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Mapas extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
//

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // marcadores son las ubicaciones de las demas reparticiones de las oficinas de empleo de la Provincia de tucuman
        Marcadores.marcadores(mMap);

        // Add a marker in Tucuman
        LatLng tucuman = new LatLng(-26.8307052, -65.20279649999998);
        mMap.addMarker(new MarkerOptions()
                .position(tucuman)
                .title("Oficina Centro - laprida 55 - Tucuman")
                .snippet("Oficina Central")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        );




        mMap.moveCamera(CameraUpdateFactory.newLatLng(tucuman));

        // permisos y parametros necesario para mostar mi posicion
        if(ActivityCompat.checkSelfPermission(

        this, android.Manifest.permission.ACCESS_FINE_LOCATION)!=
                      PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        }

        // seteo mi posicion
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.setMinZoomPreference(7.5f);
        mMap.setMaxZoomPreference(19.0f);


        mMap.setMapType(googleMap.MAP_TYPE_NORMAL);


    }
    // permisos y parametros necesario para mostar mi posicion
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1000 ){
            if(permissions.length>0
                    && permissions[0].equals(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "permiso de loction aceptado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

