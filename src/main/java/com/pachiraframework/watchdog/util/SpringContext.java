package com.pachiraframework.watchdog.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wangxuzheng
 *
 */
@Component
public class SpringContext implements ApplicationContextAware {
	private static ApplicationContext APPLICATION_CONTEXT;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		APPLICATION_CONTEXT = applicationContext;
	}

	public static <T> T get(Class<T> clazz){
		return APPLICATION_CONTEXT.getBean(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(String beanId){
		return (T)APPLICATION_CONTEXT.getBean(beanId);
	}
}
