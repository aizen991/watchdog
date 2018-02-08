package com.pachiraframework.watchdog.event.event;

import java.util.List;

import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.Monitor;

import lombok.Builder;
import lombok.Getter;

/**
 * 监控指标输出事件
 * @author wangxuzheng
 *
 */
@Getter
@Builder
public class MetricReportEvent {
	private Monitor monitor;
	private AbstractRecord record;
	private List<MetricReport> reports;
}
