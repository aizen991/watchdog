package com.pachiraframework.watchdog.inspect;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.pachiraframework.watchdog.constant.Metrics;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.HttpMonitor;
import com.pachiraframework.watchdog.entity.HttpMonitor.CaseSensitiveEnum;
import com.pachiraframework.watchdog.entity.HttpRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.MetricReport.StatusEnum;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.Monitor.TypeEnum;

/**
 * 判断telnet结果.
 * 
 * <pre>
 * 检查如下指标：
 * 1.Metrics.HTTP.AVAILABLE
 * </pre>
 * 
 * @author KevinWang
 *
 */
@Component
public class HttpInspector extends AbstractInspector {
	private static final Long WARNING_VALUE = 3000L;
	@Override
	public List<MetricReport> inspect(Monitor monitor,AbstractRecord abstractRecord) {
		HttpRecord record = (HttpRecord)abstractRecord;
		HttpMonitor httpMonitor = (HttpMonitor)monitor;
		List<MetricReport> list = Lists.newArrayList();
		// Metrics.HTTP.AVAILABLE 存活
		MetricReport available = availableReport(record);
		list.add(available);
		if (available.getStatus().equals(StatusEnum.DOWN.toString())) {
			return list;
		}
		
		// Metrics.HTTP.AVAILABLE 响应时间指标
		MetricReport responseTimeReport = responseTimeReport(record);
		list.add(responseTimeReport);
		
		// Metrics.HTTP.AVAILABLE 响应内容匹配指标
		list.add(responseBodyReport(httpMonitor, record));
		
		return list;
	}
	private MetricReport responseBodyReport(HttpMonitor httpMonitor,HttpRecord record) {
		MetricReport report = createTelnetReport(record);
		report.setMetric(Metrics.Http.RESPONSE_BODY);
		report.setStatus(StatusEnum.CLEAR.name());
		CaseSensitiveEnum caseSensitiveEnum = CaseSensitiveEnum.of(httpMonitor.getCaseSensitive());
		boolean should = true;
		boolean shouldNot = true;
		if(!Strings.isNullOrEmpty(httpMonitor.getShouldContain())){
			if(CaseSensitiveEnum.NO.equals(caseSensitiveEnum)){
				should = new CaseInsensitiveStrategyDecorator(new ShouldContainStrategy(record.getBody())).match(httpMonitor.getShouldContain());
			}else{
				should = new ShouldContainStrategy(record.getBody()).match(httpMonitor.getShouldContain());
			}
		}
		if(!should) {
			report.setStatus(StatusEnum.CRITICAL.name());
			report.setMessage(String.format("返回结果没有包含预期内容：%s",httpMonitor.getShouldContain()));
		}
		if(!Strings.isNullOrEmpty(httpMonitor.getShouldNotContain())){
			if(CaseSensitiveEnum.NO.equals(caseSensitiveEnum)){
				shouldNot = new CaseInsensitiveStrategyDecorator(new ShouldNotContainStrategy(record.getBody())).match(httpMonitor.getShouldNotContain());
			}else{
				shouldNot = new ShouldNotContainStrategy(record.getBody()).match(httpMonitor.getShouldNotContain());
			}
		}
		if(!shouldNot){
			report.setStatus(StatusEnum.CRITICAL.name());
			report.setMessage(Strings.nullToEmpty(record.getMessage())+String.format(" 返回结果包含预期之外的内容：%s",httpMonitor.getShouldNotContain()));
		}
		return report;
	}

	private MetricReport responseTimeReport(HttpRecord record) {
		MetricReport report = createTelnetReport(record);
		report.setMetric(Metrics.Http.RESPONSE_TIME);
		Long response = record.getResponseTime();
		if(response >= WARNING_VALUE) {
			report.setStatus(StatusEnum.WARNING.name());
			report.setMessage(String.format("响应时间%s,超过阀值%s毫秒",record.getResponseTime(),WARNING_VALUE));
		}else {
			report.setStatus(StatusEnum.CLEAR.name());
			report.setMessage(String.format("响应时间：%s毫秒",record.getResponseTime()));
		}
		return report;
	}
	private MetricReport createTelnetReport(HttpRecord record) {
		MetricReport report = createReport(record);
		report.setType(TypeEnum.HTTP.name());
		report.setTimestamp(new Date());
		return report;
	}

	/**
	 * ping成功数量为0视为DOWN
	 * 
	 * @param record
	 * @return
	 */
	private MetricReport availableReport(HttpRecord record) {
		MetricReport available = createTelnetReport(record);
		available.setMetric(Metrics.Http.AVAILABLE);
		available.setMessage(record.getMessage());
		available.setStatus(HttpStatus.OK.value()== record.getCode().intValue()?StatusEnum.UP.toString():StatusEnum.DOWN.toString());
		return available;
	}
}
