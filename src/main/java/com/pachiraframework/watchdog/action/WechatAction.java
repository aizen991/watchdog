package com.pachiraframework.watchdog.action;

import org.springframework.stereotype.Component;

import com.pachiraframework.watchdog.event.event.MetricReportEvent;

/**
 * 微信通知
 * 
 * @author KevinWang
 *
 */
@Component
public class WechatAction extends LimitedAction {
	private static final String TEMPLATE_NAME = "alarm/ping/wechat.ftl";

	@Override
	protected void doExecute(MetricReportEvent context) throws Exception {

	}

	@Override
	protected String templateName() {
		return TEMPLATE_NAME;
	}

}
