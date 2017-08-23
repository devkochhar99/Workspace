package com.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity {

	private EditText t1;
	private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18;
	private double a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.button1);
        b1.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	t1.setText(t1.getText() + "1");
        	}
        	
        });
        b2 = (Button)findViewById(R.id.button2);
        b2.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	t1.setText(t1.getText() + "2");
        	}
        	
        });
        b3 = (Button)findViewById(R.id.button3);
        b3.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	t1.setText(t1.getText() + "3");
        	}
        	
        });
        b4 = (Button)findViewById(R.id.button4);
        b4.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	t1.setText(t1.getText() + "4");
        	}
        	
        });
        b5 = (Button)findViewById(R.id.button5);
        b5.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	t1.setText(t1.getText() + "5");
        	}
        	
        });
        b6 = (Button)findViewById(R.id.button6);
        b6.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	t1.setText(t1.getText() + "6");
        	}
        	
        });
        b7 = (Button)findViewById(R.id.button7);
        b7.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	t1.setText(t1.getText() + "7");
        	}
        	
        });
        b8 = (Button)findViewById(R.id.button8);
        b8.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	t1.setText(t1.getText() + "8");
        	}
        	
        });
        
        b9 = (Button)findViewById(R.id.button9);
        b9.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	t1.setText(t1.getText() + "9");
        	}
        	
        });
        
    	b10 = (Button)findViewById(R.id.button10);
        b10.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        		a = Double.parseDouble(t1.getText().toString());
                
        	t1.setText("");
        	}
        	
        });
        
        b11 = (Button)findViewById(R.id.button11);
        b11.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        		a = Double.parseDouble(t1.getText().toString());
            
        	t1.setText("");
        	}
        	
        }); 
        b12 = (Button)findViewById(R.id.button12);
        b12.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        		a = Double.parseDouble(t1.getText().toString());
                
          	t1.setText("");
        	}
        	
        });   
        b13 = (Button)findViewById(R.id.button13);
        b13.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        		a = Double.parseDouble(t1.getText().toString());
                
        	
        	t1.setText("");
        	}
        	
        });
        b14 = (Button)findViewById(R.id.button14);
        b14.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	double b,c = 0;
        	b = Double.parseDouble(t1.getText().toString());
        	if(v.getId() == R.id.button10)
        {
        	c = a + b;
        }
        
        else if(v.getId() == R.id.button11)
        		c = a - b;
        	else	if(v.getId() == R.id.button12)
        		c = a * b;
         else if(v.getId() == R.id.button13)
        		c = a/b;
        	t1.setText("" + c);
        	}
        	
        });
       
        b15 = (Button)findViewById(R.id.button15);
        b15.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	t1.setText(t1.getText() + "0");
        	}
        	
        });
        b18 = (Button)findViewById(R.id.button18);
        b18.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        		//if(t1.getText() != ".")
        	t1.setText(t1.getText() + ".");
        		//else
        			//msgbox...............
        	}
        	
        });
        b16 = (Button)findViewById(R.id.button16);
        b16.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	t1.setText("");
        	}
        	
        });
        b17 = (Button)findViewById(R.id.button17);
        b17.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        	System.exit(0);
        	}
        	
        });
        
        t1 = (EditText)findViewById(R.id.editText1);

        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
