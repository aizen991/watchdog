package com.pachiraframework.watchdog.action;

import java.io.StringWriter;

import org.springframework.stereotype.Component;

import com.pachiraframework.watchdog.event.event.MetricReportEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * 将监控信息打印到控制台或日志文件中--测试环境下使用
 * 
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class ConsoleAction extends AbstractAction {
	private static final String TEMPLATE_NAME = "alarm/ping/console.ftl";

	@Override
	protected void doExecute(MetricReportEvent context) throws Exception {
		StringWriter out = new StringWriter();
		getTemplate().process(context, out);
		String text = out.toString();
		log.info(text);
	}

	@Override
	protected String templateName() {
		return TEMPLATE_NAME;
	}
}