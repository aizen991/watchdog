package com.pachiraframework.watchdog.action.sms;

import org.springframework.stereotype.Component;

import com.pachiraframework.watchdog.action.AbstractLimitedAction;
import com.pachiraframework.watchdog.event.event.MetricReportEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * 短信通知策略
 * @author wangxuzheng@aliyun.com
 *
 */
@Component
@Slf4j
public class SmsAction extends AbstractLimitedAction {
	@Override
	protected void doExecute(MetricReportEvent context) throws Exception{
		log.info("对接短信平台发送短信：{}",context);		
	}
	@Override
	protected String name() {
		return "sms";
	}

}
