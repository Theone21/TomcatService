package com.tomcat.study1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
	
	private static final int BUFFER_SIZE = 1024;
	Request request;
	OutputStream output;
	
	public Response(OutputStream output) {
		this.output = output;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	
	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try {
			if(request.getUri() == null) {
				System.out.println("yes");
				response404(output);
				return;
			}
			File file = new File(HttpServer.WEB_ROOT, request.getUri());
			if(file.exists()) {
				StringBuilder heads = new StringBuilder("HTTP/1.1 200 OK\r\n");
				heads.append("Content-Type: text/html\r\n");
				
				fis = new FileInputStream(file);
				int ch = fis.read(bytes, 0, BUFFER_SIZE);
				
				heads.append(String.format("Content-Length: %d\n", ch));
				heads.append("\r\n");
				output.write(heads.toString().getBytes());
				
				while(ch != -1) {
					output.write(bytes, 0, ch);
					ch = fis.read(bytes);
				}
			} else {
				response404(output);
			}
		} catch (FileNotFoundException e) {
			response404(output);
		} finally {
			if(fis != null) {
				fis.close();
			}
			
		}
		
	}
	
	private void response404(OutputStream output) throws IOException {
		StringBuilder response=new StringBuilder();
        response.append("HTTP/1.1 404 File Not Found\r\n");
        response.append("Content-Type: text/html\r\n");
        response.append("Content-Length: 23\r\n");
        response.append("\r\n");
        response.append("<h1>File Not Found</h1>");
        output.write(response.toString().getBytes());
	}
	
	

}
