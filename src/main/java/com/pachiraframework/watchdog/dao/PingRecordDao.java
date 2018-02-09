package com.pachiraframework.watchdog.dao;

import org.springframework.stereotype.Repository;

/**
 * @author KevinWang
 *
 */
@Repository
public class PingRecordDao extends AbstractRecordDao{
	private static final String INDEX_TYPE = "ping_record";

	@Override
	protected String index() {
		return INDEX_TYPE;
	}
}
