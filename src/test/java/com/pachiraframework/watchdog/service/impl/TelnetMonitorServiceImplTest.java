package com.pachiraframework.watchdog.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.watchdog.WatchdogApplicationTest;
import com.pachiraframework.watchdog.dto.NewTelnetMonitor;
import com.pachiraframework.watchdog.entity.TelnetMonitor;
import com.pachiraframework.watchdog.service.TelnetMonitorService;

public class TelnetMonitorServiceImplTest extends WatchdogApplicationTest{
	@Autowired
	private TelnetMonitorService telnetMonitorService;
	@Test
	@DisplayName("测试正常创建TelnetMonitor")
	public void testCreate() {
		NewTelnetMonitor telnetMonitor = new NewTelnetMonitor();
		telnetMonitor.setHost("127.0.0.1");
		telnetMonitor.setPort(3306);
		telnetMonitor.setName("telnet 127.0.0.1:3306");
//		telnetMonitor.setTimeout(50);
		ExecuteResult<TelnetMonitor> result = telnetMonitorService.create(telnetMonitor);
		assertTrue(result.isSuccess());
		TelnetMonitor monitor = result.getResult();
		assertNotNull(monitor);
	}

}
