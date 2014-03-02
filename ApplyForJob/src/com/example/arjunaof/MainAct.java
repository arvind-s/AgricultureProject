package com.example.arjunaof;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;


import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainAct extends Activity
{

	String jobChoice,name,ph;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try 
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DownloadFile downloadFile = new DownloadFile();
		downloadFile.execute("http://192.168.0.123/uploads/fowner.txt");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void job1(View view) throws IOException
	{
		jobChoice="job1";
		TextView text=(TextView) findViewById(R.id.textView2);
		double sal=this.fileProcessor();
		text.setVisibility(1);
		text.setText("Employer:"+name+"\nSalary:Rs."+sal);
		
	}
	public void job2(View view) throws IOException
	{
		jobChoice="job2";
		TextView text=(TextView) findViewById(R.id.textView2);
		double sal=this.fileProcessor();
		text.setVisibility(1);
		text.setText("Employer:"+name+"\nSalary:Rs."+sal);
	}
	public void job3(View view) throws IOException
	{
		jobChoice="job3";
		TextView text=(TextView) findViewById(R.id.textView2);
		double sal=this.fileProcessor();
		text.setVisibility(1);
		text.setText("Employer:"+name+"\nSalary:Rs."+sal);
	}
	
	public void submit(View view)
	{
		EditText text=(EditText) findViewById(R.id.editText1);
		
		SMSSender send=new SMSSender(ph);
		send.send(jobChoice,text.getText().toString());
	}
	public void download(View view)
	{
		DownloadFile downloadFile = new DownloadFile();
		downloadFile.execute("http://192.168.0.123/uploads/fowner.txt");
	}
	private double fileProcessor() throws IOException
	{
		File file = new File("/sdcard/fowner.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line=null;double sal=0;
        while((line=br.readLine())!=null)
        {
        	String tokens[]=line.split("\\W+");
        	if(jobChoice.equalsIgnoreCase("job1"))
        	{
        		if(Integer.parseInt(tokens[3])==0)
        		{
        			
        		}
        		else
        		{
        			if(Integer.parseInt(tokens[4])>sal)
        			{
        				sal=Integer.parseInt(tokens[4]);
        				name=tokens[0]+"hello";
        				ph=tokens[1];
        			}
        		}
        	}
        	if(jobChoice.equalsIgnoreCase("job2"))
        	{
        		if(Integer.parseInt(tokens[6])==0)
        		{
        			
        		}
        		else
        		{
        			if(Integer.parseInt(tokens[7])>sal)
        			{
        				sal=Integer.parseInt(tokens[7]);
        				name=tokens[0]+"hello";
        				ph=tokens[1];
        			}
        		}
        	}
        	if(jobChoice.equalsIgnoreCase("job3"))
        	{
        		if(Integer.parseInt(tokens[9])==0)
        		{
        			
        		}
        		else
        		{
        			if(Integer.parseInt(tokens[10])>sal)
        			{
        				sal=Integer.parseInt(tokens[10]);
        				name=tokens[0]+"hello";
        				ph=tokens[1];
        			}
        		}
        	}
        }
        br.close();
        return sal;
	}
	
	
	
	
	
	private class DownloadFile extends AsyncTask<String, Integer, String>
    {
        @Override
        protected String doInBackground(String... sUrl)
        {
            try 
            {
                URL url = new URL(sUrl[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output=null;
                //File file=new File("/mnt/sdcard/fowner.txt");
               
               
                	output = new FileOutputStream("/mnt/sdcard/fowner.txt");
                	
                byte data[] = new byte[1024];                
                int count;
                while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } 
            catch (Exception e) 
            {
            }
            return null;
        }
        protected void onPostExecute(String result)
        {
        	int duration = Toast.LENGTH_LONG;
        	System.out.println("downloaded");
			Toast toast = Toast.makeText(getApplicationContext(), "File Downloaded" , duration);
			toast.show();
        }
    }
}
