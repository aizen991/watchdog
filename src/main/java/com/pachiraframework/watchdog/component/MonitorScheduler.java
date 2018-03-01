package com.pachiraframework.watchdog.component;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.google.common.base.Throwables;
import com.pachiraframework.watchdog.dao.SchedulerDao;
import com.pachiraframework.watchdog.util.NamedThreadFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 如果需要外部
 * 
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class MonitorScheduler implements InitializingBean, DisposableBean,ApplicationContextAware {
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
	private Scheduler scheduler;
	@Autowired
	private SchedulerDao schedulerDao;
	private ApplicationContext applicationContext;
	private static ExecutorService threadPool = new ThreadPoolExecutor(10, 30, 10L, TimeUnit.MINUTES,
			new ArrayBlockingQueue<>(100), new NamedThreadFactory("monitor-scheduler"));

	@Override
	public void afterPropertiesSet() throws Exception {
		scheduler.start();
		if (SCHEDULE_MODE_CLUSTER.equals(this.mode)) {
			log.warn("当前运行的是cluster模式，使用外部统一的任务调度中心进行任务调度.");
			return;
		}
		if (SCHEDULE_MODE_LOCAL.equals(this.mode)) {
			schedulerDao.findAll().forEach(job -> {
				JobKey jobKey = new JobKey(job.getId().toString());
				JobDetail jobDetail = newJob(SimpleJob.class).withIdentity(jobKey).build();
				jobDetail.getJobDataMap().put(SimpleJob.JOB_KEY, job);
				jobDetail.getJobDataMap().put(SimpleJob.APPLICATION_CONTEXT,this.applicationContext);
				TriggerKey triggerKey = new TriggerKey(job.getId().toString());
				Trigger trigger = newTrigger().withIdentity(triggerKey)
						.withSchedule(CronScheduleBuilder.cronSchedule(job.getCron())).startNow().build();
				try {
					scheduler.scheduleJob(jobDetail, trigger);
				} catch (SchedulerException e) {
					log.error("{}", Throwables.getStackTraceAsString(e));
				}
			});
		}
	}

	@Override
	public void destroy() throws Exception {
		scheduler.shutdown();
	}

	public static class SimpleJob implements Job {
		public static final String JOB_KEY = "job";
		public static final String APPLICATION_CONTEXT = "applicationContext";

		@Override
		public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
			com.pachiraframework.watchdog.entity.Scheduler job = (com.pachiraframework.watchdog.entity.Scheduler)jobExecutionContext.getMergedJobDataMap().get(JOB_KEY);
			ApplicationContext applicationContext = (ApplicationContext)jobExecutionContext.getMergedJobDataMap().get(APPLICATION_CONTEXT);
			threadPool.submit(() -> applicationContext.getBean(PingChecker.class).check(job.getId()));
			threadPool.submit(() -> applicationContext.getBean(TelnetChecker.class).check(job.getId()));
			threadPool.submit(() -> applicationContext.getBean(HttpChecker.class).check(job.getId()));
			threadPool.submit(() -> applicationContext.getBean(MemcachedChecker.class).check(job.getId()));
			threadPool.submit(() -> applicationContext.getBean(RedisChecker.class).check(job.getId()));
			threadPool.submit(() -> applicationContext.getBean(MysqlChecker.class).check(job.getId()));
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
