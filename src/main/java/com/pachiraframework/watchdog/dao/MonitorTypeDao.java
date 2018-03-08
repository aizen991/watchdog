package com.pachiraframework.watchdog.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pachiraframework.dao.BaseDao;
import com.pachiraframework.dao.support.SqlCondition;
import com.pachiraframework.dao.support.SqlQueryCommand;
import com.pachiraframework.watchdog.entity.MonitorType;


/**
 * 不从数据库中读取数据，代码中配置好
 * @author wangxuzheng
 *
 */
@Repository
public class MonitorTypeDao extends BaseDao{
	@Override
	public <T> T getById(Serializable id) {
		SqlQueryCommand command = new SqlQueryCommand();
		command.addWhereSqlCondition(SqlCondition.and("m.id", "=", id));
		return this.findOneBySqlCommand(command);
	}
	public List<MonitorType> findAll() {
		SqlQueryCommand command = new SqlQueryCommand();
		return this.findListBySqlCommand(command);
	}
}
