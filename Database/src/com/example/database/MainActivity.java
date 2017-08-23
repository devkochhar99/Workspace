package com.example.database;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.content.*;
public class MainActivity extends Activity {
Button b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent ob=new Intent(getApplicationContext(),Login.class);
				startActivity(ob);
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
