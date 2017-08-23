package com.exam;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity
{
	private EditText t1,t2;
	private Button b1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.button1);
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				int a,x,s = 0;
	    		a = Integer.parseInt(t1.getText().toString());
	    		while(a != 0){
	    			x = a%10;
	    			a = a/10;
	    			s = s + x;
	    		}
	    		t2.setText(String.valueOf(s));
	    		Toast.makeText(getApplicationContext(), String.valueOf(s), Toast.LENGTH_LONG).show();
				// TODO Auto-generated method stub
				
			}
		});
        
        t1 = (EditText)findViewById(R.id.editText1);
    }
 
    /*	public void onClick(View v) {
    		int a,x,s = 0;
    		a = Integer.parseInt(t1.getText().toString());
    		while(a != 0){
    			x = a%10;
    			a = a/10;
    			s = s + x;
    		}
    		t2.setText(String.valueOf(s));
    		Toast.makeText(getApplicationContext(), String.valueOf(s), Toast.LENGTH_LONG).show();
    	}*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
