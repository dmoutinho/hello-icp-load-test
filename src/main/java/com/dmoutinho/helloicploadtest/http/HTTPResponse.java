package com.dmoutinho.helloicploadtest.http;

public class HTTPResponse {

	public static final int HTTP_SUCCCES = 200;
	public static final int HTTP_ERROR = 500;
	
	private int code;
	private String error;
	private String payload;
	
	public HTTPResponse(int code, String error, String payload) {
		super();
		this.code = code;
		this.payload = payload;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
}