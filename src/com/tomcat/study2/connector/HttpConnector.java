package com.tomcat.study2.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable {
	
	boolean stopped;
	private String scheme = "http";
	
	

	public String getScheme() {
		return scheme;
	}

	@Override
	public void run() {
		ServerSocket ss = null;
		int port = 8899;
		try {
			ss = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (IOException e) {
			System.exit(1);
		}
		while(!stopped) {
			Socket s = null;
			try {
				s = ss.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			HttpProcessor hp = new HttpProcessor(this);
			hp.process(s);
		}
	}
	
	public void start() {
		Thread t = new Thread(this);
		t.start();
	}

}
