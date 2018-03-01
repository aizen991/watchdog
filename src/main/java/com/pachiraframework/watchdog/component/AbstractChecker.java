package com.pachiraframework.watchdog.component;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.assertj.core.util.Strings;

import com.google.common.base.Throwables;
import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.PageRequest;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.event.WatchdogEventBus;
import com.pachiraframework.watchdog.event.event.MetricReportEvent;
import com.pachiraframework.watchdog.util.NamedThreadFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 监控器检查
 * @author wangxuzheng
 *
 */
@Slf4j
public abstract class AbstractChecker {
	private ExecutorService executorService = new ThreadPoolExecutor(10, 30, 1L, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1000),new NamedThreadFactory(getClass().getSimpleName()));
	/**
	 * 批量抓去数据的
	 */
	protected static final Integer BATCH_FETCH_SIZE = 20;
	/**
	 * 执行检查
	 * @param schedulerId 调度器id
	 */
	public void check(Long schedulerId) {
		int startPage = 0;
		WrappedPageRequest pageRequest = new WrappedPageRequest(new PageRequest(startPage, BATCH_FETCH_SIZE));
		pageRequest.addParam("schedulerId", schedulerId);
		Page<Monitor> page = loadBatch(pageRequest);
		while (!page.getContent().isEmpty()) {
			log.info("开始处理Monitor批次,startPage={},batchSize={},size={},interval={}", startPage, BATCH_FETCH_SIZE,
					page.getContent().size(),schedulerId);
			for (Monitor monitor : page.getContent()) {
				executorService.submit(()->{
					log.info("检查monitor-id:{},name:{}",monitor.getId(),monitor.getName());
					try {
						AbstractRecord record = doMonitor(monitor);
						List<MetricReport> reports = doInspectRecord(monitor,record);
						MetricReportEvent event = MetricReportEvent.builder().reports(reports).monitor(monitor).record(record).build();
						WatchdogEventBus.asyncEventBus().post(event);
					}catch(Exception e) {
						log.error(Throwables.getStackTraceAsString(e));
					}
				});
			}
			pageRequest = new WrappedPageRequest(new PageRequest(++startPage, BATCH_FETCH_SIZE));
			page = loadBatch(pageRequest);
		}
	}
	
	/**
	 * 执行具体的监控器监控，并且把数据持久化到对应的中间件上（如elasticsearch,mysql,mongodb）
	 * @param monitor
	 * @return
	 */
	protected abstract AbstractRecord doMonitor(Monitor monitor);
	
	/**
	 * 检查监控器输出的原始数据，输出指标检查报告，并且把数据持久化到对应的中间件上（如elasticsearch,mysql,mongodb）
	 * @param monitor 监控器对象
	 * @param record 监控记录
	 * @return
	 */
	protected abstract List<MetricReport> doInspectRecord(Monitor monitor,AbstractRecord record);
	
	/**
	 * 分页批量读取数据库中保存的额监控器数据
	 * @param pageRequest
	 * @return
	 */
	protected abstract Page<Monitor> loadBatch(WrappedPageRequest pageRequest);
	
	protected Long longValue(String value) {
		if(Strings.isNullOrEmpty(value)) {
			return null;
		}
		return Long.valueOf(value);
	}
	protected Integer intValue(String value) {
		if(Strings.isNullOrEmpty(value)) {
			return null;
		}
		return Integer.valueOf(value);
	}
	protected Double doubleValue(String value) {
		if(Strings.isNullOrEmpty(value)) {
			return null;
		}
		return Double.valueOf(value);
	}
}
