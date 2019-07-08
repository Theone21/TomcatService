package com.tomcat.study2.startup;

import com.tomcat.study2.connector.HttpConnector;

public class Bootstrap {
	
	public static void main(String[] args) {
		HttpConnector hc = new HttpConnector();
		hc.start();
	}
}
