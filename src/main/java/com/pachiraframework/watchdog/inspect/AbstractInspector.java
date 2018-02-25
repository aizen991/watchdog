package com.pachiraframework.watchdog.inspect;

import java.util.Date;
import java.util.List;

import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.Monitor;

/**
 * 质检员，负责对原始的监控器检查结果进行故障鉴定，无故障，或者故障属于哪个级别
 * @author KevinWang
 *
 */
public abstract class AbstractInspector{
	/**
	 * 质量审查
	 * @param monitor 监控器
	 * @param record 监控记录
	 * @return
	 */
	public abstract List<MetricReport> inspect(Monitor monitor,AbstractRecord record);
	
	protected MetricReport createReport(AbstractRecord record) {
		MetricReport report = new MetricReport();
		report.setMoitorId(record.getMonitorId());
		report.setRecordId(record.getId());
		report.setTimestamp(new Date());
		return report;
	}
}
