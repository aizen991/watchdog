package com.pachiraframework.watchdog.service;

import java.util.List;

import com.pachiraframework.watchdog.entity.Scheduler;

/**
 * @author wangxuzheng
 *
 */
public interface SchedulerService {
	
	/**
	 * 获得系统中所有的监控器类型列表
	 * @return
	 */
	public List<Scheduler> findAll();
}
