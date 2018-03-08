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
import com.pachiraframework.watchdog.dao.TelnetMonitorDao;
import com.pachiraframework.watchdog.dto.NewTelnetMonitor;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.Monitor.TypeEnum;
import com.pachiraframework.watchdog.entity.TelnetMonitor;
import com.pachiraframework.watchdog.service.TelnetMonitorService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Service
public class TelnetMonitorServiceImpl implements TelnetMonitorService {
	@Autowired
	private MonitorDao monitorDao;
	@Autowired
	private TelnetMonitorDao telnetMonitorDao;
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
	public ExecuteResult<TelnetMonitor> create(NewTelnetMonitor newTelnetMonitor) {
		log.info("创建telnet monitor:{}", newTelnetMonitor);
		try {
			checkNotNull(newTelnetMonitor != null, "请求参数不允许为空");
			checkNotNull(newTelnetMonitor.getName(), "监控器名称不允许为空");
			checkArgument(Strings.hasLength(newTelnetMonitor.getName()), "监控器名称不允许为空");
			checkArgument(newTelnetMonitor.getName().length() <= 100, "名称长度不能超过100");

			Monitor monitor = new Monitor();
			monitor.setName(newTelnetMonitor.getName());
			monitor.setSchedulerId(newTelnetMonitor.getSchedulerId());
			monitor.setType(TypeEnum.TELNET.name());
			monitorDao.insert(monitor);

			TelnetMonitor telnetMonitor = new TelnetMonitor();
			telnetMonitor.setId(monitor.getId());
			telnetMonitor.setPort(newTelnetMonitor.getPort());
			telnetMonitor.setHost(newTelnetMonitor.getHost());
			telnetMonitor.setTimeout(newTelnetMonitor.getTimeout());
			telnetMonitorDao.insert(telnetMonitor);
			log.info("创建 telnet monitor 成功:{}",telnetMonitor);
			return ExecuteResult.newSuccessResult(telnetMonitor);
		} catch (IllegalArgumentException | NullPointerException | IllegalStateException e) {
			log.error("monitor.telnet.create.fail:{}", e.getMessage());
			return ExecuteResult.newErrorResult(e.getMessage());
		}
	}
}
