package com.tomcat.Main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	
	public static final File WEB_ROOT = new File("D:\\webRoot");
	
	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer();
		httpServer.await();
	}
	
	public void await() {
		try(ServerSocket serverSocket = new ServerSocket(8888)){
			
			serverProcess(serverSocket);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void serverProcess(ServerSocket serverSocket) {
		while(true) {
			try(Socket socket = serverSocket.accept()){
				System.out.println(socket.hashCode());
				InputStream input =  socket.getInputStream();
				OutputStream output = socket.getOutputStream();
				Request request = new Request(input);
				request.parse();
				Response response = new Response(output);
				response.setRequest(request);
				response.sendStaticResource();
			} catch(IOException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
