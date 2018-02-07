package com.pachiraframework.watchdog.dao.elasticsearch;

import org.springframework.stereotype.Repository;

@Repository
public class TelnetRecordDao extends AbstractRecordDao {
	@Override
	protected String indexName() {
		return "telnet_record";
	}

}
