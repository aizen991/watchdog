package com.pachiraframework.watchdog.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;

/**
 * @author wangxuzheng
 *
 */
public class WatchdogEventBus {
	private static AsyncEventBus asyncEventBus = initAsyncEventBus();
	private WatchdogEventBus(){
	}
	public static AsyncEventBus asyncEventBus(){
		return asyncEventBus;
	}
	
	private static AsyncEventBus initAsyncEventBus(){
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		AsyncEventBus asyncEventBus = new AsyncEventBus(executorService);
		return asyncEventBus;
	}
}
