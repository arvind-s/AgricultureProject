package com.example.arjunaof;

import android.telephony.SmsManager;

public class SMSSender 
{
	
	SmsManager sms = SmsManager.getDefault();
	String num;
	//String text="Test message to arjun";
		
	public SMSSender(String num)
	{
		this.num=num;
	}

	public void send(String job,String name)
	{
		sms.sendTextMessage(num, null, "I "+name+" want to apply for the job:"+job, null, null);
	}
}
