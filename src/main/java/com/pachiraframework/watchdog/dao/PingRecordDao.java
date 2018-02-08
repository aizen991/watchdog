package com.pachiraframework.watchdog.dao;

import org.springframework.stereotype.Repository;

@Repository
public class PingRecordDao extends AbstractRecordDao{
	private static final String INDEX_NAME = "ping_record";
	@Override
	protected String indexName() {
		return INDEX_NAME;
	}
}
