package com.pachiraframework.watchdog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pachiraframework.dao.BaseDao;
import com.pachiraframework.dao.support.SqlQueryCommand;
import com.pachiraframework.watchdog.entity.Scheduler;

/**
 * @author wangxuzheng
 *
 */
@Repository
public class SchedulerDao extends BaseDao {
	public List<Scheduler> findAll(){
		SqlQueryCommand command = new SqlQueryCommand();
		return this.findListBySqlCommand(command);
	}
}
