package com.pachiraframework.watchdog.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统定义的调度器
 * @author wangxuzheng
 *
 */
@Getter
@AllArgsConstructor
public enum Schedulers {
	SCHEDULER1(1,"","","");
	private Integer id;
	private String cron;
	private String name;
	private String description;
}
