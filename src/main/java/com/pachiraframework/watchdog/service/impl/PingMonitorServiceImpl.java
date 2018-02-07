package com.pachiraframework.watchdog.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.elasticsearch.common.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.watchdog.dao.MonitorDao;
import com.pachiraframework.watchdog.dao.PingMonitorDao;
import com.pachiraframework.watchdog.dto.NewPingMonitor;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.MonitorType;
import com.pachiraframework.watchdog.entity.PingMonitor;
import com.pachiraframework.watchdog.service.PingMonitorService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Service
public class PingMonitorServiceImpl implements PingMonitorService {
	@Autowired
	private MonitorDao monitorDao;
	@Autowired
	private PingMonitorDao pingMonitorDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
	public ExecuteResult<PingMonitor> create(NewPingMonitor newPingMonitor) {
		log.info("创建ping monitor:{}", newPingMonitor);
		try {
			checkNotNull(newPingMonitor != null, "请求参数不允许为空");
			checkNotNull(newPingMonitor.getName(), "监控器名称不允许为空");
			checkArgument(Strings.hasLength(newPingMonitor.getName()), "监控器名称不允许为空");
			checkArgument(newPingMonitor.getName().length() <= 100, "名称长度不能超过100");

			Monitor monitor = new Monitor();
			monitor.setName(newPingMonitor.getName());
			monitor.setPollingInterval(newPingMonitor.getPollingInterval());
			monitor.setType(MonitorType.PING.getName());
			monitorDao.insert(monitor);

			PingMonitor pingMonitor = new PingMonitor();
			pingMonitor.setId(monitor.getId());
			pingMonitor.setHost(newPingMonitor.getHost());
			pingMonitor.setTimeout(newPingMonitor.getTimeout());
			pingMonitorDao.insert(pingMonitor);
			log.info("创建 ping monitor 成功:{}",pingMonitor);
			return ExecuteResult.newSuccessResult(pingMonitor);
		} catch (IllegalArgumentException | NullPointerException | IllegalStateException e) {
			log.error("monitor.ping.create.fail:{}", e.getMessage());
			return ExecuteResult.newErrorResult(e.getMessage());
		}
	}
}
