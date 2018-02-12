package com.pachiraframework.watchdog.inspect;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.pachiraframework.watchdog.constant.Metrics;
import com.pachiraframework.watchdog.entity.HttpMonitor;
import com.pachiraframework.watchdog.entity.HttpMonitor.CaseSensitiveEnum;
import com.pachiraframework.watchdog.entity.HttpMonitor.HttpConditionEnum;
import com.pachiraframework.watchdog.entity.HttpRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.MetricReport.StatusEnum;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.MonitorType;

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
public class HttpInspector extends AbstractInspector<HttpRecord> {
	@Override
	public List<MetricReport> inspect(Monitor monitor,HttpRecord record) {
		HttpMonitor httpUrlMonitor = (HttpMonitor)monitor;
		List<MetricReport> list = Lists.newArrayList();
		// Metrics.TELNET.AVAILABLE 存活
		MetricReport available = availableReport(record);
		list.add(available);
		if (available.getStatus().equals(StatusEnum.DOWN.toString())) {
			return list;
		}
		
		//TODO 响应时间指标
		
		//TODO 响应内容匹配指标
		
		HttpConditionEnum condition = HttpConditionEnum.of(httpUrlMonitor.getHttpCondition());
		boolean match = !HttpConditionMatcher.MATCHER_MAP.get(condition).match(record.getCode(), httpUrlMonitor.getHttpValue());
		if(match){
			CaseSensitiveEnum caseSensitiveEnum = CaseSensitiveEnum.of(httpUrlMonitor.getCaseSensitive());
			boolean should = true;
			boolean shouldNot = true;
			if(!Strings.isNullOrEmpty(httpUrlMonitor.getShouldContain())){
				if(CaseSensitiveEnum.NO.equals(caseSensitiveEnum)){
					should = new CaseInsensitiveStrategyDecorator(new ShouldContainStrategy(record.getBody())).match(httpUrlMonitor.getShouldContain());
				}else{
					should = new ShouldContainStrategy(record.getBody()).match(httpUrlMonitor.getShouldContain());
				}
			}
			if(!Strings.isNullOrEmpty(httpUrlMonitor.getShouldNotContain())){
				if(CaseSensitiveEnum.NO.equals(caseSensitiveEnum)){
					shouldNot = new CaseInsensitiveStrategyDecorator(new ShouldNotContainStrategy(record.getBody())).match(httpUrlMonitor.getShouldNotContain());
				}else{
					shouldNot = new ShouldNotContainStrategy(record.getBody()).match(httpUrlMonitor.getShouldNotContain());
				}
			}
			if(should && shouldNot){
				record.setMessage("OK");
			}else{
				record.setMessage("返回内容不匹配");
			}
		}else{
			record.setMessage("OK");
		}
		return list;
	}

	private MetricReport createTelnetReport(HttpRecord record) {
		MetricReport report = createReport(record);
		report.setType(MonitorType.HTTP.getName());
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
		available.setMetric(Metrics.HTTP.AVAILABLE);
		available.setMessage(record.getMessage());
		available.setStatus(HttpStatus.OK.value()== record.getCode().intValue()?StatusEnum.UP.toString():StatusEnum.DOWN.toString());
		return available;
	}
}
