package com.pachiraframework.watchdog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pachiraframework.domain.Order;
import com.pachiraframework.domain.Order.Direction;
import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.watchdog.dao.MonitorDao;
import com.pachiraframework.watchdog.dao.MonitorTypeDao;
import com.pachiraframework.watchdog.dto.SearchMonitorCriteria;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.MonitorType;
import com.pachiraframework.watchdog.service.MonitorService;

/**
 * @author wangxuzheng
 *
 */
@Service
public class MonitorServiceImpl implements MonitorService {
	@Autowired
	private MonitorDao monitorDao;
	@Autowired
	private MonitorTypeDao monitorTypeDao;
	@Override
	public Page<Monitor> search(SearchMonitorCriteria criteria) {
		criteria.getOrders().add(new Order("started_at", Direction.DESC));
		WrappedPageRequest pageRequest = new WrappedPageRequest(criteria);
		return monitorDao.findByPage(pageRequest);
	}
	@Override
	public List<MonitorType> findAll() {
		return monitorTypeDao.findAll();
	}
	@Override
	public MonitorType get(String id) {
		return monitorTypeDao.getById(id);
	}

}
