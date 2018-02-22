package com.pachiraframework.watchdog.service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.watchdog.dto.NewTelnetMonitor;
import com.pachiraframework.watchdog.entity.TelnetMonitor;

/**
 * @author wangxuzheng
 *
 */
public interface TelnetMonitorService {
	/**
	 * 创建新的telnet监控器
	 * @param telnetMonitor
	 * @return
	 */
	public ExecuteResult<TelnetMonitor> create(NewTelnetMonitor telnetMonitor);
}
