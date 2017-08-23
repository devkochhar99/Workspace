package com.colorchange;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.*;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RatingBar;
import android.widget.RelativeLayout;


public class MainActivity extends Activity implements OnSeekBarChangeListener,OnRatingBarChangeListener {
	private SeekBar s1,s2,s3;
	private RatingBar r1;
	private TextView t1,t2,t3;
	private RelativeLayout l1;
	int a,b,c;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        s1 = (SeekBar)findViewById(R.id.seekBar1);
        s2 = (SeekBar)findViewById(R.id.seekBar2);
        s3 = (SeekBar)findViewById(R.id.seekBar3);
        
        l1 = (RelativeLayout)findViewById(R.id.myScreen);
        
        updateBackground();
        
        r1 = (RatingBar)findViewById(R.id.ratingBar1);
        
        t1 = (TextView)findViewById(R.id.textView4);
        t2 = (TextView)findViewById(R.id.textView5);
        t3 = (TextView)findViewById(R.id.textView6);
        
        s1.setOnSeekBarChangeListener(this);
        s2.setOnSeekBarChangeListener(this);
        s3.setOnSeekBarChangeListener(this);
        
        r1.setOnRatingBarChangeListener(this);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		 switch (arg0.getId()) 
		    {
		    case R.id.seekBar1:
		      a = arg1;
		    	t1.setText(String.valueOf(arg1));
		        updateBackground();
		        break;
		    case R.id.seekBar2:
		    	b = arg1;
		        t2.setText(String.valueOf(arg1));
		        updateBackground();
		        break;
		    case R.id.seekBar3:
		    	c = arg1;
		        t3.setText(String.valueOf(arg1));
		        updateBackground();
		        break;
		    }
		}
	
	public void updateBackground(){
		l1.setBackgroundColor(
				  0xff000000
				  + a * 0x10000
				  + b * 0x100
				  + c 
				  );
		
	}


	


	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
		float rating=r1.getRating(); 
		Toast.makeText(getApplicationContext(),String.valueOf(rating),Toast.LENGTH_LONG).show();
	}

}

	