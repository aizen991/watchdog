package com.pachiraframework.watchdog.service;

import com.pachiraframework.domain.Page;
import com.pachiraframework.watchdog.dto.SearchMonitorCriteria;
import com.pachiraframework.watchdog.entity.Monitor;

/**
 * @author wangxuzheng
 *
 */
public interface MonitorService {
	public Page<Monitor> search(SearchMonitorCriteria pageRequest);
}
