package com.example.database;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	TextView textView;
	EditText passwordET;
	Button nextb,clearb,exitb,forgotpasswordb;
	SQLiteDatabase sqldb;
	String password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//use for changing color of action bar
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#01A9DB")));
		//#2E9AFE
		//use for full screen
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		textView=(TextView)findViewById(R.id.textView1);
		final AlertDialog.Builder builder2=new AlertDialog.Builder(this);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				builder2.setTitle("Exit");
				builder2.setIcon(R.drawable.ic_launcher);
				builder2.setMessage("Dev Kochhar(11401091)\n(CE-3,3rd Year)")
				    //.setCancelable(false)
				    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int id) {
				    //System.exit(0);
				    }
				    });
				AlertDialog alert = builder2.create();
				 alert.setTitle("Developed By:");
				 alert.show();

			}
		});
		
		passwordET=(EditText)findViewById(R.id.editText1);
		
		createDatabase();
		
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Exit");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("Do you want to close this application ?")
		    //.setCancelable(false)
		    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {
		    finish();
		    //System.exit(0);
		    }
		    })
		    .setNegativeButton("No", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {
		    //  Action for 'NO' Button
		    dialog.cancel();
		}
		});
		nextb=(Button)findViewById(R.id.button2);
		exitb=(Button)findViewById(R.id.button1);
		forgotpasswordb=(Button)findViewById(R.id.button3);
		nextb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/*if(passwordET.getText().toString().equals(""))
				{
					Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_LONG).show();

				}
				else
				{*/
					String value=passwordET.getText().toString();
					try
					{
						sqldb=openOrCreateDatabase("VJM",MODE_PRIVATE,null);
						Cursor c =sqldb.rawQuery("select * from adminlogin",null);
						int count=c.getCount();
						  if (c.getCount() > 0) 
					        {
								c.moveToFirst();
								if(value.equals(c.getString(0)))
								{
									Toast.makeText(getApplicationContext(), "Login SUCCESSFULLY",Toast.LENGTH_LONG).show();
									//System.exit(0);
									finish();
									Intent i=new Intent(getApplicationContext(),Welcome.class);
									//Intent i=new Intent(getApplicationContext(),Write_Message.class);
									startActivity(i);
									
									//closeContextMenu();
								}
								else
								{
									if(passwordET.getText().toString().equals(""))
									{
										Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_LONG).show();

									}
									else
									{
										Toast.makeText(getApplicationContext(), "Login FAILED",Toast.LENGTH_SHORT).show();
										passwordET.setText("");
										
									}
									//Toast.makeText(getApplicationContext(), "",Toast.LENGTH_SHORT).show();
								}
					        } 
					       else 
					        {
								Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_LONG).show();

					        }
					            
					}
					catch(Exception ex)
					{
						Toast.makeText(getApplicationContext(), "Error in Searching",Toast.LENGTH_LONG).show();
						//Toast.makeText(getApplicationContext(),(CharSequence) ex,Toast.LENGTH_LONG).show();
					}
				//}
				
			}
		});
		forgotpasswordb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				finish();
				Intent i=new Intent(getApplicationContext(),Forgot_Password.class);
				startActivity(i);
				
			}
		});
		exitb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				AlertDialog alert = builder.create();
						 alert.setTitle("Exit");
						 alert.show();


			}
		});
		
		
	}
	
	void createDatabase()
	{
		try
		{
			sqldb=openOrCreateDatabase("VJM",MODE_PRIVATE,null);
			//Toast.makeText(getApplicationContext(), "Database Created",Toast.LENGTH_SHORT).show();
			//String deleteTable="drop table aman1DB";
			//sqldb.execSQL(deleteTable);
			
			String createTable="create table IF NOT EXISTS adminlogin(password char(10))";
			sqldb.execSQL(createTable);
			//Toast.makeText(getApplicationContext(), "Table Created",Toast.LENGTH_SHORT).show();
			
			Cursor c =sqldb.rawQuery("select * from adminlogin",null);
			try{
				int count=c.getCount();
				if(count==0)
				{
					//Toast.makeText(getApplicationContext(),"no entry",Toast.LENGTH_LONG).show();
					sqldb.execSQL("insert into adminlogin values('admin')");
					
					final AlertDialog.Builder builder1=new AlertDialog.Builder(this);
					builder1.setTitle("Exit");
					builder1.setIcon(R.drawable.ic_launcher);
					builder1.setMessage("Your Password is \"admin\".\nPlease change after first login.")
					    //.setCancelable(false)
					    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog, int id) {
					    //System.exit(0);
					    }
					    });
					AlertDialog alert = builder1.create();
					 alert.setTitle("WELCOME");
					 alert.show();


				}
				else
				{
					//Toast.makeText(getApplicationContext(),""+count+"entry",Toast.LENGTH_LONG).show();
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				//Toast.makeText(getApplicationContext(),"exxeption",Toast.LENGTH_LONG).show();
			}
			
		}
		catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), "ERROR in createDatabase function",Toast.LENGTH_LONG).show();
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}