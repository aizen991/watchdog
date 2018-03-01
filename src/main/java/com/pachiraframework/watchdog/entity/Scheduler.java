package com.pachiraframework.watchdog.entity;

import com.pachiraframework.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wangxuzheng
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class Scheduler extends BaseEntity<Long> {
	private static final long serialVersionUID = -5171331016361756763L;
	private String cron;
	private String name;
	private String description;
}
