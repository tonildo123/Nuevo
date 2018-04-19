package com.empleotucuman.tuoficinadeempleo.presentacion.tabs;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.empleotucuman.tuoficinadeempleo.R;
import com.empleotucuman.tuoficinadeempleo.dominio.Programa;
import com.empleotucuman.tuoficinadeempleo.persistencia.PersisRequisitos;
import com.empleotucuman.tuoficinadeempleo.presentacion.caracteristicas.Transicion;
import com.empleotucuman.tuoficinadeempleo.presentacion.presentador.PresentadorRequisitos;
import com.empleotucuman.tuoficinadeempleo.presentacion.presentador.PresentadorRequisitosImpl;
import com.empleotucuman.tuoficinadeempleo.presentacion.vistas.MainView;

import java.io.UnsupportedEncodingException;


public class TabReqProg extends Fragment implements MainView{
    private ImageView imagen;
    private WebView pagina;
    private View v;
    private PresentadorRequisitos presentadorRequisitos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_progresar_requisitos, container, false);
        imagen = v.findViewById(R.id.imgRequisitos);
        presentadorRequisitos = PresentadorRequisitosImpl.getInstance();
        presentadorRequisitos.setVista(this);
        presentadorRequisitos.iniciar();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }

    @Override
    public void mostrarContenido() {
        imagen.setImageBitmap(presentadorRequisitos.getRequisito().getImg());
        pagina = (WebView)v.findViewById(R.id.webProgProg);
        WebSettings settings = pagina.getSettings();
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setDefaultFontSize(14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            String base64 = null;
            try {
                base64 = Base64.encodeToString(presentadorRequisitos.getRequisito().getContenido().getBytes("UTF-8"), Base64.DEFAULT);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            pagina.loadData(base64, "text/html; charset=UTF-8", "base64");
        } else {
            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
            pagina.loadData(header + presentadorRequisitos.getRequisito().getContenido(), "text/html; charset=UTF-8", null);
        }
    }

    @Override
    public void actualizarBarraProgreso(int porcentaje) {

    }
}
