package com.pachiraframework.watchdog.component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
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
public class MonitorScheduler {
	/**
	 * 本地单机运行模式
	 */
	private static final String SCHEDULE_MODE_LOCAL = "local";
	/**
	 * 集中化任务调度中心调度模式
	 */
	private static final String SCHEDULE_MODE_CLUSTER = "cluster";
	@Value("${schedule.mode?:local}")
	private String mode;
	@Autowired
	private PingChecker pingChecker;
	@Autowired
	private TelnetChecker telnetChecker;
	@Autowired
	private HttpChecker httpChecker;
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);

	/**
	 * 一分钟执行一次
	 */
	@Scheduled(fixedRate = (1 * 60 * 1000))
	public void everyMinutes() {
		Integer interval = ScheduleInterval.MIN_1.milliseconds();
		doCheck(interval);
	}

	/**
	 * 5分钟执行一次
	 */
	@Scheduled(fixedRate = (5 * 60 * 1000))
	public void every5Minutes() {
		log.info("monitor.scheduler.min_5:开始执行");
		Integer interval = ScheduleInterval.MIN_5.milliseconds();
		doCheck(interval);
	}

	/**
	 * 10分钟执行一次
	 */
	@Scheduled(fixedRate = (10 * 60 * 1000))
	public void every10Minutes() {
		log.info("monitor.scheduler.min_10:开始执行");
		Integer interval = ScheduleInterval.MIN_10.milliseconds();
		doCheck(interval);
	}

	/**
	 * 20分钟执行一次
	 */
	@Scheduled(fixedRate = (20 * 60 * 1000))
	public void every20Minutes() {
		log.info("monitor.scheduler.min_20:开始执行");
		Integer interval = ScheduleInterval.MIN_20.milliseconds();
		doCheck(interval);
	}

	/**
	 * 30分钟执行一次
	 */
	@Scheduled(fixedRate = (30 * 60 * 1000))
	public void every30Minutes() {
		log.info("monitor.scheduler.min_30:开始执行");
		Integer interval = ScheduleInterval.MIN_30.milliseconds();
		doCheck(interval);
	}

	/**
	 * 40分钟执行一次
	 */
	@Scheduled(fixedRate = (40 * 60 * 1000))
	public void every40Minutes() {
		log.info("monitor.scheduler.min_40:开始执行");
		Integer interval = ScheduleInterval.MIN_40.milliseconds();
		doCheck(interval);
	}

	/**
	 * 50分钟执行一次
	 */
	@Scheduled(fixedRate = (50 * 60 * 1000))
	public void every50Minutes() {
		log.info("monitor.scheduler.min_50:开始执行");
		Integer interval = ScheduleInterval.MIN_50.milliseconds();
		doCheck(interval);
	}

	/**
	 * 60分钟执行一次
	 */
	@Scheduled(fixedRate = (60 * 60 * 1000))
	public void every60Minutes() {
		log.info("monitor.scheduler.min_40:开始执行");
		Integer interval = ScheduleInterval.MIN_40.milliseconds();
		doCheck(interval);
	}

	/**
	 * 90分钟执行一次
	 */
	@Scheduled(fixedRate = (90 * 60 * 1000))
	public void every90Minutes() {
		log.info("monitor.scheduler.min_90:开始执行");
		Integer interval = ScheduleInterval.MIN_90.milliseconds();
		doCheck(interval);
	}

	private void doCheck(Integer interval) {
		if (SCHEDULE_MODE_CLUSTER.equals(this.mode)) {
			log.warn("当前运行的是cluster模式，使用外部统一的任务调度中心进行任务调度.");
			return;
		}
		if (SCHEDULE_MODE_LOCAL.equals(this.mode)) {
			log.info("monitor.scheduler.{}:开始执行", interval);
			
			threadPool.submit(() -> pingChecker.check(interval));
			threadPool.submit(() -> telnetChecker.check(interval));
			threadPool.submit(() -> httpChecker.check(interval));
		}
	}
}
