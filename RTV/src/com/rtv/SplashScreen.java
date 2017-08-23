package com.rtv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
 
public class SplashScreen extends Activity {
 private boolean backbtnPress;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        //code that displays the content in full screen mode  
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
 
        new Handler().postDelayed(new Runnable() {

        	@Override
            public void run() {
                finish();
                if(!backbtnPress){
                	  Intent i = new Intent(SplashScreen.this, MyVoteMyRight.class);
                     SplashScreen.this.startActivity(i);
                }
            }
        }, 3000);
    }
    
    @Override
    public void onBackPressed() {
    	backbtnPress = true;
    	super.onBackPressed();
    }
}