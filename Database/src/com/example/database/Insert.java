package com.example.database;

import java.util.Calendar;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Insert extends Activity {

	Button insertb,clearb;
	SQLiteDatabase sqldb;
	EditText dob,dom,fname,address,groupArea,zone,officeno,mobileno;
	int day,month,year;
	ImageButton dobButton,domButton;
	Calendar cal;
	int activebutton;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);
		
		//use for changing color of action bar
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#01A9DB")));
		
		dobButton=(ImageButton)findViewById(R.id.dobButton);
		dobButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				activebutton=1;
				showDialog(0);
				}
		});
		domButton=(ImageButton)findViewById(R.id.domButton);
		domButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				activebutton=2;
				showDialog(0);
				}
		});
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);

		dob=(EditText)findViewById(R.id.dob);
		dom=(EditText)findViewById(R.id.dom);
		fname=(EditText)findViewById(R.id.followername);
		address=(EditText)findViewById(R.id.address);
		groupArea=(EditText)findViewById(R.id.groupArea);
		zone=(EditText)findViewById(R.id.zone);
		officeno=(EditText)findViewById(R.id.office);
		mobileno=(EditText)findViewById(R.id.mobile);
		
		insertb=(Button)findViewById(R.id.button3);
		clearb=(Button)findViewById(R.id.button2);

		insertb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if((dob.getText().toString().equals(""))||(dom.getText().toString().equals(""))||(fname.getText().toString().equals(""))||(address.getText().toString().equals(""))||(groupArea.getText().toString().equals(""))||(zone.getText().toString().equals("")))
				{
					Toast.makeText(getApplicationContext(),"Enter Complete Details",Toast.LENGTH_SHORT).show();

				}
				
				else if(mobileno.getText().length()!=10)
				{
					Toast.makeText(getApplicationContext(),"Wrong Mobile No.",Toast.LENGTH_SHORT).show();
				}
				else
				{
					
					try
					{
						
						//int a1=Integer.parseInt(a);
						
						String followerName=fname.getText().toString();
						String address1=address.getText().toString();
						String groupArea1=groupArea.getText().toString();
						String zone1=zone.getText().toString();
						String officeno1=officeno.getText().toString();
						String mobileno1=mobileno.getText().toString();
						String dBirth=dom.getText().toString();
						String dMarriage=dob.getText().toString();
						
						
						
						sqldb=openOrCreateDatabase("VJM",MODE_PRIVATE,null);
						sqldb.execSQL("insert into followers (name,address,groupArea,zone,officeno,mobileno,dob,dom,status) values('"+followerName +"','"+address1 +"','"+groupArea1 +"','"+zone1 +"','"+officeno1 +"','"+mobileno1 +"','"+dBirth +"','"+dMarriage+"','"+1+"')");	
						Toast.makeText(getApplicationContext(), "Values Inserted Successfully",Toast.LENGTH_SHORT).show();
					}
					catch(Exception ex)
					{
						Toast.makeText(getApplicationContext(), "ex"+ex,Toast.LENGTH_SHORT).show();
						//Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_SHORT).show();
					}

				}
			}
		});
		clearb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				fname.setText("");address.setText("");groupArea.setText("");
				zone.setText("");officeno.setText("");mobileno.setText("");dom.setText("");dob.setText("");
			}
		});


	}
	protected Dialog onCreateDialog(int id)
	{
		return new DatePickerDialog(this, datePickerListener, year, month, day);

	}
	
	DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener()
	{
		  public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) 
		  {
			  String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			  if(activebutton==1)
			  {
				  dob.setText(selectedDay+"-"+(month[selectedMonth])+"-"+selectedYear);
			  }
			  else
			  {
				  dom.setText(selectedDay+"-"+(month[selectedMonth])+"-"+selectedYear);
			  }
		  }
	};
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
		getMenuInflater().inflate(R.menu.insert, menu);
		return true;
	}

}