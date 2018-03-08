package com.pachiraframework.watchdog.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.elasticsearch.common.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.watchdog.dao.HttpMonitorDao;
import com.pachiraframework.watchdog.dao.MonitorDao;
import com.pachiraframework.watchdog.dto.NewHttpMonitor;
import com.pachiraframework.watchdog.entity.HttpMonitor;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.Monitor.TypeEnum;
import com.pachiraframework.watchdog.service.HttpMonitorService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Service
public class HttpMonitorServiceImpl implements HttpMonitorService {
	@Autowired
	private MonitorDao monitorDao;
	@Autowired
	private HttpMonitorDao httpMonitorDao;
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
	public ExecuteResult<HttpMonitor> create(NewHttpMonitor newHttpMonitor) {
		log.info("创建http monitor:{}", newHttpMonitor);
		try {
			checkNotNull(newHttpMonitor != null, "请求参数不允许为空");
			checkNotNull(newHttpMonitor.getName(), "监控器名称不允许为空");
			checkArgument(Strings.hasLength(newHttpMonitor.getName()), "监控器名称不允许为空");
			checkArgument(newHttpMonitor.getName().length() <= 100, "名称长度不能超过100");

			Monitor monitor = new Monitor();
			monitor.setName(newHttpMonitor.getName());
			monitor.setSchedulerId(newHttpMonitor.getSchedulerId());
			monitor.setType(TypeEnum.HTTP.name());
			monitorDao.insert(monitor);

			HttpMonitor httpMonitor = new HttpMonitor();
			BeanUtils.copyProperties(newHttpMonitor, httpMonitor);
			httpMonitor.setId(monitor.getId());
			httpMonitor.setTimeout(newHttpMonitor.getTimeout());
			httpMonitorDao.insert(httpMonitor);
			log.info("创建 http monitor 成功:{}",httpMonitor);
			return ExecuteResult.newSuccessResult(httpMonitor);
		} catch (IllegalArgumentException | NullPointerException | IllegalStateException e) {
			log.error("monitor.telnet.create.fail:{}", e.getMessage());
			return ExecuteResult.newErrorResult(e.getMessage());
		}
	}
	@Override
	public ExecuteResult<HttpMonitor> get(Long id) {
		HttpMonitor monitor = httpMonitorDao.getById(id);
		return ExecuteResult.newSuccessResult(monitor);
	}
	
}
