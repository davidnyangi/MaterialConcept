package com.davidnyangi.ccbrt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {

    private static final long SPLASH_SCREEN_DELAY = 2500;


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

//        ImageView myImageView= (ImageView)findViewById(R.id.logo);
//        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
//        myImageView.startAnimation(myFadeInAnimation);
//        TextView karibu = (TextView)findViewById(R.id.karibu);
//        Animation myFadeInAnimation2 = AnimationUtils.loadAnimation(this, R.anim.fadein);
//        myImageView.startAnimation(myFadeInAnimation2);
//        kenBurnsView = (KenBurnsView) findViewById(R.id.image);
        //Random rand = new Random();

        //int image = rand.nextInt((3) + 1);

        ImageView imgSplash = (ImageView) findViewById(R.id.imageSplash);
        Glide.with(this)
                .load(R.drawable.logo)
                .asGif()
                .crossFade()
                .into(imgSplash);

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