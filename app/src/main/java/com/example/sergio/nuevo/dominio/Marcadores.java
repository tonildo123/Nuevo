package com.example.sergio.nuevo.dominio;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Sergio on 25/08/2017.
 */

public abstract class Marcadores {


    public static void marcadores(GoogleMap mMap) {

// nuestras oficinas marcadas a continuacion
        // aguilares _ 1
        LatLng aguilares = new LatLng(-27.4315089, -65.61471619999998);
        mMap.addMarker(new MarkerOptions()
                .position(aguilares)
                .title(" Las Talitas - Calle 29 y 10, Villa Mariano Moreno"));
        // alderetes _ 2
        LatLng alderetes = new LatLng(-26.8166695, -65.13333130000001);
        mMap.addMarker(new MarkerOptions()
                .position(alderetes)
                .title(" Alderetes - Caseros y Blas Parera"));
        // Amaicha del Valle_ 3
        LatLng amaicha_del_valle = new LatLng(-26.5938655, -65.9228956);
        mMap.addMarker(new MarkerOptions()
                .position(amaicha_del_valle)
                .title(" Amaicha del Valle - Ernesto Padilla e Hipolito Yrigoyen"));
        // Banda del Rio Salí 4
        LatLng banda_del_Rio_Salí = new LatLng(-26.8341103, -65.16542049999998);
        mMap.addMarker(new MarkerOptions()
                .position(banda_del_Rio_Salí)
                .title(" Banda del Rio Salí - Av. Monseñor Díaz Nº 870"));
        // Bella Vista 5
        LatLng bella_vista = new LatLng(-26.8341103, -65.16542049999998);
        mMap.addMarker(new MarkerOptions()
                .position(bella_vista)
                .title(" Bella vista - García Fernández 199"));
        // Burruyacu 6
        LatLng burruyacu = new LatLng(-26.8341103, -65.16542049999998);
        mMap.addMarker(new MarkerOptions()
                .position(burruyacu)
                .title(" Burruyacu - Calle Legislador Alberto Leal 565 (frente a la Municipalidad)"));
        // Cevil Redondo 7
        LatLng cevil_redondo = new LatLng(-26.7908265, -65.28584569999998);
        mMap.addMarker(new MarkerOptions()
                .position(cevil_redondo)
                .title(" Cevil Redondo - Barrio 188 Viviendas Mza B Casa 35"));
        // Choromoro 8
        LatLng choromoro = new LatLng(-26.4113031, -65.31990940000003);
        mMap.addMarker(new MarkerOptions()
                .position(choromoro)
                .title(" Choromoro - Juan Malensek"));
        // Colalao del Valle 9
        LatLng colalao_del_Valle = new LatLng(-26.3620097, 65.95700310000001);
        mMap.addMarker(new MarkerOptions()
                .position(colalao_del_Valle)
                .title(" Colalao del Valle - 9 De Julio S/N"));
        // Concepción 10
        LatLng concepción = new LatLng(-27.3459427, -65.59274440000002);
        mMap.addMarker(new MarkerOptions()
                .position(concepción)
                .title(" Concepción - Sarmiento esq. Moreno"));
        // nuestras oficinas marcadas a continuacion
        // Famailla _ 11
        LatLng famailla = new LatLng(-27.0563545, -65.4040986);
        mMap.addMarker(new MarkerOptions()
                .position(famailla)
                .title(" Sarmiento 236 _ Famailla"));
        // Graneros _ 12
        LatLng graneroa = new LatLng(-27.6488809, -65.44001159999999);
        mMap.addMarker(new MarkerOptions()
                .position(graneroa)
                .title(" Avellaneda y Moreno - Graneros"));
        // Juan Bautista Alberdi_ 13
        LatLng jbalberdi = new LatLng(-27.5871218, -65.61872499999998);
        mMap.addMarker(new MarkerOptions()
                .position(jbalberdi)
                .title(" Juan Bautista Alberdi -  Laprida Nº 668"));
        // La Cocha 14
        LatLng lacocha = new LatLng(-27.7647082, -65.5869169);
        mMap.addMarker(new MarkerOptions()
                .position(lacocha)
                .title(" San Martín Norte 55 - La Cocha"));
        // Las Talitas 15
        LatLng lastalitas = new LatLng(-26.7713943, -65.20343000000003);
        mMap.addMarker(new MarkerOptions()
                .position(lastalitas)
                .title(" Bella vista - García Fernández 199"));
        // Burruyacu 16
        LatLng lules = new LatLng(-26.9244894, -65.33625440000003);
        mMap.addMarker(new MarkerOptions()
                .position(lules)
                .title(" Lules,   Alte. Brown 351"));
        //  Monteros 17
        LatLng monteros = new LatLng(-27.1675246, -65.49740939999998);
        mMap.addMarker(new MarkerOptions()
                .position(monteros)
                .title("  Monteros - 25 de Mayo 261"));
        // ranchillos 18
        LatLng ranchillos = new LatLng(-26.4113031, -65.31990940000003);
        mMap.addMarker(new MarkerOptions()
                .position(ranchillos)
                .title(" Ranchillos - Av. San Martín 64 "));
        // San miguel de Tucuman 19
        LatLng smtuc = new LatLng(-26.8205189, -65.13567219999999);
        mMap.addMarker(new MarkerOptions()
                .position(smtuc)
                .title("San Miguel de Tucumán - Av. Juan B. Justo 951"));
        // Santa ana 20
        LatLng santana = new LatLng(-27.3459427, -65.59274440000002);
        mMap.addMarker(new MarkerOptions()
                .position(santana)
                .title("  Santa Ana - Sarmiento Entre Urquiza y Carlos Pellegrini "));

        // Simoca 21
        LatLng simoca = new LatLng(-27.2571024, -65.3544996);
        mMap.addMarker(new MarkerOptions()
                .position(simoca)
                .title(" Simoca - 25 De Mayo y Moreno"));
        // tafi del valle _ 22
        LatLng t_del_valle = new LatLng(-26.8166695, -65.13333130000001);
        mMap.addMarker(new MarkerOptions()
                .position(t_del_valle)
                .title(" Tafí del Valle -  Peatonal Los Faroles S/Nº (al lado de la casa del turista) "));
        // tafi viejo 23
        LatLng tafi_viejo = new LatLng(-26.732343, -65.25786590000001);
        mMap.addMarker(new MarkerOptions()
                .position(tafi_viejo)
                .title("  Tafí Viejo - San Martín 130 Paseo Tafí Altos"));
        // Trancas 24
        LatLng trancas = new LatLng(-26.2304116, -65.28257789999998);
        mMap.addMarker(new MarkerOptions()
                .position(trancas)
                .title(" Trancas - Av. Hipólito Irigoyen 508 "));
        // colombres  25
        LatLng colombres = new LatLng(-26.8994319, -65.10322819999999);
        mMap.addMarker(new MarkerOptions()
                .position(colombres)
                .title(" Colombres - San Martin S/N "));
        // Delfin gallo 26
        LatLng defin_gallo = new LatLng(-26.8506882, -65.0886456);
        mMap.addMarker(new MarkerOptions()
                .position(defin_gallo)
                .title(" Delfín Gallo - Barrio El Bosque Primera Cuadra S/N"));
        //  el chañar 27
        LatLng chaniar = new LatLng(-26.7558594, -65.06593499999997);
        mMap.addMarker(new MarkerOptions()
                .position(chaniar)
                .title(" El Chañar - 25 De Mayo y Sarmiento"));
        // el manantial 28
        LatLng el_manantial = new LatLng(-26.85, -65.28333299999997);
        mMap.addMarker(new MarkerOptions()
                .position(el_manantial)
                .title(" El Manantial - Ruta 301 y Cristo Rey"));
        //  el mollar  29
        LatLng el_mollar = new LatLng(-26.9387, -65.70704999999998);
        mMap.addMarker(new MarkerOptions()
                .position(el_mollar)
                .title("  El Mollar - 21 De Noviembre "));
        // Escaba 30
        LatLng escaba = new LatLng(-27.6590418, -65.76296769999999);
        mMap.addMarker(new MarkerOptions()
                .position(escaba)
                .title(" Escaba - La Calera"));

        // LA FLORIDA  _ 31
        LatLng la_florida = new LatLng(-27.2371976, -65.56604290000001);
        mMap.addMarker(new MarkerOptions()
                .position(la_florida)
                .title(" La Florida - Av Hipolito Irigoyen S/N "));
        // la ramada _ 32
        LatLng la_ramada = new LatLng(-26.6878924, -64.94514530000004);
        mMap.addMarker(new MarkerOptions()
                .position(la_ramada)
                .title(" La Ramada - 24 De Sep y Prebistero Figueroa"));
        // la trinidad   33
        LatLng la_trinidad = new LatLng(-27.4143987, -65.51673299999999);
        mMap.addMarker(new MarkerOptions()
                .position(la_trinidad)
                .title(" La Trinidad - Av Las Fuentes y Las Heras"));
        // Lamadrid 34
        LatLng lamadrid = new LatLng(-27.6458653, -65.24743219999999);
        mMap.addMarker(new MarkerOptions()
                .position(lamadrid)
                .title(" Lamadrid - 25 De Mayo S/N Frente a Plaza Principal"));
        // Leon rouges 35
        LatLng leon_rouges = new LatLng(-27.2198231, -65.52618259999997);
        mMap.addMarker(new MarkerOptions()
                .position(leon_rouges)
                .title(" Leon Rouges, Monteros "));
        // los nogales  36
        LatLng los_nogales = new LatLng(-26.7880325, -65.22046899999998);
        mMap.addMarker(new MarkerOptions()
                .position(los_nogales)
                .title(" Los Nogales -  Ruta 9 Km1308 Los Nogales"));
        //  los ralos 37
        LatLng los_ralos = new LatLng(-26.8870919, -64.99923530000001);
        mMap.addMarker(new MarkerOptions()
                .position(los_ralos)
                .title("Los Ralos - Perito Moreno y Güemes"));
        // manuela pedraza 38
        LatLng manuela_pedraza = new LatLng(-26.4113031, -65.31990940000003);
        mMap.addMarker(new MarkerOptions()
                .position(manuela_pedraza)
                .title(" Manuela Pedraza - Ruta 157 Km 1220"));
        //   quilmes y los sueldos  39
        LatLng qysleales = new LatLng(-27.083333, -65.25);
        mMap.addMarker(new MarkerOptions()
                .position(qysleales)
                .title("Quilmes y Los Sueldos - Pje Ecuador 200"));
        // San pablo 40
        LatLng san_pablo = new LatLng(-26.8841164, -65.31522640000003);
        mMap.addMarker(new MarkerOptions()
                .position(san_pablo)
                .title("San Pablo -Av. San Martin 4 "));
        // San pedro de colalao 41
        LatLng san_pedro_colalao = new LatLng(-26.2403967, -65.49971540000001);
        mMap.addMarker(new MarkerOptions()
                .position(san_pedro_colalao)
                .title("San Pedro de Colalao, Trancas"));
        // Santa lucia 42
        LatLng santa_lucia = new LatLng(-27.0927792, -65.53258199999999);
        mMap.addMarker(new MarkerOptions()
                .position(santa_lucia)
                .title("Santa Lucia - Av. Libertador y Marco Avellaneda "));
    }
}


