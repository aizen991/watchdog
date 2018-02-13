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
import com.pachiraframework.watchdog.entity.PingRecord;

/**
 * 判断ping结果.
 * 
 * <pre>
 * 检查如下指标：
 * 1.Metrics.PING.AVAILABLE:结果中成功次数为0视为DOWN,其他情况视为成功
 * 2.Metrics.PING.SUCCESS_RATE 80%以上正常，50%以上警告，其他严重
 * 3.Metrics.PING.RESPONSE_TIME 1.5秒以内正常，1.5秒以上警告，2秒以上严重
 * </pre>
 * 
 * @author KevinWang
 *
 */
@Component
public class PingInspector extends AbstractInspector {
	@Override
	public List<MetricReport> inspect(Monitor monitor,AbstractRecord pingRecord) {
		PingRecord record = (PingRecord)pingRecord;
		List<MetricReport> list = Lists.newArrayList();

		// Metrics.PING.AVAILABLE 存活
		MetricReport available = availableReport(record);
		list.add(available);
		if (available.getStatus().equals(StatusEnum.DOWN.toString())) {
			return list;
		}

		// Metrics.PING.SUCCESS_RATE 成功率
		MetricReport successRateReport = successRateReport(record);
		list.add(successRateReport);

		// Metrics.PING.RESPONSE_TIME 平均返回时间
		MetricReport responseTimeReport = responseTimeReport(record);
		list.add(responseTimeReport);
		return list;
	}

	private MetricReport responseTimeReport(PingRecord record) {
		MetricReport responseTimeReport = createPingReport(record);
		responseTimeReport.setMetric(Metrics.PING.RESPONSE_TIME);
		double time = record.getAvg();
		if (time > 2000) {
			responseTimeReport.setStatus(StatusEnum.CRITICAL.toString());
			responseTimeReport.setMessage("平均响应时间：" + record.getAvg() + "毫秒，超过2000毫秒");
		} else if (time > 1500) {
			responseTimeReport.setStatus(StatusEnum.WARNING.toString());
			responseTimeReport.setMessage("平均响应时间：" + record.getAvg() + "毫秒，超过1500毫秒");
		} else {
			responseTimeReport.setStatus(StatusEnum.CLEAR.toString());
			responseTimeReport.setMessage("平均响应时间：" + record.getAvg() + "毫秒.");
		}
		return responseTimeReport;
	}

	private MetricReport createPingReport(PingRecord record) {
		MetricReport report = createReport(record);
		report.setType(MonitorType.PING.getName());
		report.setTimestamp(new Date());
		return report;
	}

	/**
	 * ping成功数量为0视为DOWN
	 * 
	 * @param record
	 * @return
	 */
	private MetricReport availableReport(PingRecord record) {
		MetricReport available = createPingReport(record);
		available.setMetric(Metrics.PING.AVAILABLE);
		available.setStatus(record.getSuccess().equals(0) ? StatusEnum.DOWN.toString() : StatusEnum.UP.toString());
		available.setMessage(record.getMessage() == null ? "成功次数：" + record.getSuccess() : record.getMessage());
		return available;
	}

	private MetricReport successRateReport(PingRecord record) {
		MetricReport successRateReport = createPingReport(record);
		successRateReport.setMetric(Metrics.PING.SUCCESS_RATE);
		// 80%-正常，50%警告，其他正常
		double successRate = (record.getSuccess() == null ? 0 : record.getSuccess()) / record.getSent();
		successRateReport.setMessage("Ping成功率：" + successRate * 100 + "%");
		if (successRate >= 0.8D) {
			successRateReport.setStatus(StatusEnum.CLEAR.toString());
		} else if (successRate >= 0.5D) {
			successRateReport.setStatus(StatusEnum.WARNING.toString());
		} else {
			successRateReport.setStatus(StatusEnum.CRITICAL.toString());
		}
		return successRateReport;
	}

}
