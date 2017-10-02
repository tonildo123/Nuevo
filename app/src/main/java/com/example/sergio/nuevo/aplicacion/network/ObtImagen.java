package com.example.sergio.nuevo.aplicacion.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by Operador1 on 11/08/2017.
 */

public class ObtImagen {
    private static final ObtImagen img = new ObtImagen();
    public static ObtImagen getInstance(){
        return img;
    }
    private ObtImagen(){}

    public Bitmap descargarImagen(String src,int ancho,int alto) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            myBitmap = getResizedBitmap(myBitmap, alto, ancho);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }
    public void descargarCaptcha(String src){
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            byte[] buffer = new byte[1024];
            int bytesRead =0;
            FileOutputStream fileOutput = new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/SSE/tmp/captcha.jpg"));
            while((bytesRead=connection.getInputStream().read(buffer))>0) {
                fileOutput.write(buffer,0,bytesRead);
            }
            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
