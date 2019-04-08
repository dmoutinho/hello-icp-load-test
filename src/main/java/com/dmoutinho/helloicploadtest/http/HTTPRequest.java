package com.dmoutinho.helloicploadtest.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.dmoutinho.log.Log;

public class HTTPRequest {

	private final static String METHOD_GET = "GET";
	
	private String to;
	private HttpURLConnection connection;
	
	public HTTPRequest to(String to) {
		this.to = to;
		return this;
	}
	
	private void initConnection() throws Exception {
		
		Log.info("HTTPRequest - initConnection - init");
		
		this.connection = (HttpURLConnection) (new URL(this.to)).openConnection();							
		
		Log.info("HTTPRequest - initConnection - end");
	
	}
	
	private HTTPResponse invoke(String method) throws Exception {
		
		Log.info("HTTPRequest - invoke - init");
	    
		String response  = new BufferedReader(new InputStreamReader(this.connection.getInputStream())).readLine();			
	    HTTPResponse httpResponse = new HTTPResponse(HTTPResponse.HTTP_SUCCCES,"",response);
	    
	    Log.info("HTTPRequest - invoke - httpResponse: "+httpResponse);
		
	    return httpResponse;
	    
	}
	

	public HTTPResponse get(Map<String,String> params) throws Exception  {

		Log.info("HTTPRequest - get - init");

		StringBuilder strB = new StringBuilder();
		strB.append(this.to);
		strB.append("?");
		for(String key : params.keySet()) {
			strB.append(key + "=" + params.get(key) + "&");
		}
		
		this.to = strB.toString(); 

		this.initConnection();

		Log.info("HTTPRequest - get - end");
		
		return this.invoke(METHOD_GET);

	}	
	
}