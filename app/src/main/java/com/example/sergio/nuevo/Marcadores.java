package com.example.sergio.nuevo;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Sergio on 25/08/2017.
 */

public abstract class Marcadores {
    public static void marcadores(GoogleMap mMap) {


    /* Famaillá - Sarmiento 236 - 11

      Graneros - Avellaneda y Moreno - 12

      Juan Bautista Alberdi - Laprida Nº 668 -13

      La Cocha - San Martín Norte 55 - 14

      Las Talitas - Calle 29 y 10, Villa Mariano Moreno - 15

      Lules - Alte. Brown 351 - 16

      Monteros - 25 de Mayo 261 - 17

      Ranchillos - Av. San Martín 64 - 18

      San Miguel de Tucumán - Av. Juan B. Justo 951 - 19

      Santa Ana - Sarmiento Entre Urquiza y Carlos Pellegrini - 20

      Simoca - 25 De Mayo y Moreno - 21

      Tafí del Valle -  Peatonal Los Faroles S/Nº - 22

      Tafí Viejo - San Martín 130 - 23

      Trancas - Av. Hipólito Irigoyen 508 - 24

      Yerba Buena - Av. Aconquija 2021, Galería central, Local 5 - 25

      Colombres - San Martin S/N - 26

      Delfín Gallo - Barrio El Bosque Primera Cuadra S/N - 27

      El Chañar - 25 De Mayo y Sarmiento - 28

      El Manantial - Ruta 301 y Cristo Rey, B° Soberania Argentina - 29

      El Mollar - 21 De Noviembre S/N, Frente de la Plaza Principal - 30

      Escaba - La Calera - 31

      La Florida - Av Hipolito Irigoyen S/N - 31
    La Florida y Luisiana, Cruz Alta

    La Ramada - 24 De Sep y Prebistero Figueroa - 32

    La Trinidad -  Av Las Fuentes y Las Heras - 33

    Lamadrid - 25 De Mayo S/N Frente a Plaza Principal - 34

    León Rouges - 24 De Sep Esq 25 De Mayo - 35

    Los Nogales - Ruta 9 Km1308 Los Nogales - 36

    Los Ralos - Perito Moreno y Güemes - 37

    Manuela Pedraza - Ruta 157 Km 1220 - 38

    Quilmes y Los Sueldos - Pje Ecuador 200 - 39
    Quilmes y Los Sueldos, Leales

    San Pablo - Av. San Martin 4 - 40

    San Pedro de Colalao - 9 De Julio y Las Heras - 41

    Santa Lucia - Av. Libertador y Marco Avellaneda - 42 */
    // nuestras oficinas marcadas a continuacion
    // aguilares _ 1
    LatLng aguilares = new LatLng(-27.4315089, -65.61471619999998);
        mMap.addMarker(new

    MarkerOptions()
                .

    position(aguilares)
                .

    title(" Aguilares - Remedios de Escalada 950"));
    // alderetes _ 2
    LatLng alderetes = new LatLng(-26.8166695, -65.13333130000001);
        mMap.addMarker(new

    MarkerOptions()
                .

    position(alderetes)
                .

    title(" Alderetes - Caseros y Blas Parera"));
    // Amaicha del Valle_ 3
    LatLng amaicha_del_valle = new LatLng(-26.5938655, -65.9228956);
        mMap.addMarker(new

    MarkerOptions()
                .

    position(amaicha_del_valle)
                .

    title(" Amaicha del Valle - Ernesto Padilla e Hipolito Yrigoyen"));
    // Banda del Rio Salí 4
    LatLng banda_del_Rio_Salí = new LatLng(-26.8341103, -65.16542049999998);
        mMap.addMarker(new

    MarkerOptions()
                .

    position(banda_del_Rio_Salí)
                .

    title(" Banda del Rio Salí - Av. Monseñor Díaz Nº 870"));
    // Bella Vista 5
    LatLng bella_vista = new LatLng(-26.8341103, -65.16542049999998);
        mMap.addMarker(new

    MarkerOptions()
                .

    position(bella_vista)
                .

    title(" Bella vista - García Fernández 199"));
    // Burruyacu 6
    LatLng burruyacu = new LatLng(-26.8341103, -65.16542049999998);
        mMap.addMarker(new

    MarkerOptions()
                .

    position(burruyacu)
                .

    title(" Burruyacu - Calle Legislador Alberto Leal 565 (frente a la Municipalidad)"));
    // Cevil Redondo 7
    LatLng cevil_redondo = new LatLng(-26.7908265, -65.28584569999998);
        mMap.addMarker(new

    MarkerOptions()
                .

    position(cevil_redondo)
                .

    title(" Cevil Redondo - Barrio 188 Viviendas Mza B Casa 35"));
    // Choromoro 8
    LatLng choromoro = new LatLng(-26.4113031, -65.31990940000003);
        mMap.addMarker(new

    MarkerOptions()
                .

    position(choromoro)
                .

    title(" Choromoro - Juan Malensek"));
    // Colalao del Valle 9
    LatLng colalao_del_Valle = new LatLng(-26.3620097, 65.95700310000001);
        mMap.addMarker(new

    MarkerOptions()
                .

    position(colalao_del_Valle)
                .

    title(" Colalao del Valle - 9 De Julio S/N"));
    // Concepción 10
    LatLng concepción = new LatLng(-27.3459427, -65.59274440000002);
        mMap.addMarker(new

    MarkerOptions()
                .

    position(concepción)
                .

    title(" Concepción - Sarmiento esq. Moreno"));
    // aguilares _ 1
//        LatLng aguilares = new LatLng(-27.4315089, -65.61471619999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(aguilares)
//                .title(" Aguilares - Remedios de Escalada 950"));
//        // alderetes _ 2
//        LatLng alderetes = new LatLng(-26.8166695, -65.13333130000001);
//        mMap.addMarker(new MarkerOptions()
//                .position(alderetes)
//                .title(" Alderetes - Caseros y Blas Parera"));
//        // Amaicha del Valle_ 3
//        LatLng amaicha_del_valle = new LatLng(-26.5938655, -65.9228956);
//        mMap.addMarker(new MarkerOptions()
//                .position(amaicha_del_valle)
//                .title(" Amaicha del Valle - Ernesto Padilla e Hipolito Yrigoyen"));
//        // Banda del Rio Salí 4
//        LatLng banda_del_Rio_Salí = new LatLng(-26.8341103, -65.16542049999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(banda_del_Rio_Salí)
//                .title(" Banda del Rio Salí - Av. Monseñor Díaz Nº 870"));
//        // Bella Vista 5
//        LatLng bella_vista = new LatLng(-26.8341103, -65.16542049999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(bella_vista)
//                .title(" Bella vista - García Fernández 199"));
//        // Burruyacu 6
//        LatLng burruyacu = new LatLng(-26.8341103, -65.16542049999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(burruyacu)
//                .title(" Burruyacu - Calle Legislador Alberto Leal 565 (frente a la Municipalidad)"));
//        // Cevil Redondo 7
//        LatLng cevil_redondo = new LatLng(-26.7908265, -65.28584569999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(cevil_redondo)
//                .title(" Cevil Redondo - Barrio 188 Viviendas Mza B Casa 35"));
//        // Choromoro 8
//        LatLng choromoro = new LatLng(-26.4113031, -65.31990940000003);
//        mMap.addMarker(new MarkerOptions()
//                .position(choromoro)
//                .title(" Choromoro - Juan Malensek"));
//        // Colalao del Valle 9
//        LatLng colalao_del_Valle = new LatLng(-26.3620097, 65.95700310000001);
//        mMap.addMarker(new MarkerOptions()
//                .position(colalao_del_Valle)
//                .title(" Colalao del Valle - 9 De Julio S/N"));
//        // Concepción 10
//        LatLng concepción = new LatLng(-27.3459427, -65.59274440000002);
//        mMap.addMarker(new MarkerOptions()
//                .position(concepción)
//                .title(" Concepción - Sarmiento esq. Moreno"));
//        // aguilares _ 1
//        LatLng aguilares = new LatLng(-27.4315089, -65.61471619999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(aguilares)
//                .title(" Aguilares - Remedios de Escalada 950"));
//        // alderetes _ 2
//        LatLng alderetes = new LatLng(-26.8166695, -65.13333130000001);
//        mMap.addMarker(new MarkerOptions()
//                .position(alderetes)
//                .title(" Alderetes - Caseros y Blas Parera"));
//        // Amaicha del Valle_ 3
//        LatLng amaicha_del_valle = new LatLng(-26.5938655, -65.9228956);
//        mMap.addMarker(new MarkerOptions()
//                .position(amaicha_del_valle)
//                .title(" Amaicha del Valle - Ernesto Padilla e Hipolito Yrigoyen"));
//        // Banda del Rio Salí 4
//        LatLng banda_del_Rio_Salí = new LatLng(-26.8341103, -65.16542049999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(banda_del_Rio_Salí)
//                .title(" Banda del Rio Salí - Av. Monseñor Díaz Nº 870"));
//        // Bella Vista 5
//        LatLng bella_vista = new LatLng(-26.8341103, -65.16542049999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(bella_vista)
//                .title(" Bella vista - García Fernández 199"));
//        // Burruyacu 6
//        LatLng burruyacu = new LatLng(-26.8341103, -65.16542049999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(burruyacu)
//                .title(" Burruyacu - Calle Legislador Alberto Leal 565 (frente a la Municipalidad)"));
//        // Cevil Redondo 7
//        LatLng cevil_redondo = new LatLng(-26.7908265, -65.28584569999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(cevil_redondo)
//                .title(" Cevil Redondo - Barrio 188 Viviendas Mza B Casa 35"));
//        // Choromoro 8
//        LatLng choromoro = new LatLng(-26.4113031, -65.31990940000003);
//        mMap.addMarker(new MarkerOptions()
//                .position(choromoro)
//                .title(" Choromoro - Juan Malensek"));
//        // Colalao del Valle 9
//        LatLng colalao_del_Valle = new LatLng(-26.3620097, 65.95700310000001);
//        mMap.addMarker(new MarkerOptions()
//                .position(colalao_del_Valle)
//                .title(" Colalao del Valle - 9 De Julio S/N"));
//        // Concepción 10
//        LatLng concepción = new LatLng(-27.3459427, -65.59274440000002);
//        mMap.addMarker(new MarkerOptions()
//                .position(concepción)
//                .title(" Concepción - Sarmiento esq. Moreno"));
//        // aguilares _ 1
//        LatLng aguilares = new LatLng(-27.4315089, -65.61471619999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(aguilares)
//                .title(" Aguilares - Remedios de Escalada 950"));
//        // alderetes _ 2
//        LatLng alderetes = new LatLng(-26.8166695, -65.13333130000001);
//        mMap.addMarker(new MarkerOptions()
//                .position(alderetes)
//                .title(" Alderetes - Caseros y Blas Parera"));
//        // Amaicha del Valle_ 3
//        LatLng amaicha_del_valle = new LatLng(-26.5938655, -65.9228956);
//        mMap.addMarker(new MarkerOptions()
//                .position(amaicha_del_valle)
//                .title(" Amaicha del Valle - Ernesto Padilla e Hipolito Yrigoyen"));
//        // Banda del Rio Salí 4
//        LatLng banda_del_Rio_Salí = new LatLng(-26.8341103, -65.16542049999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(banda_del_Rio_Salí)
//                .title(" Banda del Rio Salí - Av. Monseñor Díaz Nº 870"));
//        // Bella Vista 5
//        LatLng bella_vista = new LatLng(-26.8341103, -65.16542049999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(bella_vista)
//                .title(" Bella vista - García Fernández 199"));
//        // Burruyacu 6
//        LatLng burruyacu = new LatLng(-26.8341103, -65.16542049999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(burruyacu)
//                .title(" Burruyacu - Calle Legislador Alberto Leal 565 (frente a la Municipalidad)"));
//        // Cevil Redondo 7
//        LatLng cevil_redondo = new LatLng(-26.7908265, -65.28584569999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(cevil_redondo)
//                .title(" Cevil Redondo - Barrio 188 Viviendas Mza B Casa 35"));
//        // Choromoro 8
//        LatLng choromoro = new LatLng(-26.4113031, -65.31990940000003);
//        mMap.addMarker(new MarkerOptions()
//                .position(choromoro)
//                .title(" Choromoro - Juan Malensek"));
//        // Colalao del Valle 9
//        LatLng colalao_del_Valle = new LatLng(-26.3620097, 65.95700310000001);
//        mMap.addMarker(new MarkerOptions()
//                .position(colalao_del_Valle)
//                .title(" Colalao del Valle - 9 De Julio S/N"));
//        // Concepción 10
//        LatLng concepción = new LatLng(-27.3459427, -65.59274440000002);
//        mMap.addMarker(new MarkerOptions()
//                .position(concepción)
//                .title(" Concepción - Sarmiento esq. Moreno"));
//        // aguilares _ 1
//        LatLng aguilares = new LatLng(-27.4315089, -65.61471619999998);
//        mMap.addMarker(new MarkerOptions()
//                .position(aguilares)
//                .title(" Aguilares - Remedios de Escalada 950"));
//        // alderetes _ 2
//        LatLng alderetes = new LatLng(-26.8166695, -65.13333130000001);
//        mMap.addMarker(new MarkerOptions()
//                .position(alderetes)
//                .title(" Alderetes - Caseros y Blas Parera"));
//        // Amaicha del Valle_ 3
//        LatLng amaicha_del_valle = new LatLng(-26.5938655, -65.9228956);
//        mMap.addMarker(new MarkerOptions()
//                .position(amaicha_del_valle)
//                .title(" Amaicha del Valle - Ernesto Padilla e Hipolito Yrigoyen"));


}}