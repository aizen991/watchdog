package com.pachiraframework.watchdog.inspect;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.pachiraframework.watchdog.constant.Metrics;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.MetricReport.StatusEnum;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.Monitor.TypeEnum;
import com.pachiraframework.watchdog.entity.RedisRecord;

/**
 * @author wangxuzheng
 *
 */
@Component
public class RedisInspector extends AbstractInspector {
	@Override
	public List<MetricReport> inspect(Monitor redisMonitor, AbstractRecord redisRecord) {
		RedisRecord record = (RedisRecord)redisRecord;
		List<MetricReport> list = Lists.newArrayList();
		MetricReport avariableReport = avariableReport(record);
		list.add(avariableReport);
		if(StatusEnum.DOWN.name().equals(avariableReport.getStatus())) {
			return list;
		}
		return list;
	}

	private MetricReport redisReport(RedisRecord record) {
		MetricReport report = createReport(record);
		report.setType(TypeEnum.REDIS.name());
		return report;
	}
	private MetricReport avariableReport(RedisRecord record) {
		MetricReport report = redisReport(record);
		report.setMetric(Metrics.Redis.AVAILABLE);
		if(Strings.isNullOrEmpty(record.getProcessId())) {
			report.setStatus(StatusEnum.DOWN.name());
			record.setMessage(record.getMessage());
		}else {
			report.setStatus(StatusEnum.UP.name());
		}
		return report;
	}
}
