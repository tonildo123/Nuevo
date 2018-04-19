package com.empleotucuman.tuoficinadeempleo.presentacion.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.empleotucuman.tuoficinadeempleo.R;
import com.empleotucuman.tuoficinadeempleo.aplicacion.network.VolleyClient;
import com.empleotucuman.tuoficinadeempleo.dominio.VariablesDeEntorno;
import com.empleotucuman.tuoficinadeempleo.persistencia.PersisToken;
import com.empleotucuman.tuoficinadeempleo.presentacion.caracteristicas.Transicion;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    private EditText nombre;
    private EditText apellido;
    private EditText email;
    private EditText dni;
    private Button enviar;
    private String token;
    private PersisToken persisToken;
    private Spinner localidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = (EditText) findViewById(R.id.txtNombre);
        apellido = (EditText) findViewById(R.id.txtApellido);
        email = (EditText) findViewById(R.id.txtEmail);
        dni = (EditText) findViewById(R.id.txtDni);
        localidad = (Spinner) findViewById(R.id.slocalidad);

        enviar = (Button) findViewById(R.id.btnRegister);

        persisToken = new PersisToken(this);
        enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // registra el token en la base de datos del servidor  de app firebase
        RequestQueue queue = VolleyClient.getInstance(getBaseContext()).getRequestQueue(); //Obtain the instance

        StringRequest volleyRequest = new StringRequest(Request.Method.POST, VariablesDeEntorno.getUrlAPI()+"/usuarioAPI", //Change the url parameter
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Se registro correctamente",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            //Post method parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                //TODO: Change params and values
                params.put("nombre", nombre.getText().toString());
                params.put("apellido", apellido.getText().toString());
                params.put("idlocalidad", "1");
                params.put("email", email.getText().toString());
                params.put("dni", dni.getText().toString());
                token = FirebaseInstanceId.getInstance().getToken();
                persisToken.guardar(token);
                params.put("token", token);

                return params;
            }
        };

        //TODO: Change the retry policy
        volleyRequest.setRetryPolicy(new DefaultRetryPolicy());
        queue.add(volleyRequest);

        Transicion.getInstance().transicionActivity(this,1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
