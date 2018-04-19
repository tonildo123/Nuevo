package com.empleotucuman.tuoficinadeempleo.presentacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.empleotucuman.tuoficinadeempleo.dominio.A;
import com.empleotucuman.tuoficinadeempleo.dominio.Marcadores;
import com.empleotucuman.tuoficinadeempleo.presentacion.caracteristicas.Transicion;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Mapas extends SupportMapFragment  implements OnMapReadyCallback,A {
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        getMapAsync(this);

        return rootView;
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
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tucuman));

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
    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }
}


