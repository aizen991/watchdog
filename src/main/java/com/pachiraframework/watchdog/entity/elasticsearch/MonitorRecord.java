package com.pachiraframework.watchdog.entity.elasticsearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 监控结果记录
 * @author wangxuzheng
 *
 */
@Getter
@Setter
@ToString
public class MonitorRecord implements Indexable{
	private String id;
	private Long moitorId;
	private String message;
}
