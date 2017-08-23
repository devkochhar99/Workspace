package com.fragment;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View; 
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

@SuppressLint("NewApi")
public class F1 extends Fragment
{ 
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
	return inflater.inflate( R.layout.f1, container, false); 
	
	}
}

/*
public class MainActivity extends Activity {
	private CheckBox c1,c2,c3;
	private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c1 = (CheckBox)findViewById(R.id.checkBox1);
        c2 = (CheckBox)findViewById(R.id.checkBox2);
        c3 = (CheckBox)findViewById(R.id.checkBox3);
        b1 = (Button)findViewById(R.id.button1);
        b1.setOnClickListener(new OnClickListener(){
        		
        	public void onClick(View v){
        		  int totalamount=0;  
                  StringBuilder result=new StringBuilder();  
                  result.append("Selected Items:");  
                  if(c1.isChecked()){  
                      result.append("\nPizza 100Rs");  
                      totalamount+=100;  
                  }  
                  if(c2.isChecked()){  
                      result.append("\nBurger 50Rs");  
                      totalamount+=50;  
                  }  
                  if(c3.isChecked()){  
                      result.append("\nCoffee 120Rs");  
                      totalamount+=120;  
                  }  
                  result.append("\nTotal: "+totalamount+"Rs");  
                  
                  Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
        		
        	}
        	
        });

*/