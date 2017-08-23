package com.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
 
public class MainActivity extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 6000;
    MediaPlayer song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        //code that displays the content in full screen mode  
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    	song = MediaPlayer.create(getApplicationContext(), R.raw.super_mario);
		song.start();
 
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(getApplicationContext(), Second.class);
                startActivity(i);
 
                // close this activity
                finish();
                song.release();
            }
        }, SPLASH_TIME_OUT);
    }
 
}



