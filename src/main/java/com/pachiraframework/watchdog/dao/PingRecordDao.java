package com.pachiraframework.watchdog.dao;

import org.springframework.stereotype.Repository;

@Repository
public class PingRecordDao extends AbstractRecordDao{
	@Override
	protected String indexName() {
		return "monitor_record";
	}
}
