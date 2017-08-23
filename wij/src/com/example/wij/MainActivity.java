package com.example.wij;


import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	SQLiteDatabase sqldb;
	private Button b1;
	EditText t1,t2,t3,t4,t5,t6,t7,t8;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	t1=(EditText)findViewById(R.id.editText1);
	t2=(EditText)findViewById(R.id.editText2);
	t3=(EditText)findViewById(R.id.editText3);
	t4=(EditText)findViewById(R.id.editText4);
	t5=(EditText)findViewById(R.id.editText5);
	t6=(EditText)findViewById(R.id.editText6);
	t7=(EditText)findViewById(R.id.editText7);
	t8=(EditText)findViewById(R.id.editText8);

	b1 = (Button)findViewById(R.id.button1);
	
	b1.setOnClickListener(new OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
				
				try
				{
					
					//int a1=Integer.parseInt(a);
					
					String followerName=t1.getText().toString();
					String address1=t2.getText().toString();
					String groupArea1=t3.getText().toString();
					String zone1=t4.getText().toString();
					String officeno1=t5.getText().toString();
					String mobileno1=t6.getText().toString();
					String dBirth=t7.getText().toString();
					String dMarriage=t8.getText().toString();
					
					
					
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
	});
	
}



}