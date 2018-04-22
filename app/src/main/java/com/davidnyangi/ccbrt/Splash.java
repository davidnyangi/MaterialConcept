package com.davidnyangi.ccbrt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {

    private static final long SPLASH_SCREEN_DELAY = 3000;


    /*private int[] images = {
            R.drawable.logo_3
    };*/

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = Splash.this;
        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            decorView.setSystemUiVisibility(uiOptions);
        }

        ImageView myImageView= (ImageView)findViewById(R.id.logo_img);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_top);
        myImageView.startAnimation(myFadeInAnimation);
        TextView karibu = (TextView)findViewById(R.id.logo_txt);
        Animation myFadeInAnimation2 = AnimationUtils.loadAnimation(this, R.anim.slide_top);
        karibu.startAnimation(myFadeInAnimation2);

//
//        ImageView imgSplash = (ImageView) findViewById(R.id.splash_background);
//        Glide.with(this)
//                .load(R.drawable.logo)
//                .asGif()
//                .crossFade()
//                .into(imgSplash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent = new Intent().setClass(
                        Splash.this, Welcome.class);
                startActivity(mainIntent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();


                // Close the activity so the user won't able to go back this
                // activity pressing Back button

            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

}