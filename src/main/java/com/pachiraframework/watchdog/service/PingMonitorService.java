package com.pachiraframework.watchdog.service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.watchdog.dto.NewPingMonitor;
import com.pachiraframework.watchdog.entity.PingMonitor;

/**
 * Ping监控服务接口
 * @author wangxuzheng
 *
 */
public interface PingMonitorService {
	/**
	 * 创建新的ping监控器
	 * @param pingMonitor
	 * @return
	 */
	public ExecuteResult<PingMonitor> create(NewPingMonitor pingMonitor);
	
	/**
	 * 根据id查询ping监控器明细
	 * @param id
	 * @return
	 */
	public ExecuteResult<PingMonitor> get(Long id);
}
