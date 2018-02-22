package com.pachiraframework.watchdog.service;

import com.pachiraframework.domain.Page;
import com.pachiraframework.watchdog.dto.SearchMonitorCriteria;
import com.pachiraframework.watchdog.entity.Monitor;

/**
 * @author wangxuzheng
 *
 */
public interface MonitorService {
	/**
	 * 分页查询
	 * @param pageRequest
	 * @return
	 */
	public Page<Monitor> search(SearchMonitorCriteria pageRequest);
}
