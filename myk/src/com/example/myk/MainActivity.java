package com.example.myk;

import com.android.*;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	 EditText t11,t12,t13,t14;
	 Button b1,b2,b3,b4;
	 SQLiteDatabase sqldb;
	 Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		t11 = (EditText)findViewById(R.id.editText1);
		t12 = (EditText)findViewById(R.id.editText2);
		t13 = (EditText)findViewById(R.id.editText3);
		t14 = (EditText)findViewById(R.id.editText4);
		
		b1 = (Button)findViewById(R.id.button1);
		b2 = (Button)findViewById(R.id.button2);
		b3 = (Button)findViewById(R.id.button3);
		b4 = (Button)findViewById(R.id.button4);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				// TODO Auto-generated method stub
				String str=t11.getText().toString();
				String str1=t12.getText().toString();
				String str2=t13.getText().toString();
				String str3=t14.getText().toString();
				
				if(str.equals(""))
				{
					
					Toast.makeText(getApplicationContext(),"Enter comete details", 2000).show();
				}
				else{
					try
					{
						sqldb=openOrCreateDatabase("VJM1",MODE_PRIVATE,null);
						//Toast.makeText(getApplicationContext(), "Database Created",Toast.LENGTH_SHORT).show();
						//String deleteTable="drop table aman1DB";
						//sqldb.execSQL(deleteTable);
						
						String createTable="create table IF NOT EXISTS adminlogin(name char(50),age char(3),dob char(20),address char(50))";
						sqldb.execSQL(createTable);
						Toast.makeText(getApplicationContext(), "Table Created",Toast.LENGTH_SHORT).show();
						
						Cursor c =sqldb.rawQuery("select * from adminlogin",null);
						Toast.makeText(getApplicationContext(), "Table Created",Toast.LENGTH_SHORT).show();
						try{
							
							int count=c.getCount();
							
						//Toast.makeText(getApplicationContext(),"no entry",Toast.LENGTH_LONG).show();
								sqldb.execSQL("insert into adminlogin values('" + str + "','" + str1 + "','" + str2 + "','" + str3 +"')");
								Toast.makeText(getApplicationContext(),"Stored",Toast.LENGTH_LONG).show();

						
						
							}
						catch(Exception e) {Toast.makeText(getApplicationContext(), "ex"+e,Toast.LENGTH_SHORT).show();}
			}catch(Exception e){}
					}
				

			Toast.makeText(getApplicationContext(), "Hello", 500).show();	
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			

				try
				{
					sqldb=openOrCreateDatabase("VJM1",MODE_PRIVATE,null);
					//Toast.makeText(getApplicationContext(), "Database Created",Toast.LENGTH_SHORT).show();
					//String deleteTable="drop table aman1DB";
					//sqldb.execSQL(deleteTable);
					
					Cursor c =sqldb.rawQuery("select * from adminlogin where name ='"+"ankit"+"' ",null);
					
					//Toast.makeText(getApplicationContext(),count,Toast.LENGTH_LONG).show();
					  if (c.getCount() > 0) 
				        {
							//ed1.setText(c.getString(0));
						    c.moveToFirst();
							t11.setText(c.getString(0));
							t12.setText(c.getString(1));
							t13.setText(c.getString(2));
							t14.setText(c.getString(3));
				        }	
					  
				}catch(Exception e){Toast.makeText(getApplicationContext(),""+e, 500).show();	}

		Toast.makeText(getApplicationContext(), "Hello", 500).show();	
		
		}
		});
		
		
b4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				Intent ob = new Intent(getApplicationContext(),Login.class);
				startActivity(ob);
				
				
			}
		});
		
b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				t11.setText("");
				t12.setText("");
				t13.setText("");
				t14.setText("");
				
				
			}
		});
		

}

	public void complete()  {
  		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

			// set title
			

			// set dialog message
			alertDialogBuilder
				.setMessage("Please enter complete details and then press /'Create Account'/ Button")
				.setCancelable(false)
					.setNegativeButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
       });

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
  		
  	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
