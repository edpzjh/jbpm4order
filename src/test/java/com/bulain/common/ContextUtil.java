package com.bulain.common;

import org.springframework.context.ApplicationContext;

public final class ContextUtil {
	private static ApplicationContext _applicationContext;
	
	private ContextUtil(){}
	
	public static ApplicationContext getApplicationContext(){
		return _applicationContext;
	}
	
	public static void setApplicationContext(ApplicationContext applicationContext){
		_applicationContext = applicationContext;
	}
}
