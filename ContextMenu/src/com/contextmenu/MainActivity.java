package com.contextmenu;

import android.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputFilter.LengthFilter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
		ListView l1;
		String contacts[] = {"Arun","Vargun","Arjun","Bheem","Yugo"};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		l1 = (ListView)findViewById(R.id.listView1);
		ArrayAdapter<String> a = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
		l1.setAdapter(a);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onCreateContextView(ContextMenu menu ,View v,ContextMenuInfo menuInfo){
		
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Action");
		menu.add(0,v.getId(),0,"Call");
		menu.add(0,v.getId(),0,"SMS");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem i)
	{
		
		if(i.getTitle() == "Call"){
			Toast.makeText(getApplicationContext(),"calling code",Toast.LENGTH_LONG).show();
		}
		else if(i.getTitle() == "SMS"){
			
			Toast.makeText(getApplicationContext(),"sending sms code",Toast.LENGTH_LONG).show();
		}
		else{
			return false;
			
		}
		return true;
	}
}
