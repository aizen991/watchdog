package com.pachiraframework.watchdog.event.subscriber;

import org.springframework.beans.factory.InitializingBean;

import com.pachiraframework.watchdog.event.WatchdogEventBus;

/**
 * @author wangxuzheng
 *
 */
public abstract class AbstractSubscriber implements InitializingBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		WatchdogEventBus.asyncEventBus().register(this);
	}

}
