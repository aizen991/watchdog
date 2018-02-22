package com.pachiraframework.watchdog.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangxuzheng
 *
 */
public class NamedThreadFactory implements ThreadFactory{
	private AtomicInteger poolNumber = new AtomicInteger(1);
	private ThreadGroup group;
	private AtomicInteger threadNumber = new AtomicInteger(1);
	private String namePrefix;

	public NamedThreadFactory(String namePrefix) {
		SecurityManager securityManager = System.getSecurityManager();
		this.group = securityManager != null ? securityManager.getThreadGroup()
				: Thread.currentThread().getThreadGroup();
		this.namePrefix = namePrefix + "-pool-" + poolNumber.getAndIncrement() + "-thread-";
	}

	@Override
	public Thread newThread(Runnable runnable) {
		Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
		if (thread.isDaemon()) {
			thread.setDaemon(false);
		}

		if (thread.getPriority() != 5) {
			thread.setPriority(5);
		}

		return thread;
	}
}
