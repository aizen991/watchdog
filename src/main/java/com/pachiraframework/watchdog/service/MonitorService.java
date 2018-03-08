package com.pachiraframework.watchdog.service;

import java.util.List;

import com.pachiraframework.domain.Page;
import com.pachiraframework.watchdog.dto.SearchMonitorCriteria;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.MonitorType;

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
	
	/**
	 * 获得系统中所有的监控器类型列表
	 * @return
	 */
	public List<MonitorType> findAll();
	
	/**
	 * 获取单个监控器类型明细
	 * @param id
	 * @return
	 */
	public MonitorType get(String id);
}
