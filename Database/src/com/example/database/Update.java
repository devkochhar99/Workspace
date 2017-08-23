package com.example.database;

import java.util.Calendar;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;  

@SuppressLint("NewApi")
public class Update extends Activity {

	Button searchb,updateb,clearb,refreshb,statusButton;
	ImageButton callb,msgb,callfb,msgfb;
	SQLiteDatabase sqldb;
	AutoCompleteTextView actv;
	EditText address,groupArea,zone,officeno,mobileno,dob,dom;
	TextView tv1,StatusTextView;
	String adapterValues[],selectedAdapterValue;
	int callmsg=0;
	int index,FollowerID,activebutton,day,month,year;
	Calendar cal;
	ImageButton dobButton,domButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
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
		statusButton=(Button)findViewById(R.id.status);
		//statusButton.setBackgroundColor(Color.parseColor("#F2F2F2"));
		statusButton.setEnabled(false);
		statusButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(statusButton.getText().toString().equals("Active"))
				{
					statusButton.setText("Inactive");
					statusButton.setBackgroundColor(Color.parseColor("#FF0000"));

					
				}
				else
				{
					statusButton.setText("Active");
					statusButton.setBackgroundColor(Color.parseColor("#90EE90"));

				}
			}
		});
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		
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
		updateb=(Button)findViewById(R.id.button4);
		refreshb=(Button)findViewById(R.id.refresh);
		clearb=(Button)findViewById(R.id.button3);
		
		updateb.setEnabled(false);
		
		try
		{
			sqldb=openOrCreateDatabase("VJM",MODE_PRIVATE,null);
			Cursor cursor=sqldb.rawQuery("Select * from followers",null);
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
				 updateb.setEnabled(false);
				 clearb.setEnabled(false);
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
						Cursor c =sqldb.rawQuery("select * from followers where name='"+abc[0].trim()+"' and mobileno='"+abc[2].trim()+"'",null);
						//Cursor c =sqldb.rawQuery("select * from a",null);
						int count=c.getCount();
						//Toast.makeText(getApplicationContext(),count,Toast.LENGTH_LONG).show();
						  if (c.getCount() > 0) 
					        {
								//ed1.setText(c.getString(0));
							    c.moveToFirst();
							    FollowerID=c.getInt(0);
								address.setText(c.getString(2));
								groupArea.setText(c.getString(3));
								zone.setText(c.getString(4));
								officeno.setText(c.getString(5));
								mobileno.setText(c.getString(6));
								dob.setText(c.getString(7));
								dom.setText(c.getString(8));
								StatusTextView.setVisibility(View.VISIBLE);
								updateb.setEnabled(true);
								statusButton.setEnabled(true);
								if(c.getString(9).toString().equals("1"))
								{
									StatusTextView.setText("Active");
									//StatusTextView.setTextColor(Color.parseColor("#01A9DB"));
									StatusTextView.setBackgroundColor(Color.parseColor("#90EE90"));
									statusButton.setBackgroundColor(Color.parseColor("#90EE90"));
									statusButton.setText("Active");

								}
								else
								{
									StatusTextView.setText("Inactive");
									//StatusTextView.setTextColor(Color.parseColor("#FF0000"));
									StatusTextView.setBackgroundColor(Color.parseColor("#FF0000"));
									statusButton.setBackgroundColor(Color.parseColor("#FF0000"));
									statusButton.setText("Inactive");
									
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
								statusButton.setEnabled(false);
								//statusButton.setBackgroundColor((Integer) null);
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
		updateb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				if((actv.getText().toString().equals("")))
				{
					Toast.makeText(getApplicationContext(),"Enter Name",Toast.LENGTH_SHORT).show();
					updateb.setEnabled(false);
				}
				else if((dob.getText().toString().equals(""))||(dom.getText().toString().equals(""))||(address.getText().toString().equals(""))||(groupArea.getText().toString().equals(""))||(zone.getText().toString().equals("")))
				{
					Toast.makeText(getApplicationContext(),"Enter Complete Details",Toast.LENGTH_SHORT).show();
					updateb.setEnabled(false);
				}				
				else if(mobileno.getText().length()!=10)
				{
					Toast.makeText(getApplicationContext(),"Wrong Mobile No.",Toast.LENGTH_SHORT).show();
					updateb.setEnabled(false);
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
						//Toast.makeText(getApplicationContext(),actv,Toast.LENGTH_LONG).show();
						sqldb=openOrCreateDatabase("VJM",MODE_PRIVATE,null);
						Cursor cursor =sqldb.rawQuery("select * from followers where name='"+abc[0].trim()+"' and mobileno='"+abc[2].trim()+"'",null);
						//Cursor c =sqldb.rawQuery("select * from a",null);
						int count=cursor.getCount();
						if(count==0)
						{
							Toast.makeText(getApplicationContext(), "Name does not exists",Toast.LENGTH_SHORT).show();
						}
						else
						{					
							//String a=actv.getText().toString();
							//int a1=Integer.parseInt(a);
							String abc1[]=selectedAdapterValue.split("\n  ");
							String address1=address.getText().toString();
							String groupArea1=groupArea.getText().toString();
							String zone1=zone.getText().toString();
							String officeno1=officeno.getText().toString();
							String mobileno1=mobileno.getText().toString();
							String dBirth=dob.getText().toString();
							String dMarriage=dom.getText().toString();
							String status=statusButton.getText().toString();
							if(status.equals("Active"))
							{
								status="1";
							}
							else
							{
								status="0";
							}
							
							sqldb=openOrCreateDatabase("VJM",MODE_PRIVATE,null);
							sqldb.execSQL("update followers set address='"+address1+"',groupArea='"+groupArea1+"',zone='"+zone1+"',"+
										  	"officeno='"+officeno1+"',mobileno='"+mobileno1+"',dob='"+dBirth+"',dom='"+dMarriage+"'," +
										  	"status='"+status+"' where id='"+FollowerID+"' and name='"+abc1[0].trim()+"'");
							Toast.makeText(getApplicationContext(), "Values Updated Successfully",Toast.LENGTH_SHORT).show();						
							
							searchb.setEnabled(false);
							updateb.setEnabled(false);
							statusButton.setEnabled(false);
							clearb.setEnabled(false);
							actv.setEnabled(false);
							address.setEnabled(false);
							groupArea.setEnabled(false);
							zone.setEnabled(false);
							officeno.setEnabled(false);
							mobileno.setEnabled(false);
							StatusTextView.setVisibility(View.INVISIBLE);
							//refreshb.setVisibility(View.VISIBLE);
							
						}

					}
					catch(Exception ex)
					{
						Toast.makeText(getApplicationContext(), "Error in Updation"+ex,Toast.LENGTH_LONG).show();
					}

				}
			}
		});
		refreshb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

				Intent i=new Intent(getApplicationContext(),Update.class);
				startActivity(i);
				

			}
		});
		clearb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				address.setText("");groupArea.setText("");zone.setText("");officeno.setText("");
				mobileno.setText("");dob.setText("");dom.setText("");
				statusButton.setText("Status");statusButton.setEnabled(false);
				
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
		getMenuInflater().inflate(R.menu.update, menu);
		return true;
	}

}
