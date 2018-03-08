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
	 * 创建新的http监控器
	 * @param newHttpMonitor
	 * @return
	 */
	public ExecuteResult<HttpMonitor> create(NewHttpMonitor newHttpMonitor);
	
	/**
	 * 根据id查明细
	 * @param id
	 * @return
	 */
	public ExecuteResult<HttpMonitor> get(Long id);
}
