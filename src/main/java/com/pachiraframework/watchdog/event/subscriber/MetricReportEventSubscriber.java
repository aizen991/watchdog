package com.pachiraframework.watchdog.event.subscriber;

import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.pachiraframework.watchdog.event.event.MetricReportEvent;
import com.pachiraframework.watchdog.util.Drools;

/**
 * @author wangxuzheng
 *
 */
@Component
public class MetricReportEventSubscriber extends AbstractSubscriber {
	@Subscribe
	public void fireAlarmsRules(MetricReportEvent event) {
		KieSession kieSession = Drools.newAlarmSession();
		kieSession.insert(event);
		kieSession.fireAllRules();
		kieSession.dispose();
	}
}
