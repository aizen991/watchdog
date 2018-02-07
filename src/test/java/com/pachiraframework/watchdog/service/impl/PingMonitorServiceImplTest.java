package com.pachiraframework.watchdog.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.watchdog.WatchdogApplicationTest;
import com.pachiraframework.watchdog.dto.NewPingMonitor;
import com.pachiraframework.watchdog.entity.PingMonitor;
import com.pachiraframework.watchdog.service.PingMonitorService;

/**
 * @author wangxuzheng
 *
 */
public class PingMonitorServiceImplTest extends WatchdogApplicationTest{
	@Autowired
	private PingMonitorService pingMonitorService;
	@Test
	@DisplayName("测试正常创建PingMonitor")
	public void testCreate() {
		NewPingMonitor pingMonitor = new NewPingMonitor();
		pingMonitor.setHost("127.0.0.1");
		pingMonitor.setName("ping 127.0.0.1");
		pingMonitor.setTimeout(50);
		ExecuteResult<PingMonitor> result = pingMonitorService.create(pingMonitor);
		assertTrue(result.isSuccess());
		PingMonitor monitor = result.getResult();
		assertNotNull(monitor);
	}

}
