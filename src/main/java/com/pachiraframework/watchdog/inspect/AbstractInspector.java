package com.pachiraframework.watchdog.inspect;

import java.util.List;

import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.AbstractRecord;

/**
 * 质检员，负责对原始的监控器检查结果进行故障鉴定，无故障，或者故障属于哪个级别
 * @author KevinWang
 *
 */
public abstract class AbstractInspector<T extends AbstractRecord> {
	/**
	 * 质量审查
	 * @param record
	 * @return
	 */
	public abstract List<MetricReport> inspect(T record);
	
	protected MetricReport createReport(T record) {
		MetricReport report = new MetricReport();
		report.setMoitorId(record.getMoitorId());
		report.setRecordId(record.getId());
		return report;
	}
}
