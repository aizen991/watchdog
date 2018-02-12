package com.pachiraframework.watchdog.dao;

import org.springframework.stereotype.Repository;

/**
 * @author KevinWang
 *
 */
@Repository
public class HttpRecordDao extends AbstractRecordDao {
	private static final String INDEX_TYPE = "http_record";

	@Override
	protected String index() {
		return INDEX_TYPE;
	}

}
