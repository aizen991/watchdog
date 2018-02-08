package com.pachiraframework.watchdog.dao;

public abstract class AbstractRecordDao extends AbstractElasticsearchDao {
	private static final String INDEX_TYPE = "ping_record";

	@Override
	protected String indexType() {
		return INDEX_TYPE;
	}

}
