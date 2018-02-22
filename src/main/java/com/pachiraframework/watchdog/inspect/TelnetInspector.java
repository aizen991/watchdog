package com.pachiraframework.watchdog.inspect;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.pachiraframework.watchdog.constant.Metrics;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.MetricReport.StatusEnum;
import com.pachiraframework.watchdog.entity.MonitorType;
import com.pachiraframework.watchdog.entity.TelnetRecord;

/**
 * 判断telnet结果.
 * 
 * <pre>
 * 检查如下指标：
 * 1.Metrics.TELNET.AVAILABLE
 * </pre>
 * 
 * @author KevinWang
 *
 */
@Component
public class TelnetInspector extends AbstractInspector {
	@Override
	public List<MetricReport> inspect(Monitor monitor,AbstractRecord telnetRecord) {
		TelnetRecord record = (TelnetRecord)telnetRecord;
		List<MetricReport> list = Lists.newArrayList();

		// Metrics.TELNET.AVAILABLE 存活
		MetricReport available = availableReport(record);
		list.add(available);
		if (available.getStatus().equals(StatusEnum.DOWN.toString())) {
			return list;
		}
		return list;
	}

	private MetricReport createTelnetReport(TelnetRecord record) {
		MetricReport report = createReport(record);
		report.setType(MonitorType.TELNET.getName());
		report.setTimestamp(new Date());
		return report;
	}

	/**
	 * ping成功数量为0视为DOWN
	 * 
	 * @param record
	 * @return
	 */
	private MetricReport availableReport(TelnetRecord record) {
		MetricReport available = createTelnetReport(record);
		available.setMetric(Metrics.Telnet.AVAILABLE);
		available.setStatus(record.getResponseTime() ==Long.MAX_VALUE ? StatusEnum.DOWN.toString() : StatusEnum.UP.toString());
		available.setMessage(record.getMessage());
		return available;
	}

}
