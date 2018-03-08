package com.pachiraframework.watchdog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pachiraframework.watchdog.dao.SchedulerDao;
import com.pachiraframework.watchdog.entity.Scheduler;
import com.pachiraframework.watchdog.service.SchedulerService;

/**
 * @author wangxuzheng
 *
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {
	@Autowired
	private SchedulerDao schedulerDao;
	@Override
	public List<Scheduler> findAll() {
		return schedulerDao.findAll();
	}

}
