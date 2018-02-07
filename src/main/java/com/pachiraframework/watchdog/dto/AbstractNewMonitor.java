package com.pachiraframework.watchdog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 新建监控器
 * @author wangxuzheng
 *
 */
@Getter
@Setter
@ToString
public abstract class AbstractNewMonitor {
	private String name;
	private Long pollingInterval = 5000L;
}
