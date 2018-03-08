package com.pachiraframework.watchdog.inspect;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.pachiraframework.watchdog.constant.Metrics;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MemcachedRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.MetricReport.StatusEnum;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.Monitor.TypeEnum;

/**
 * @author wangxuzheng
 *
 */
@Component
public class MemcachedInspector extends AbstractInspector {
	@Override
	public List<MetricReport> inspect(Monitor monitor, AbstractRecord memcachedRecord) {
		MemcachedRecord record = (MemcachedRecord) memcachedRecord;
		List<MetricReport> list = Lists.newArrayList();
		// 健康检查
		MetricReport avriableReport = aviableReprot(record);
		list.add(avriableReport);
		if (avriableReport.getStatus().equals(StatusEnum.DOWN.name())) {
			return list;
		}
		// 缓存命中率
		list.add(connectionReport(record));

		// 内存利用率
		list.add(memoryUsedReport(record));
		return list;
	}

	private MetricReport aviableReprot(MemcachedRecord record) {
		MetricReport report = createMemcachedReport(record);
		report.setMetric(Metrics.Memcached.AVAILABLE);
		report.setMessage(record.getMessage());
		report.setStatus(Strings.isNullOrEmpty(record.getPid()) ? StatusEnum.DOWN.name() : StatusEnum.UP.name());
		return report;
	}

	private MetricReport createMemcachedReport(MemcachedRecord record) {
		MetricReport report = createReport(record);
		report.setType(TypeEnum.MEMCACHED.name());
		return report;
	}

	private MetricReport memoryUsedReport(MemcachedRecord record) {
		MetricReport report = createMemcachedReport(record);
		report.setMetric(Metrics.Memcached.GET_HITS_RATE);
		double rate = record.getBytes() * 1D / record.getLimitMaxbytes();
		report.setMessage("memcached内存利用率:" + rate);
		if (rate >= 0.95) {
			report.setStatus(StatusEnum.CRITICAL.name());
		} else if (rate >= 0.85) {
			report.setStatus(StatusEnum.WARNING.name());
		} else {
			report.setStatus(StatusEnum.CLEAR.name());
		}
		return report;
	}

	private MetricReport connectionReport(MemcachedRecord record) {
		MetricReport report = createMemcachedReport(record);
		report.setMetric(Metrics.Memcached.GET_HITS_RATE);
		if (record.getCmdGet().longValue() == 0L) {
			report.setStatus(StatusEnum.CRITICAL.name());
			report.setMessage("缓存命中率：0");
		} else {
			double rate = record.getGetHits() * 1D / record.getCmdGet();
			report.setMessage("缓存命中率：" + rate);
			if (rate <= 0.2D) {
				report.setStatus(StatusEnum.CRITICAL.name());
			} else if (rate <= 0.6) {
				report.setStatus(StatusEnum.WARNING.name());
			} else {
				report.setStatus(StatusEnum.CLEAR.name());
			}
		}
		return report;
	}
}
