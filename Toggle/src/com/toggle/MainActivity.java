package com.toggle;

import android.os.Bundle;
import android.view.*;
import android.app.Activity;
import android.view.Menu;
import android.widget.*;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {
   private ToggleButton t1,t2;
   private Button b1;
   
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (ToggleButton)findViewById(R.id.toggleButton1);
        t2 = (ToggleButton)findViewById(R.id.toggleButton2); 
        b1 = (Button)findViewById(R.id.button1);
        b1.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		StringBuilder s = new StringBuilder();
        		s.append("Toggle Button 1 :").append(t1.getText());
        		s.append("\nToggle Button 2 :").append(t2.getText());
        		Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_LONG).show();
        		}
        	
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
