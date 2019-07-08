package com.tomcat.study2;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;


public class ServletProcess1 {

	public void process(Request request, Response response){
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		URLClassLoader loader = null;
		try {
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new java.io.File(Constants.WEB_ROOT);
			
			String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Class<?> myClass = null;
		try {
			myClass = loader.loadClass(servletName);
		} catch (Exception e) {
			System.out.println("加载类失败");
		}
		Servlet servlet = null;
		try {
			servlet = (Servlet) myClass.newInstance();
			servlet.service(request, response);
		} catch (Exception e) {
			System.out.println("调用类方法失败");
		}
	}
}
