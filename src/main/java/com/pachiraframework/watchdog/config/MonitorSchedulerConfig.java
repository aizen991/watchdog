package com.pachiraframework.watchdog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 如果需要外部
 * 
 * @author wangxuzheng
 *
 */
@Configuration
public class MonitorSchedulerConfig {
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler poolTaskScheduler = new ThreadPoolTaskScheduler();
		poolTaskScheduler.setPoolSize(20);
		return poolTaskScheduler;
	}
}
