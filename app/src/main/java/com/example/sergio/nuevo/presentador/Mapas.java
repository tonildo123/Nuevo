package com.example.sergio.nuevo.presentador;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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
        marcadores();
        // Add a marker in Sydney and move the camera
        LatLng tucuman = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(tucuman)
                .title("Oficina Centro - laprida 55 - Tucuman"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tucuman));
        mMap.setMinZoomPreference(9.0f);
        mMap.setMaxZoomPreference(19.0f);


    }

    public void marcadores(){
        /*
Colalao del Valle

9 De Julio S/N
(0381) 155093627
Colalao del Valle, Tafi del Valle
colalaodelvalle@empleotucuman.gob.ar

Concepción

Sarmiento esq. Moreno
(03865) 455003
Concepción, Chicligasta
concepcion@empleotucuman.gob.ar

Famaillá

Sarmiento 236
(03863) 462032
Famaillá, Famaillá
famailla@empleotucuman.gob.ar

Graneros

Avellaneda y Moreno
(03891) 491231
Graneros, Graneros
graneros@empleotucuman.gob.ar

Juan Bautista Alberdi

Laprida Nº 668
(03865) 472870
Juan Bautista Alberdi, Juan Bautista Alberdi
juanbautistaalberdi@empleotucuman.gob.ar

La Cocha

San Martín Norte 55
(03865) 496340
La Cocha, La Cocha
lacocha@empleotucuman.gob.ar

Las Talitas

Calle 29 y 10, Villa Mariano Moreno
(0381) 4373405
Las Talitas, Tafí Viejo
lastalitas@empleotucuman.gob.ar

Lules

Alte. Brown 351
(0381) 4810068
Lules, Lules
lules@empleotucuman.gob.ar

Monteros

25 de Mayo 261
(03863) 428853
Monteros, Monteros
monteros@empleotucuman.gob.ar

Ranchillos

Av. San Martín 64
(03869) 421498 / 421217
Ranchillos, Cruz Alta
ranchillos@empleotucuman.gob.ar

San Miguel de Tucumán

Av. Juan B. Justo 951
(0381) 4224337/4228421
San Miguel de Tucumán, Capital
sanmigueldetucuman@empleotucuman.gob.ar

Santa Ana

Sarmiento Entre Urquiza y Carlos Pellegrini
(03865) 15417044 / 15598971
Santa Ana, Río Chico
santaana@empleotucuman.gob.ar

Simoca

25 De Mayo y Moreno
(03863) 481995
Simoca, Simoca
simoca@empleotucuman.gob.ar

Tafí del Valle

Peatonal Los Faroles S/Nº (al lado de la casa del turista) Acceso Avenida Gobernador Miguel Crito, La Villa
(03867) 420183
Tafí del Valle, Tafí del Valle
tafidelvalle@empleotucuman.gob.ar

Tafí Viejo

San Martín 130 Paseo Tafí Altos
(0381) 4616788
Tafí Viejo, Tafí Viejo
tafiviejo@empleotucuman.gob.ar

Trancas

Av. Hipólito Irigoyen 508
(03862) 421974
Trancas, Trancas
trancas@empleotucuman.gob.ar

Yerba Buena

Av. Aconquija 2021, Galería central, Local 5
(0381) 4258022
Yerba Buena, Yerba Buena
yerbabuena@empleotucuman.gob.ar

Unidades de Empleo

Colombres

San Martin S/N
(0381) 155031339
Colombres, Cruz Alta
colombres@empleotucuman.gob.ar

Delfín Gallo

Barrio El Bosque Primera Cuadra S/N
(0381) 154199833 / 155122599
Delfin Gallo, Cruz Alta
delfingallo@empleotucuman.gob.ar

El Chañar

25 De Mayo y Sarmiento
El Chañar, Burruyacu
elchaniar@empleotucuman.gob.ar

El Manantial

Ruta 301 y Cristo Rey, B° Soberania Argentina
El Manantial, Lules
elmanantial@empleotucuman.gob.ar

El Mollar

21 De Noviembre S/N, Frente de la Plaza Principal
El Mollar, Tafi del Valle
elmollar@empleotucuman.gob.ar

Escaba

La Calera
(03865) 15308012
Escaba, Juan Bautista Alberdi
escaba@empleotucuman.gob.ar

La Florida

Av Hipolito Irigoyen S/N
La Florida y Luisiana, Cruz Alta
lafloridayluisiana@empleotucuman.gob.ar

La Ramada

24 De Sep y Prebistero Figueroa
La Ramada y La Cruz, Burruyacu
laramada@empleotucuman.gob.ar

La Trinidad

Av Las Fuentes y Las Heras
La Trinidad, Chicligasta
latrinidad@empleotucuman.gob.ar

Lamadrid

25 De Mayo S/N Frente a Plaza Principal
Lamadrid, Graneros
lamadrid@empleotucuman.gob.ar

León Rouges

24 De Sep Esq 25 De Mayo
Leon Rouges, Monteros
leonrouges@empleotucuman.gob.ar

Los Nogales

Ruta 9 Km1308 Los Nogales
(0381) 155170144
Los Nogales, Tafi Viejo
losnogales@empleotucuman.gob.ar

Los Ralos

Perito Moreno y Güemes
Los Ralos, Cruz Alta
losralos@empleotucuman.gob.ar

Manuela Pedraza

Ruta 157 Km 1220
Manuela Pedraza, Simoca
manuelapedraza@empleotucuman.gob.ar

Quilmes y Los Sueldos

Pje Ecuador 200
Quilmes y Los Sueldos, Leales
quilmesylossueldos@empleotucuman.gob.ar

San Pablo

Av. San Martin 4
San Pablo, Lules
sanpablo@empleotucuman.gob.ar

San Pedro de Colalao

9 De Julio y Las Heras
(0381) 155750090
San Pedro de Colalao, Trancas
sanpedrodecolalao@empleotucuman.gob.ar

Santa Lucia

Av. Libertador y Marco Avellaneda
Santa Lucia, Monteros
santalucia@empleotucuman.gob.ar*/
        // nuestras oficinas marcadas a continuacion
        // aguilares _ 1
        LatLng aguilares = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(aguilares)
                .title(" Aguilares - Remedios de Escalada 950"));
        // sin coordenadas

        // alderetes _ 2
        LatLng alderetes = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(alderetes)
                .title(" Alderetes - Caseros y Blas Parera"));
        // sin coordenadas

        // Amaicha del Valle_ 3
        LatLng amaicha_del_valle = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(amaicha_del_valle)
                .title(" Amaicha del Valle - Ernesto Padilla e Hipolito Yrigoyen"));
        // sin coordenadas

        // Banda del Rio Salí 4
        LatLng banda_del_Rio_Salí = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(banda_del_Rio_Salí)
                .title(" Banda del Rio Salí - Av. Monseñor Díaz Nº 870"));
        // sin coordenadas

        // Bella Vista 5
        LatLng bella_vista = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(bella_vista)
                .title(" Bella vista - García Fernández 199"));
        // sin coordenadas

        // Burruyacu 6
        LatLng burruyacu = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(burruyacu)
                .title(" Burruyacu - Calle Legislador Alberto Leal 565 (frente a la Municipalidad)"));
        // sin coordenadas

        // Cevil Redondo 7
        LatLng cevil_redondo = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(cevil_redondo)
                .title(" Cevil Redondo - Barrio 188 Viviendas Mza B Casa 35"));
        // sin coordenadas

        // Choromoro 8
        LatLng choromoro = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(choromoro)
                .title(" Choromoro - Juan Malensek"));
        // sin coordenadas

        // aguilares _ 1
        LatLng aguilares = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(aguilares)
                .title(" Aguilares - Remedios de Escalada 950"));
        // sin coordenadas

        // aguilares _ 1
        LatLng aguilares = new LatLng(-26.8167, -65.2167);
        mMap.addMarker(new MarkerOptions()
                .position(aguilares)
                .title(" Aguilares - Remedios de Escalada 950"));
        // sin coordenadas



    }



}

