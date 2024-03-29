package com.tomcat.study1;

import java.io.IOException;
import java.io.InputStream;

public class Request {

	private InputStream input;
	
	private String uri;
	
	public Request(InputStream input) {
		this.input = input;
	}
	
	public void parse() {
		StringBuffer request = new StringBuffer(2048);
		int i;
		byte[] buffer = new byte[2048];
		try {
			i = input.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			i = -1;
		}
		
		for(int j = 0; j < i; j++) {
			request.append((char) buffer[j]);
		}
		
		System.out.println(request.toString());
		uri = parseUri(request.toString());
		
		
	}
	
	public String parseUri(String requestStr) {
		int index1;
		int index2;
		index1 = requestStr.indexOf(' ');
		if(index1 != -1) {
			index2 = requestStr.indexOf(' ', index1+1);
			if(index2 > index1) {
				return requestStr.substring(index1+1, index2);
			}
		}
		return null;
	}

	public String getUri() {
		return uri;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
