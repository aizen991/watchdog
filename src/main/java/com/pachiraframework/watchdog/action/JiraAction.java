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
public class JiraAction extends AbstractLimitedAction {
	@Override
	protected void doExecute(MetricReportEvent context) throws Exception {

	}

	@Override
	protected String name() {
		return "jira";
	}

}
