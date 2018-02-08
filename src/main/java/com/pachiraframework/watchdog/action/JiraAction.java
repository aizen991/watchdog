package com.pachiraframework.watchdog.action;

import org.springframework.stereotype.Component;

import com.pachiraframework.watchdog.event.event.MetricReportEvent;

/**
 * 创建Jira bug
 * 
 * @author KevinWang
 *
 */
@Component
public class JiraAction extends LimitedAction {
	private static final String TEMPLATE_NAME = "alarm/ping/jira.ftl";

	@Override
	protected void doExecute(MetricReportEvent context) throws Exception {

	}

	@Override
	protected String templateName() {
		return TEMPLATE_NAME;
	}

}
