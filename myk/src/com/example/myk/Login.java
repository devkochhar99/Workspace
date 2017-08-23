package com.example.myk;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
Button b1;
EditText t1,t2;
SQLiteDatabase sqldb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		b1 = (Button) findViewById(R.id.button1);
		t1  = (EditText) findViewById(R.id.editText1);
		t2  = (EditText) findViewById(R.id.editText2);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				

				try
				{
					
					sqldb=openOrCreateDatabase("VJM1",MODE_PRIVATE,null);
					//Toast.makeText(getApplicationContext(), "Database Created",Toast.LENGTH_SHORT).show();
					//String deleteTable="drop table aman1DB";
					//sqldb.execSQL(deleteTable);
					
					Cursor c =sqldb.rawQuery("select * from adminlogin where name = '"+t1.getText().toString()+"' and dob = '"+t2.getText().toString()+"' ",null);
				
					//Toast.makeText(getApplicationContext(),count,Toast.LENGTH_LONG).show();
					  if (c.getCount() > 0 )
				        {
							c.moveToFirst();
							String s = c.getString(0).toString();
							 Toast.makeText(getApplicationContext(),""+s+c.getString(2).toString(),2000).show();
							 		  
						  Toast.makeText(getApplicationContext(),"Mission Successful!!!",2000).show();}
				        	
					  else{
						  
						  Toast.makeText(getApplicationContext(),"Wrong username or password", 2000).show();
					  }
					  
				}catch(Exception e){Toast.makeText(getApplicationContext(),""+e, 500).show();	}

		Toast.makeText(getApplicationContext(), "Hello", 500).show();	
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
