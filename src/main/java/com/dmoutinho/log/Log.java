package com.dmoutinho.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	private static String time() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}
		
	public static void debug(String log) {
		System.out.println( time() + " " + log );
	}
	
	public static void info(String log) {
		System.out.println( time() + " " + log );
	}

	public static void warn(String log) {
		System.out.println( time() + " " + log );
	}
	
	public static void error(String log) {
		System.out.println( time() + " " + log );
	}
	
}