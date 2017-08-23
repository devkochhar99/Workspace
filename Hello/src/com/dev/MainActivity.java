package com.dev;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.*;
import android.view.*;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {
	private EditText t1,t2,t3;
	private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.button1);
    	b1.setOnClickListener(this); 
        t1 = (EditText)findViewById(R.id.editText1);
		t2 = (EditText)findViewById(R.id.editText2);
		t3 = (EditText)findViewById(R.id.editText3);
		
    }
    public void onClick(View v){
    		String s1,s2;
    		
    		s1 = t1.getText().toString();
    		s2 = t2.getText().toString();
    		
    		int a,b,c;
    		a = Integer.parseInt(s1);
    		b = Integer.parseInt(s2);
    		c = a + b;
    		t3.setText(String.valueOf(c));
    		Toast.makeText(getApplicationContext(),String.valueOf(c),Toast.LENGTH_LONG).show();
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
