package com.pachiraframework.watchdog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pachiraframework.domain.Order;
import com.pachiraframework.domain.Order.Direction;
import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.watchdog.dao.MonitorDao;
import com.pachiraframework.watchdog.dto.SearchMonitorCriteria;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.service.MonitorService;

/**
 * @author wangxuzheng
 *
 */
@Service
public class MonitorServiceImpl implements MonitorService {
	@Autowired
	private MonitorDao monitorDao;
	@Override
	public Page<Monitor> search(SearchMonitorCriteria criteria) {
		criteria.getOrders().add(new Order("started_at", Direction.DESC));
		WrappedPageRequest pageRequest = new WrappedPageRequest(criteria);
		return monitorDao.findByPage(pageRequest);
	}

}
