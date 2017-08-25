package com.example.sergio.nuevo.presentador;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.sergio.nuevo.Marcadores;
import com.example.sergio.nuevo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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
        // Add a marker in Sydney and move the camera
        LatLng tucuman = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(tucuman)
                .title("Oficina Centro - laprida 55 - Tucuman"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tucuman));
        mMap.setMinZoomPreference(9.0f);
        mMap.setMaxZoomPreference(19.0f);


    }





}

