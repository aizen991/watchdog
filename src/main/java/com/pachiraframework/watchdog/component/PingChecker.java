package com.pachiraframework.watchdog.component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.PageRequest;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.watchdog.dao.PingMonitorDao;
import com.pachiraframework.watchdog.dao.elasticsearch.PingRecordDao;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.PingMonitor;
import com.pachiraframework.watchdog.entity.elasticsearch.MetricsReport;
import com.pachiraframework.watchdog.entity.elasticsearch.PingRecord;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class PingChecker extends AbstractChecker implements DisposableBean {
	@Autowired
	private PingRecordDao pingRecordDao;
	@Autowired
	private PingMonitorDao pingMonitorDao;
	private ExecutorService executorService = Executors.newFixedThreadPool(100);
	@Override
	public void check(Integer interval) {
		int startPage = 0;
		WrappedPageRequest pageRequest = new WrappedPageRequest(new PageRequest(startPage, BATCH_FETCH_SIZE));
		pageRequest.addParam("interval", interval);
		Page<PingMonitor> page = pingMonitorDao.findByPage(pageRequest);
		while (!page.getContent().isEmpty()) {
			log.info("开始处理Monitor批次,startPage={},batchSize={},size={}", startPage, BATCH_FETCH_SIZE,
					page.getContent().size());
			for (Monitor monitor : page.getContent()) {
				executorService.submit(new HandleTask(monitor));
			}
			pageRequest = new WrappedPageRequest(new PageRequest(++startPage, BATCH_FETCH_SIZE));
			page = pingMonitorDao.findByPage(pageRequest);
		}
	}
	protected PingRecord doMonitor(Monitor monitor) {
		String host = ((PingMonitor)monitor).getHost();
		PingRecord record = Ping.ping(host);
		record.setMoitorId(monitor.getId());
		return record;
	}
	private class HandleTask extends Thread{
		private Monitor monitor;
		public HandleTask(Monitor monitor){
			this.monitor = monitor;
		}
		@Override
		public void run() {
			log.info("检查monitor-{}",monitor);
			PingRecord record = doMonitor(monitor);
			pingRecordDao.insert(record);
			log.info("monitor.ping.record.insert.success:插入es成功:{}",record);
			//规则引擎匹配UP/DOWN/CLEAR/WARNING/CRITIAL级别
			
			List<MetricsReport> results = calculatorMonitorResults(record);
			handleMonitorRecordResult(record, results);
		}
		private void handleMonitorRecordResult(PingRecord record, List<MetricsReport> results) {
			// TODO 触发告警规则
		}
		private List<MetricsReport> calculatorMonitorResults(PingRecord record) {
			List<MetricsReport> list = Lists.newArrayList();
			return list;
		}
	}
	@Override
	public void destroy() throws Exception {
		executorService.shutdown();
	}
}
