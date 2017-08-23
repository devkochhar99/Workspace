package com.fragment;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity; 
import android.app.FragmentManager; 
import android.app.FragmentTransaction; 
import android.content.res.Configuration; 


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class MainActivity extends Activity {

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
@Override 
	
protected void onCreate(Bundle savedInstanceState){
	
	super.onCreate(savedInstanceState); 
Configuration config = getResources().getConfiguration(); 
FragmentManager fragmentManager = getFragmentManager(); 
FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); 
	F2 f2 = new F2(); 
	fragmentTransaction.replace(android.R.id.content, f2); 
	F1 f1 = new F1(); 
	fragmentTransaction.replace(android.R.id.content, f1); 
	fragmentTransaction.commit();
	}
}