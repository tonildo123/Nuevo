package com.example.sergio.nuevo.presentacion.tabs;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.dominio.Programa;
import com.example.sergio.nuevo.persistencia.PersisRequisitos;
import com.example.sergio.nuevo.presentacion.caracteristicas.Transicion;
import com.example.sergio.nuevo.presentacion.presentador.PresentadorRequisitos;
import com.example.sergio.nuevo.presentacion.presentador.PresentadorRequisitosImpl;
import com.example.sergio.nuevo.presentacion.vistas.MainView;

import java.io.UnsupportedEncodingException;


public class TabReqJoven extends Fragment implements MainView{
    private ImageView imagen;
    private WebView pagina;
    private PresentadorRequisitos presentadorRequisitos;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_jovenes_requisitos, container, false);
        imagen = v.findViewById(R.id.imgRequisitos);
        pagina = (WebView) v.findViewById(R.id.webProgJov);
        presentadorRequisitos = PresentadorRequisitosImpl.getInstance();
        presentadorRequisitos.setVista(this);
        presentadorRequisitos.iniciar();
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        presentadorRequisitos.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        presentadorRequisitos.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presentadorRequisitos.onDestroy();
    }

    @Override
    public void mostrarContenido() {
        imagen.setImageBitmap(presentadorRequisitos.getRequisito().getImg());
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearlayout);
        Transicion.getInstance().animarLinearLayout(linearLayout, 0);

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
