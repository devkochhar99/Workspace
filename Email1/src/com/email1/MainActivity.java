package com.email1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    /** Called when the activity is first created. */
	EditText t1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        t1 = (EditText) this.findViewById(R.id.editText1);

        final Button send = (Button) this.findViewById(R.id.button1);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {   
                    GMailSender sender = new GMailSender("devkochhar.100@gmail.com", "rjaDr@1234");
                    sender.sendMail("Verification Code",   
                            "Verification Code is - 123",   
                            t1.getText().toString(),   
                            "devkochhar.100@gmail.com"); 
                    Toast.makeText(getApplicationContext(),"Msg Sent", Toast.LENGTH_LONG).show();
                } catch (Exception e) {   
                    Log.e("SendMail", e.getMessage(), e);   
                } 

            }
        });

    }
}