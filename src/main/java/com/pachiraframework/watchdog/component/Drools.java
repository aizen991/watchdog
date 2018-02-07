package com.pachiraframework.watchdog.component;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class Drools {
	private static Drools drools;
	@Autowired
	@Setter
	private KieBase kieBase;
	public static KieSession newStatefulSession(){
		log.debug("新建kiession对象");
//		KieSessionConfiguration configuration = kieContainer.getKieSessionConfiguration();
//		configuration.setOption(TimedRuleExectionOption.YES);//自动执行timer-rule
//		KieSession session = kieContainer().newKieSession(configuration);
		KieSession session = drools.kieBase.newKieSession();
//		session.addEventListener(new TrackingAgendaEventListener());
//		session.addEventListener(new DebugAgendaEventListener());
//		session.addEventListener(new DebugRuleRuntimeEventListener());
		
		//每周一到周五，09:00-18:00
		Calendar onlyWeekDays = new Calendar() {
			@Override
			public boolean isTimeIncluded(long time) {
				return isWeekdays(time);
			}
		};
		session.getCalendars().set("only-weekdays", onlyWeekDays);//要与drl文件中的定义一致
		
		//休假时间
		Calendar notWeekDays = new Calendar() {
			@Override
			public boolean isTimeIncluded(long time) {
				return !isWeekdays(time);
			}
		}; 
		session.getCalendars().set("not-weekdays", notWeekDays);//要与drl文件中的定义一致
		return session;
	}
	private static boolean isWeekdays(long time){
		DateTime dateTime = new DateTime(time);
		return dateTime.getDayOfWeek() < 6 && dateTime.getHourOfDay()<=18 && dateTime.getHourOfDay()>=9;//周一到周五
	}
	@PostConstruct
	public void init(){
		drools = this;
		drools.kieBase = this.kieBase;
	}
}
