package com.example.sergio.nuevo.vistas.caracteristicas;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.example.sergio.nuevo.R;
import com.example.sergio.nuevo.vistas.VistaNoticias;

/**
 * Created by JESUS on 21/09/2017.
 */

public class Transicion {
    private static final Transicion animacion = new Transicion();

    public static Transicion getInstance() {
        return animacion;
    }

    public void animarLinearLayout(LinearLayout l, int animacion) {
        AnimationSet set = new AnimationSet(true);
        Animation animation = null;
        switch (animacion) {
            case 0:
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                break;
            case 1:
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                break;
        }
        //duración en milisegundos
        animation.setDuration(400);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
        l.setLayoutAnimation(controller);
    }
    public void transicionFragments(View v, final FragmentActivity activity){
        if (v == null) {
            return;
        }
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    FragmentManager m = activity.getSupportFragmentManager();
                    m.beginTransaction().replace(R.id.contenedor, VistaNoticias.getInstance()).commit();
                    return true;
                }
                return false;
            }
        });
    }

    public void transicionActivity(Activity activity,int i) {
        switch (i){
            case 0:
                activity.overridePendingTransition(R.anim.zoom_back_in,R.anim.zoom_back_out);
                break;
            case 1:
                activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case 2:
                activity.overridePendingTransition(R.anim.left_in,R.anim.left_out);
                break;
            case 3:
                activity.overridePendingTransition(R.anim.right_in,R.anim.right_out);
                break;
        }
    }
}
