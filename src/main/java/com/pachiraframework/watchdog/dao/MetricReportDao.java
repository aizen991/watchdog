package com.pachiraframework.watchdog.dao;

import org.springframework.stereotype.Repository;

/**
 * @author wangxuzheng
 *
 */
@Repository
public class MetricReportDao extends AbstractElasticsearchDao {
	private static final String INDEX_NAME = "metric_report";
	@Override
	protected String indexName() {
		return INDEX_NAME;
	}
	
}
