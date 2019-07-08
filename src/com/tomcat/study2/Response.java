package com.tomcat.study2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class Response implements ServletResponse {
	
	private static final int BUFFER_SIZE = 1024;
	Request request;
	OutputStream output;
	PrintWriter writer;
	
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

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		writer = new PrintWriter(output, true);
		return writer;
	}

	@Override
	public void setCharacterEncoding(String charset) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentLength(int len) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentLengthLong(long length) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentType(String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBufferSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocale(Locale loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
