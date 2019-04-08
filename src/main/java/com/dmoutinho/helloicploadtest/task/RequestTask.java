package com.dmoutinho.helloicploadtest.task;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.dmoutinho.helloicploadtest.http.HTTPRequest;
import com.dmoutinho.helloicploadtest.http.HTTPResponse;
import com.dmoutinho.log.Log;

public class RequestTask extends Thread {
	
	private static final int WAIT = 10;
	private String to;
	
	public RequestTask(String to) {
		this.to = to;
	}
	
	public void run(){
 		
		Log.info("RequestTask - run - start");
		Log.info("RequestTask - run - to: "+this.to);
		
		try {
			
			while( true ) {
				
				Map<String,String> params = new HashMap<>();
				
		 		HTTPResponse response = (new HTTPRequest()).to(this.to).get(params);
		 		
	    		Log.info("HelloIcpLoadTestApplication - run - payload: "+response.getPayload());
				
	    		Thread.sleep((new Random().nextInt(WAIT))*1000);
	    		
			}
			
		} catch (Exception e) {
			Log.info("RequestTask - run - error: " + e.getMessage());
		}
		
		Log.info("RequestTask - run - end");

     }
	
}