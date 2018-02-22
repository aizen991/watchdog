package com.pachiraframework.watchdog.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.watchdog.WatchdogApplicationTest;
import com.pachiraframework.watchdog.dto.NewHttpMonitor;
import com.pachiraframework.watchdog.entity.HttpMonitor;
import com.pachiraframework.watchdog.entity.HttpMonitor.CaseSensitiveEnum;
import com.pachiraframework.watchdog.entity.HttpMonitor.MethodEnum;
import com.pachiraframework.watchdog.service.HttpMonitorService;

/**
 * @author wangxuzheng
 *
 */
public class HttpMonitorServiceImplTest extends WatchdogApplicationTest{
	@Autowired
	private HttpMonitorService httpMonitorService;
	@Test
	@DisplayName("测试正常创建TelnetMonitor")
	public void testCreate() {
		NewHttpMonitor httpMonitor = new NewHttpMonitor();
		httpMonitor.setName("baidu.com");
		httpMonitor.setUrl("https://baidu.com");
		httpMonitor.setMethod(MethodEnum.GET.name());
		httpMonitor.setCaseSensitive(CaseSensitiveEnum.NO.name());
		httpMonitor.setTimeout(3000);
//		telnetMonitor.setTimeout(50);
		ExecuteResult<HttpMonitor> result = httpMonitorService.create(httpMonitor);
		assertTrue(result.isSuccess());
		HttpMonitor monitor = result.getResult();
		assertNotNull(monitor);
	}

}
