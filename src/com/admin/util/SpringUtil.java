package com.admin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class SpringUtil {

	ApplicationContext applicationContext;

	static SpringUtil instance;

	public static void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if(instance==null){
			instance=new SpringUtil();
		}
		instance.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return instance.applicationContext;
	}
}