package com.pachiraframework.watchdog.entity.elasticsearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 监控指标报告
 * @author wangxuzheng
 *
 */
@Getter
@Setter
@ToString
public class MetricsReport implements Indexable {
	/**
	 * 报告ID
	 */
	private String id;
	/**
	 * 监控器ID
	 */
	private Long moitorId;
	/**
	 * 监控结果ID
	 */
	private String recordId;
	/**
	 * 监控器类型
	 */
	private String type;
	/**
	 * 要监控的指标
	 */
	private String metrics;
	
	/**
	 * 监控结果UP/DOWN/CLEAR/WARNING/CRITICAL
	 */
	private String status;
}
