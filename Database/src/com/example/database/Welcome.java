package com.example.database;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends Activity {

	TextView textView;
	Button insertb,deleteb,searchb,updateb,contactsb,resetb,msgtoallb,changepasswordb,historyb;
	SQLiteDatabase sqldb;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		//use for changing color of action bar
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#01A9DB")));
		
		//used to hide action bar
		//getActionBar().hide();
		
		final AlertDialog.Builder builder1=new AlertDialog.Builder(this);
		
		textView=(TextView)findViewById(R.id.textView1);
		final AlertDialog.Builder builder2=new AlertDialog.Builder(this);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				builder2.setTitle("Exit");
				builder2.setIcon(R.drawable.ic_launcher);
				builder2.setMessage("Welcome to\n\"Contact Management System\"\n" +
						"This application provides an easy way to manage Contacts...")
				    //.setCancelable(false)
				    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int id) {
				    //System.exit(0);
				    }
				    });
				AlertDialog alert = builder2.create();
				 alert.setTitle("About Contact Management System:");
				 alert.show();

			}
		});

		insertb=(Button)findViewById(R.id.button2);
		searchb=(Button)findViewById(R.id.button3);
		updateb=(Button)findViewById(R.id.button4);
		deleteb=(Button)findViewById(R.id.button5);
		
		createDatabase();
		
		insertb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    finish();

			    Intent i=new Intent(getApplicationContext(),Insert.class);
				startActivity(i);

				}
			});
		searchb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    finish();

			    Intent i=new Intent(getApplicationContext(),Search.class);
				startActivity(i);

				}
			});

		updateb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    finish();

			    Intent i=new Intent(getApplicationContext(),Update.class);
				startActivity(i);

				}
			});
		deleteb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    finish();

			    Intent i=new Intent(getApplicationContext(),Delete.class);
				startActivity(i);

				}
			});
		
		

	}	
	void createDatabase()
	{
		try
		{
			sqldb=openOrCreateDatabase("VJM",MODE_PRIVATE,null);
			//Toast.makeText(getApplicationContext(), "Database Created",Toast.LENGTH_SHORT).show();
			
			
			String createTable="create table IF NOT EXISTS followers(id INTEGER primary key autoincrement, name char(50),address char(50)," +
					"groupArea char(20),zone char(20),officeno char(10),mobileno char(11),dob char(12),dom char(12), status int(1))";
			sqldb.execSQL(createTable);
			Toast.makeText(getApplicationContext(), "Table Created",Toast.LENGTH_SHORT).show();
			
			String createTable1="create table IF NOT EXISTS history(msgid int(5),name char(32),id int(10),date char(15),time char(15))";
			sqldb.execSQL(createTable1);
			
			
			//String deleteTable="drop table followers";
			//sqldb.execSQL(deleteTable);
			
			
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "ERROR "+e,Toast.LENGTH_LONG).show();
			Toast.makeText(getApplicationContext(), "ERROR "+e.toString(),Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();

	    Intent i=new Intent(getApplicationContext(),Login.class);
		startActivity(i);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

}
