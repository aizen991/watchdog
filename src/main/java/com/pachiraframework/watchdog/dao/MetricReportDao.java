package com.pachiraframework.watchdog.dao;

import org.springframework.stereotype.Repository;

/**
 * @author wangxuzheng
 *
 */
@Repository
public class MetricReportDao extends AbstractElasticsearchDao {
	private static final String INDEX_TYPE = "metric_report";

	@Override
	protected String indexType() {
		return INDEX_TYPE;
	}
	
}
