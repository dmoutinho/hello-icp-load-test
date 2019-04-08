package com.dmoutinho.helloicploadtest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmoutinho.helloicploadtest.task.RequestTask;
import com.dmoutinho.log.Log;

@SpringBootApplication
@EnableAutoConfiguration
public class HelloIcpLoadTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloIcpLoadTestApplication.class, args);
	}
}

@RestController
@RequestMapping("/")
class HelloICPController {

	@Value("${request.task.url}")
	private String requestTaskUrl;
	
	private List<RequestTask> requests;
	
	@GetMapping("/request-task/start")
	ResponseEntity<?> start() {
		
		ResponseEntity<Message> responseEntity = null;
    	
		try {
						
    		Log.info("HelloIcpLoadTestApplication - start - init");
    		
    		if( this.requests==null ) {
    			this.requests = new ArrayList<>();
    		}
    		
    		RequestTask request = new RequestTask(this.requestTaskUrl);
    		request.start();
    		;
    		
    		requests.add(request);
    		
    		responseEntity = new ResponseEntity<>(new Message("RequestTask started! Total: "+requests.size()),
    															HttpStatus.OK);
    		
    		Log.info("HelloIcpLoadTestApplication - start - end");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return responseEntity;
    
	}
	
	@GetMapping("/request-task/stop")
	ResponseEntity<?> stop() {
		
		ResponseEntity<Message> responseEntity = null;
    	
		try {
			
    		Log.info("HelloIcpLoadTestApplication - stop - init");
    		
    		requests.stream().forEach( item -> item.interrupt() );
    		
    		responseEntity = new ResponseEntity<>(new Message("RequestTask stoped! Count: "+this.requests.size()),HttpStatus.OK);
    		
    		requests = new ArrayList<>();
    		
    		Log.info("HelloIcpLoadTestApplication - stop - end");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return responseEntity;
    
	}
	
	@GetMapping("")
	ResponseEntity<String> root() {
		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<String>("OK",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}	
		
}

class Message {
	private String text;
	Message(String text) {
		this.text = text;
	}
	public String getText() {
		return this.text;
	}
}