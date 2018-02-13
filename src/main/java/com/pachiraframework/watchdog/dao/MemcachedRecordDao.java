package com.pachiraframework.watchdog.dao;

import org.springframework.stereotype.Repository;

/**
 * @author wangxuzheng
 *
 */
@Repository
public class MemcachedRecordDao extends AbstractRecordDao {
	private static final String INDEX_TYPE = "memcached_record";
	@Override
	protected String index() {
		return INDEX_TYPE;
	}

}
