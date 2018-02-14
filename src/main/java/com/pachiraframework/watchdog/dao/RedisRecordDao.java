package com.pachiraframework.watchdog.dao;

import org.springframework.stereotype.Repository;

/**
 * @author wangxuzheng
 *
 */
@Repository
public class RedisRecordDao extends AbstractRecordDao {
	private static final String INDEX_TYPE = "redis_record";
	@Override
	protected String index() {
		return INDEX_TYPE;
	}

}
