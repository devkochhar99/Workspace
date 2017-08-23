package com.swipemenu;

import com.swipemenu.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
 
public class TopRatedFragment extends Fragment {
	private Button b1;
	private EditText t1,t2;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_toprated, container, false);
        b1 = (Button) rootView.findViewById(R.id.button1);
    	t1 = (EditText) rootView.findViewById(R.id.editText1);
    	t2 = (EditText) rootView.findViewById(R.id.editText2);
    	
    	b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String x;
				if(t1.getText().toString().equals("Dev") && t2.getText().toString().equals("Pass")){
					Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_LONG).show();
					
				}
				else{
					Toast.makeText(getActivity(), "Wrong Username or Password Entered", Toast.LENGTH_LONG).show();
					
				}
			}
		});
        return rootView;
    }
}

