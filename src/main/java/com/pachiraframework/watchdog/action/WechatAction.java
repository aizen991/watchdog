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
	@Override
	protected void doExecute(MetricReportEvent context) throws Exception {

	}
	@Override
	protected String name() {
		return "wechat";
	}
}
