package com.pachiraframework.watchdog.entity;

import lombok.Data;

/**
 * 监控指标
 * @author KevinWang
 *
 */
@Data
public class Metric {
	private String id;
	private String name;
	private String type;
}
