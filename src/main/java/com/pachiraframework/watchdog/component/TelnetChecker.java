package com.pachiraframework.watchdog.component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.watchdog.dao.MetricReportDao;
import com.pachiraframework.watchdog.dao.TelnetMonitorDao;
import com.pachiraframework.watchdog.dao.TelnetRecordDao;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.TelnetMonitor;
import com.pachiraframework.watchdog.entity.TelnetRecord;
import com.pachiraframework.watchdog.inspect.TelnetInspector;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class TelnetChecker extends AbstractChecker {
	@Autowired
	private TelnetMonitorDao telnetMonitorDao;
	@Autowired
	private MetricReportDao metricReportDao;
	@Autowired
	private TelnetInspector telnetInspector;
	@Autowired
	private TelnetRecordDao telnetRecordDao;

	@Override
	protected TelnetRecord doMonitor(Monitor m) {
		TelnetMonitor monitor = (TelnetMonitor) m;
		TelnetRecord record = new TelnetRecord();
		Socket target = null;
		Stopwatch stopwatch = Stopwatch.createStarted();
		try {
			target = new Socket();
			InetSocketAddress address = new InetSocketAddress(monitor.getHost(), monitor.getPort());
			target.connect(address, monitor.getTimeout() * 1000);
			long milliseconds = stopwatch.elapsed(TimeUnit.MILLISECONDS);
			record.setResponseTime(milliseconds);
		} catch (IOException e) {
			record.setResponseTime(Long.MAX_VALUE);
			record.setMessage(Throwables.getStackTraceAsString(e));
		} finally {
			stopwatch.stop();
			if (target != null) {
				try {
					target.close();
				} catch (IOException e) {
					log.warn(Throwables.getStackTraceAsString(e));
				}
			}
		}
		record.setMonitorId(monitor.getId());
		record.setTimestamp(new Date());
		telnetRecordDao.insert(record);
		log.info("monitor.telnet.record.insert.success:插入es成功:{}", record.getId());
		return record;
	}

	@Override
	protected List<MetricReport> doInspectRecord(Monitor monitor,AbstractRecord record) {
		List<MetricReport> results = telnetInspector.inspect(monitor,(TelnetRecord) record);
		metricReportDao.batchInsert(results);
		log.info("monitor.telnet.metric.report.insert.success:record id ={},size={}", record.getId(), results.size());
		return results;
	}

	@Override
	protected Page<Monitor> loadBatch(WrappedPageRequest pageRequest) {
		Page<Monitor> page = telnetMonitorDao.findByPage(pageRequest);
		return page;
	}

}
