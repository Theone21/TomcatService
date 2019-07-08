package com.tomcat.study2.connector;

import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessor {

	public HttpProcessor(HttpConnector httpConnector) {
		
	}

	public void process(Socket s) {
		SocketInputStream input = null;
		OutputStream output = null;
		try {
			input = new SocketInputStream(s.getInputStream(), 2048);
			output = s.getOutputStream();
			request = new HttpRequest(input);
			response = new HttpResponse(output);
			response.setRequest(request);
			response.setHeader("Server", "Pyrmont Servlet Container");
			parseRequest(input, output);
			parseHeader(input);
			if(request.getRequestURI().startsWith("/servlet/")) {
				ServeltProcessor processor = new ServletProcessor();
				processor.process(request, response);
			} else {
				StaticResourceProcessor processor = new StaticResourceProcessor();
				processor.process(request, response);
			}
			s.close();
		} catch (Exception e) {
			
		}
	}
	
	private void parseRequest(SocketInputStream input, OutputStream output) {
		input.readRequestLine(requestLine);
		
		String method = new String(requestLine.method, 0, requestLine.methodEnd);
		String uri = null;
		String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);
		if(method.length() < 1) {
			throw new ServletException("Missing HTTP request method");
		} else if(requestLine.uriEnd < 1) {
			throw new ServletException("Missing HTTP request URI");
		}
		int question = requestLine.indexOf("?");
//		if(question >= 0) {
//			
//		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
