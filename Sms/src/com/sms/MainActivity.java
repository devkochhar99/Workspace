package com.sms;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	EditText phonenoET,ucodeET;
	Button sendb,nextb,forgotpasswordb;
	SQLiteDatabase sqldb;
	String uniquecode;
	NotificationManager mNotificationManager;
	NotificationCompat.Builder mBuilder;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//use for changing color of action bar
				getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#01A9DB")));
						
				mBuilder = new NotificationCompat.Builder(this);
				
				phonenoET=(EditText)findViewById(R.id.editText1);
				ucodeET=(EditText)findViewById(R.id.editText2);
				sendb=(Button)findViewById(R.id.button2);
				nextb=(Button)findViewById(R.id.button3);
				final AlertDialog.Builder builder=new AlertDialog.Builder(this);
				sendb.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try
						{
							String phoneno=phonenoET.getText().toString();
							if(phoneno.equals(""))
							{
								Toast.makeText(getApplicationContext(),"Enter Phone No.",Toast.LENGTH_LONG).show();
							}
							else if(phoneno.length()!=10)
							{
								Toast.makeText(getApplicationContext(),"Wrong Phone No.",Toast.LENGTH_LONG).show();
							}
							else
							{
								//uniquecode="hello";
								//double code=Math.random();
								//Toast.makeText(getApplicationContext(),""+code+"",Toast.LENGTH_LONG).show();
								uniquecode=Integer.toString((int)(Math.random()*1000000000));
								//Toast.makeText(getApplicationContext(),"Unique CODE: "+uniquecode,Toast.LENGTH_LONG).show();
								SmsManager sms=SmsManager.getDefault();
								sms.sendTextMessage(phoneno, null,"Unique CODE: "+uniquecode, null, null);
								Toast.makeText(getApplicationContext(),"Msg Sent",Toast.LENGTH_SHORT).show();
								Date date = new Date();
								SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
								String currentDate = dateFormat.format(date);
								SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
								String currentTime = timeFormat.format(date);
								
								mBuilder.setContentTitle("Unique Code Sent");
								mBuilder.setContentText("to "+phoneno+"\non "+currentDate+"");
								mBuilder.setTicker("Password Alert!");
								mBuilder.setSmallIcon(R.drawable.ic_launcher);
								//mBuilder.setNumber(++numMessages);
								
								mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
								/* notificationID allows you to update the notification later on. */ 
								mNotificationManager.notify((int)(Math.random()*1000), mBuilder.build());
								
								//Intent i=new Intent(getApplicationContext(),Read_Activity.class);
								//startActivity(i);
								phonenoET.setEnabled(false);
								sendb.setEnabled(false);
								ucodeET.setVisibility(0);
								nextb.setVisibility(0);
								
							}
						}
						catch(Exception ex)
						{
							Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
							Toast.makeText(getApplicationContext(),"Msg not Sent",Toast.LENGTH_LONG).show();
						}
					}
					});
					
				nextb.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						try
						{
							String uniquecode1=ucodeET.getText().toString();
							if(uniquecode1.equals(""))
							{
								Toast.makeText(getApplicationContext(),"Enter Unique Code",Toast.LENGTH_LONG).show();
							}
							else
							{/*
								if(uniquecode1.equals(uniquecode))
								{*/
									/*
									String password="";
									sqldb=openOrCreateDatabase("aman",MODE_PRIVATE,null);
									Cursor c =sqldb.rawQuery("select * from aman1DB",null);
									int count=c.getCount();
									if (c.getCount() > 0) 
							        {
									    c.moveToFirst();
									    password=c.getString(0);
									    */
									
								/*	} 
							       else 
							      {
										Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_LONG).show();
							     }*/
								    Toast.makeText(getApplicationContext(),"Code Matched",Toast.LENGTH_LONG).show();
								    builder.setIcon(R.drawable.ic_launcher);
									//builder.setTitle("Exit");
									builder.setMessage("Kindly Change Your Password !!")
									    //.setCancelable(false)
									    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
									    public void onClick(DialogInterface dialog, int id) {
									    //System.exit(0);
									    	finish();
											Intent i=new Intent(getApplicationContext(),Login.class);
											startActivity(i);
									    }
									    });
									AlertDialog alert = builder.create();
									 alert.setTitle("PASSWORD");
									 alert.show();

									//Toast.makeText(getApplicationContext(),password,Toast.LENGTH_LONG).show();
									
								}
							/*	else
								{
									Toast.makeText(getApplicationContext(),"Wrong Code",Toast.LENGTH_LONG).show();
									ucodeET.setText("");
								}*/
								
							}
					//	}
						catch(Exception ex)
						{
							Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
							Toast.makeText(getApplicationContext(),"Msg not Sent",Toast.LENGTH_LONG).show();
						}
						
						}
					});

		
		 }
}
