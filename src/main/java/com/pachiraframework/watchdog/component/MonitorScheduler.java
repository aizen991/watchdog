package com.pachiraframework.watchdog.component;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;

import com.pachiraframework.watchdog.constant.ScheduleInterval;

import lombok.extern.slf4j.Slf4j;

/**
 * 如果需要外部
 * 
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class MonitorScheduler implements InitializingBean, DisposableBean {
	private static final String SCHEDULE_MODE = "default";
	@Value("${schedule.mode?:default}")
	private String mode;
	@Autowired
	private PingChecker pingChecker;

	@Override
	public void destroy() throws Exception {
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Runnint mode [{}]", this.mode);
		if (SCHEDULE_MODE.equals(this.mode)) {
			for (ScheduleInterval interval : ScheduleInterval.values()) {
				Integer delay = interval.getMinutes() * 60 * 1000;
				new ConcurrentTaskScheduler()
						.scheduleWithFixedDelay(() -> pingChecker.check(delay), delay);
			}
		}
	}
}
