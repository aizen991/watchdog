package com.pachiraframework.watchdog.entity;

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
public abstract class AbstractRecord implements Indexable{
	private String id;
	private Long timestamp;
	private Long moitorId;
	private String message;
}
