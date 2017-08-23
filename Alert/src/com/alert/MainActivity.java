package com.alert;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.AlertDialog;  
import android.content.DialogInterface;

public class MainActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  
        //Uncomment the below code to Set the message and title from the strings.xml file  
        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);  
          
        //Setting message manually and performing action on button click  
        builder.setMessage("Do you want to close this application ?")  
            .setCancelable(false)  
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {  
                public void onClick(DialogInterface dialog, int id) {  
                finish();  
                }  
            })  
            .setNegativeButton("No", new DialogInterface.OnClickListener() {  
                public void onClick(DialogInterface dialog, int id) {  
                //  Action for 'NO' Button  
                dialog.cancel();  
             }  
            });  
  
        //Creating dialog box  
        AlertDialog alert = builder.create();  
        //Setting the title manually  
        //alert.setTitle("AlertDialogExample");  
        alert.show();  
        setContentView(R.layout.activity_main);  
    }  
  

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
