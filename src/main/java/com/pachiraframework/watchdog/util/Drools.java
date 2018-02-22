package com.pachiraframework.watchdog.util;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	private KieContainer kieContainer;
	public static KieSession newAlarmSession(){
		log.debug("新建kiession对象");
		KieSession session = drools.kieContainer.newKieSession("AlarmKS");
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
		session.getCalendars().set("only-weekdays", onlyWeekDays);
		
		//休假时间
		Calendar notWeekDays = new Calendar() {
			@Override
			public boolean isTimeIncluded(long time) {
				return !isWeekdays(time);
			}
		}; 
		session.getCalendars().set("not-weekdays", notWeekDays);
		return session;
	}
	private static boolean isWeekdays(long time){
		DateTime dateTime = new DateTime(time);
		//周一到周五
		return dateTime.getDayOfWeek() < 6 && dateTime.getHourOfDay()<=18 && dateTime.getHourOfDay()>=9;
	}
	@PostConstruct
	public void init(){
		drools = this;
//		drools.kieBase = this.kieBase;
		drools.kieContainer = this.kieContainer;
	}
}
