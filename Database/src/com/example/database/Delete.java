package com.example.database;

import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

public class Delete extends Activity {

	Button deleteb;
	SQLiteDatabase sqldb;
	AutoCompleteTextView actv;
	TextView tv1;
	String adapterValues[],selectedAdapterValue;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
		//use for changing color of action bar
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#01A9DB")));
		tv1=(TextView)findViewById(R.id.textView3);
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Drop Database");
		builder.setIcon(R.drawable.ic_launcher);
		
		actv=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		deleteb=(Button)findViewById(R.id.button2);
		
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
				actv.setEnabled(false);
				deleteb.setEnabled(false);
			}
			else
			{
				addValuesToACTV(count,cursor);
			}
			
			//Toast.makeText(getApplicationContext(),""+count+" Entries",Toast.LENGTH_LONG).show();
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(),e.toString()+ "ERROR in button 2",Toast.LENGTH_SHORT).show();
		}

		deleteb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try
				{
					if(actv.getText().toString().equals(""))
					{
						Toast.makeText(getApplicationContext(),"Enter Name",Toast.LENGTH_SHORT).show();
	
					}
					else
					{
						try
						{
							if(selectedAdapterValue!=null)
							{
								String abc[]=selectedAdapterValue.split("\n  ");
								if(!abc[0].equals(actv.getText()))
								{
									abc[0]=actv.getText().toString();						
								}
								sqldb=openOrCreateDatabase("VJM",MODE_PRIVATE,null);
								Cursor cursor=sqldb.rawQuery("Select * from followers where name='"+abc[0].trim()+"' and mobileno='"+abc[2].trim()+"' and status='1'",null);
								int count=cursor.getCount();
								if(count!=0)
								{
									sqldb.execSQL("update followers set status='0' where name='"+abc[0].trim()+"' and mobileno='"+abc[2].trim()+"'");	
									Toast.makeText(getApplicationContext(), "Values Deleted",Toast.LENGTH_LONG).show();
									actv.setText("");
									cursor=sqldb.rawQuery("Select * from followers where status='1'",null);
									count=cursor.getCount();
									addValuesToACTV(count,cursor);
									tv1.setText(count+" Entries");
									if(count==0)
									{
										actv.setEnabled(false);
										deleteb.setEnabled(false);
										Toast.makeText(getApplicationContext(),"Database Empty",Toast.LENGTH_LONG).show();
										
									}
								}
								else
								{
									Toast.makeText(getApplicationContext(),"Name does not exists",Toast.LENGTH_LONG).show();
								}
							
							}
							else
							{
								Toast.makeText(getApplicationContext(),"Name does not exists",Toast.LENGTH_LONG).show();
							}
						
								
						}
						catch(Exception ex)
						{
							Toast.makeText(getApplicationContext(), "Error in Deletion"+ex,Toast.LENGTH_LONG).show();
						}
					}
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "Name does not exists",Toast.LENGTH_SHORT).show();
				
				}
			}
		});

	}
	public void addValuesToACTV(int count,Cursor cursor)
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
                 //Toast.makeText(getApplicationContext(),"index = "+index,Toast.LENGTH_LONG).show();
                 selectedAdapterValue=actv.getText().toString();
                 String abc[]=selectedAdapterValue.split("\n  ");
				 actv.setText(abc[0]);
            }
        });
	    
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
		getMenuInflater().inflate(R.menu.delete, menu);
		return true;
	}

}