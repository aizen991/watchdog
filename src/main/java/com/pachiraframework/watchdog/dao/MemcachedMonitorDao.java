package com.pachiraframework.watchdog.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.pachiraframework.dao.support.SqlCondition;
import com.pachiraframework.dao.support.SqlQueryCommand;

/**
 * @author wangxuzheng
 *
 */
@Repository
public class MemcachedMonitorDao extends MonitorDao {
	@Override
	public <T> T getById(Serializable id) {
		SqlQueryCommand command = new SqlQueryCommand();
		command.addWhereSqlCondition(SqlCondition.and("h.id", "=", id));
		return this.findOneBySqlCommand(command);
	}
}
