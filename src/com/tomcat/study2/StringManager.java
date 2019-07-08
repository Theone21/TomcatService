package com.tomcat.study2;

import java.util.Hashtable;

public class StringManager {
	
	private static Hashtable<String, StringManager> managers = new Hashtable<>();
	
	private StringManager() {
		
	}
	
	public static StringManager getManager(String packageName) {
		if(managers.get(packageName) != null) {
			return managers.get(packageName);
		}
		return managers.put(packageName, new StringManager());
	}

}
