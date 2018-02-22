package com.pachiraframework.watchdog.event;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.AsyncEventBus;
import com.pachiraframework.watchdog.util.NamedThreadFactory;

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
		ExecutorService executorService = new ThreadPoolExecutor(200, 1000, 10L, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000),new NamedThreadFactory("eventbus"));;
		AsyncEventBus asyncEventBus = new AsyncEventBus(executorService);
		return asyncEventBus;
	}
}
