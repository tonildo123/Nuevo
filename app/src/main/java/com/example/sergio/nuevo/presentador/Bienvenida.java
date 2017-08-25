package com.example.sergio.nuevo.presentador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sergio.nuevo.R;

public class Bienvenida extends AppCompatActivity {

    int dormir = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        Thread hilo1 = new Thread(){
            @Override
            public void run() {
                try {
                    Intent pasar = new Intent(Bienvenida.this, MainActivity.class );
                    sleep(dormir);
                    startActivity(pasar);
                    finish();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };

        hilo1.start();
    }
}
