package com.example.database;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.System;
import android.annotation.SuppressLint;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends Activity {

	Button searchb;
	ImageButton callb,msgb,callfb,msgfb;
	SQLiteDatabase sqldb;
	AutoCompleteTextView actv;
	EditText address,groupArea,zone,officeno,mobileno,dob,dom;
	TextView tv1,StatusTextView;
	String adapterValues[],selectedAdapterValue;
	int callmsg=0;
	int index;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		//use for changing color of action bar
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#01A9DB")));
		
		tv1=(TextView)findViewById(R.id.textView12);
		StatusTextView=(TextView)findViewById(R.id.statusTextView);
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Drop Database");
		builder.setIcon(R.drawable.ic_launcher);
		
		actv=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		address=(EditText)findViewById(R.id.editText2);
		groupArea=(EditText)findViewById(R.id.editText3);
		zone=(EditText)findViewById(R.id.editText4);
		officeno=(EditText)findViewById(R.id.editText5);
		mobileno=(EditText)findViewById(R.id.editText6);
		dob=(EditText)findViewById(R.id.editText7);
		dom=(EditText)findViewById(R.id.editText8);
		searchb=(Button)findViewById(R.id.button2);
		callb=(ImageButton)findViewById(R.id.imageButton1);
		msgb=(ImageButton)findViewById(R.id.imageButton2);
		callfb=(ImageButton)findViewById(R.id.imageButton3);
		msgfb=(ImageButton)findViewById(R.id.imageButton4);
		
		try
		{
			sqldb=openOrCreateDatabase("VJM",MODE_PRIVATE,null);
			Cursor cursor=sqldb.rawQuery("Select * from followers where status='1'",null);
			int count=cursor.getCount();
			tv1.setText(count+" Entries");
			if(count==0)
			{
				final AlertDialog.Builder builder1=new AlertDialog.Builder(this);
				//builder1.setTitle("Exit");
				builder1.setIcon(R.drawable.ic_launcher);
				builder1.setMessage("NO DATA")
				    //.setCancelable(false)
				    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int id) {
				    //System.exit(0);
				    	finish();

						Intent i=new Intent(getApplicationContext(),Welcome.class);
						startActivity(i);

				    }
				    });
				AlertDialog alert = builder1.create();
				 alert.setTitle("Database Empty");
				 alert.show();
				 EditText ed[]={address,groupArea,zone,officeno,mobileno,dob,dom};
				 for(int i=0;i<ed.length;i++)
				 {
					 ed[i].setEnabled(false);
				 }
				 actv.setEnabled(false);
				 searchb.setEnabled(false);
			}
			else
			{
				adapterValues=new String[count];
				if(cursor.moveToFirst())
				{
					int i=0;
					do
					{
						adapterValues[i]=cursor.getString(1)+"\n   "+cursor.getString(3)+"\n   "+cursor.getString(6);
						i++;
					}
					while(cursor.moveToNext());
				}
				actv=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
				ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.select_dialog_item,adapterValues);  
			    actv.setThreshold(1);
			    actv.setAdapter(adapter);
			    actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	                @Override
	                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	                    //// id contains item if from database
	                     index = (int)id;
	                     //Toast.makeText(getApplicationContext(),"index = "+index,Toast.LENGTH_LONG).show();
	                     selectedAdapterValue=actv.getText().toString();
	                     String abc[]=selectedAdapterValue.split("\n  ");
						 actv.setText(abc[0]);
	                }
	            });
			    
			}
			
			//Toast.makeText(getApplicationContext(),""+count+" Entries",Toast.LENGTH_LONG).show();
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "ERROR in button 2"+e,Toast.LENGTH_SHORT).show();
		}
		searchb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				address.setText("");
				groupArea.setText("");
				zone.setText("");
				officeno.setText("");
				mobileno.setText("");
				dob.setText("");
				dom.setText("");
				callmsg=0;
				StatusTextView.setVisibility(View.GONE);
				
				if(actv.getText().toString().equals(""))
				{
					Toast.makeText(getApplicationContext(),"Enter Name",Toast.LENGTH_SHORT).show();
					address.setText("");groupArea.setText("");zone.setText("");officeno.setText("");
					mobileno.setText("");dob.setText("");dom.setText("");
				}
				else
				{
					try
					{
						String abc[]=selectedAdapterValue.split("\n  ");
						if(!abc[0].equals(actv.getText()))
						{
							abc[0]=actv.getText().toString();
							
						}
						//Toast.makeText(getApplicationContext(),abc[0].trim(),Toast.LENGTH_LONG).show();
						//Toast.makeText(getApplicationContext(),abc[1].trim(),Toast.LENGTH_LONG).show();
						sqldb=openOrCreateDatabase("VJM",MODE_PRIVATE,null);
						Cursor c =sqldb.rawQuery("select * from followers where status='1' and name='"+abc[0].trim()+"' and mobileno='"+abc[2].trim()+"'",null);
						//Cursor c =sqldb.rawQuery("select * from a",null);
						int count=c.getCount();
						//Toast.makeText(getApplicationContext(),count,Toast.LENGTH_LONG).show();
						  if (c.getCount() > 0) 
					        {
								//ed1.setText(c.getString(0));
							    c.moveToFirst();
								address.setText(c.getString(2));
								groupArea.setText(c.getString(3));
								zone.setText(c.getString(4));
								officeno.setText(c.getString(5));
								mobileno.setText(c.getString(6));
								dob.setText(c.getString(7));
								dom.setText(c.getString(8));
								StatusTextView.setVisibility(View.VISIBLE);
								if(c.getString(9).toString().equals("1"))
								{
									StatusTextView.setText("Active");
									//StatusTextView.setTextColor(Color.parseColor("#01A9DB"));
									StatusTextView.setBackgroundColor(Color.parseColor("#90EE90"));
									 
								}
								else
								{
									StatusTextView.setText("Inactive");
									//StatusTextView.setTextColor(Color.parseColor("#FF0000"));
									StatusTextView.setBackgroundColor(Color.parseColor("#FF0000"));
									
								}
								
								callmsg=1;
					        } 
					        else 
					        {
								Toast.makeText(getApplicationContext(),"Name does not exists",Toast.LENGTH_LONG).show();
								address.setText("");
								groupArea.setText("");
								zone.setText("");
								officeno.setText("");
								mobileno.setText("");
								dob.setText("");
								dom.setText("");
								callmsg=0;
								
					        }
					            
						//int count=c.getCount();
						//Toast.makeText(getApplicationContext(),count,Toast.LENGTH_LONG).show();
										}
					catch(Exception ex)
					{
						Toast.makeText(getApplicationContext(), "Record does not exist",Toast.LENGTH_LONG).show();
						//Toast.makeText(getApplicationContext(),(CharSequence) ex,Toast.LENGTH_LONG).show();
					}

				}
				}
		});
		callb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			    if(callmsg==1)
			    {
			    	get(0);			    
			    }
				
			}
		});
		callfb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(callmsg==1)
			    {
			    	get(1);			    
			    }
							    
				
			}
		});
		msgb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(callmsg==1)
			    {
			    	get(2);			    
			    }
			}
		});
		msgfb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(callmsg==1)
			    {
			    	get(3);			    
			    }
				
			   
			}
		});
	
	}
	void get(int n)
	{
		try
	    {
	    	if(actv.getText().toString().equals(""))
			{
				Toast.makeText(getApplicationContext(),"Enter Id",Toast.LENGTH_SHORT).show();

			}
			/*else
			{
				String id=actv.getText().toString();
				String name=ed3.getText().toString();
				String phno="0"+ed4.getText().toString();
				String fname=ed6.getText().toString();
				String fphno="0"+ed7.getText().toString();
				if(n==0)
				{
					Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phno+""));
					startActivity(i);
				}
				if(n==1)
				{
					Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+fphno+""));
					startActivity(i);
				}
				if(n==2)
				{
					finish();
					Intent i=new Intent(getApplicationContext(),Message.class);
					i.putExtra("id",id);
					i.putExtra("name",name);
					i.putExtra("fname","");
					i.putExtra("phoneno",phno);
					startActivity(i);
				}
				if(n==3)
				{
					finish();
					Intent i=new Intent(getApplicationContext(),Message.class);
					i.putExtra("id",id);
					i.putExtra("name",name);
					i.putExtra("fname",fname);
					i.putExtra("phoneno",fphno);
					startActivity(i);
				}
				
			}*/
	    }
	    catch(Exception e)
	    {
	    	Toast.makeText(getApplicationContext(), "error",Toast.LENGTH_SHORT).show();
	    }

	}

	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();

		Intent i=new Intent(getApplicationContext(),Welcome.class);
		startActivity(i);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
