package com.pachiraframework.watchdog.component;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.watchdog.dao.MetricReportDao;
import com.pachiraframework.watchdog.dao.PingMonitorDao;
import com.pachiraframework.watchdog.dao.PingRecordDao;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.PingMonitor;
import com.pachiraframework.watchdog.entity.PingRecord;
import com.pachiraframework.watchdog.inspect.PingInspector;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class PingChecker extends AbstractChecker{
	@Autowired
	private PingRecordDao pingRecordDao;
	@Autowired
	private PingMonitorDao pingMonitorDao;
	@Autowired
	private MetricReportDao metricReportDao;
	@Autowired
	private PingInspector pingInspector;
	
	@Override
	protected List<MetricReport> doInspectRecord(Monitor monitor,AbstractRecord record) {
		List<MetricReport> results = pingInspector.inspect(monitor,(PingRecord)record);
		metricReportDao.batchInsert(results);
		log.info("monitor.ping.metric.report.insert.success:record id ={},size={}",record.getId(),results.size());
		return results;
	}

	@Override
	protected Page<Monitor> loadBatch(WrappedPageRequest pageRequest) {
		Page<Monitor> page = pingMonitorDao.findByPage(pageRequest);
		return page;
	}

	@Override
	protected PingRecord doMonitor(Monitor monitor) {
		String host = ((PingMonitor)monitor).getHost();
		PingRecord record = Ping.ping(host);
		record.setMoitorId(monitor.getId());
		record.setTimestamp(new Date());
		pingRecordDao.insert(record);
		log.info("monitor.ping.record.insert.success:插入es成功:{}",record);
		return record;
	}
}
