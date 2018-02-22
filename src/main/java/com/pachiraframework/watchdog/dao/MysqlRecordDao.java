package com.pachiraframework.watchdog.dao;

import org.springframework.stereotype.Repository;

/**
 * @author wangxuzheng
 *
 */
@Repository
public class MysqlRecordDao extends AbstractRecordDao {
	private static final String INDEX_TYPE = "mysql_record";
	@Override
	protected String index() {
		return INDEX_TYPE;
	}

}
