package com.example.employer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Intent in=getIntent();
		//Bundle b=in.getExtras();
		//String message=b.getString("Message");
		//String snum=b.getString("Sender");
		//int i=b.getInt("job");
		
		TextView t3=(TextView)findViewById(R.id.textView3);
		t3.setText("Click on confirm to send confirmation message");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void confirm(View v)
	{
		SmsManager sms = SmsManager.getDefault();
		EditText et=(EditText)findViewById(R.id.editText1);
		sms.sendTextMessage(et.getText().toString(), null, "I confirm your application", null, null);
	}

}
