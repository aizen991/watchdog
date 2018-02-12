package com.pachiraframework.watchdog.component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.PageRequest;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.event.WatchdogEventBus;
import com.pachiraframework.watchdog.event.event.MetricReportEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * 监控器检查
 * @author wangxuzheng
 *
 */
@Slf4j
public abstract class AbstractChecker {
	private ExecutorService executorService = Executors.newFixedThreadPool(30);
	/**
	 * 批量抓去数据的
	 */
	protected static final Integer BATCH_FETCH_SIZE = 20;
	/**
	 * 执行检查
	 * @param interval 检查的间隔
	 */
	public void check(Integer interval) {
		int startPage = 0;
		WrappedPageRequest pageRequest = new WrappedPageRequest(new PageRequest(startPage, BATCH_FETCH_SIZE));
		pageRequest.addParam("interval", interval);
		Page<Monitor> page = loadBatch(pageRequest);
		while (!page.getContent().isEmpty()) {
			log.info("开始处理Monitor批次,startPage={},batchSize={},size={},interval={}", startPage, BATCH_FETCH_SIZE,
					page.getContent().size(),interval);
			for (Monitor monitor : page.getContent()) {
				executorService.submit(()->{
					log.info("检查monitor-id:{},name:{}",monitor.getId(),monitor.getName());
					AbstractRecord record = doMonitor(monitor);
					List<MetricReport> reports = doInspectRecord(record);
					MetricReportEvent event = MetricReportEvent.builder().reports(reports).monitor(monitor).record(record).build();
					WatchdogEventBus.asyncEventBus().post(event);
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
	 * @param record
	 * @return
	 */
	protected abstract List<MetricReport> doInspectRecord(AbstractRecord record);
	
	/**
	 * 分页批量读取数据库中保存的额监控器数据
	 * @param pageRequest
	 * @return
	 */
	protected abstract Page<Monitor> loadBatch(WrappedPageRequest pageRequest);
}
