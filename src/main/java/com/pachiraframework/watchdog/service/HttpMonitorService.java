package com.pachiraframework.watchdog.service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.watchdog.dto.NewHttpMonitor;
import com.pachiraframework.watchdog.entity.HttpMonitor;

/**
 * @author wangxuzheng
 *
 */
public interface HttpMonitorService {
	/**
	 * 创建新的ping监控器
	 * @param pingMonitor
	 * @return
	 */
	public ExecuteResult<HttpMonitor> create(NewHttpMonitor newHttpMonitor);
}
