package com.pachiraframework.watchdog.inspect;

import java.util.List;

import com.google.common.collect.Lists;
import com.pachiraframework.watchdog.constant.Metrics;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.MonitorType;
import com.pachiraframework.watchdog.entity.PingRecord;

/**
 * 判断ping结果.
 * <pre>
 * 1.UP/DOWN:结果中成功次数为0视为DOWN,其他情况视为成功
 * 2.CRITICAL:结果中超过50%失败
 * </pre>
 * @author KevinWang
 *
 */
public class PingInspector extends AbstractInspector<PingRecord> {

	@Override
	public List<MetricReport> inspect(PingRecord record) {
		List<MetricReport> list = Lists.newArrayList();
		//判断UP/DOWN
		MetricReport health = createReport(record);
		health.setMetric(Metrics.PING.AVAILABLE);
		health.setType(MonitorType.PING.getName());
		health.setStatus(record.getSuccess().equals(0)?Metrics.AVAILABLE_DOWN:Metrics.AVAILABLE_UP);
		health.setMessage(record.getMessage());
		list.add(health);
		
		//判断是否是CRITICAL
		MetricReport critical = createReport(record);
		//判断是否是WARNING
		
		//判断是否是
		return list;
	}


}
