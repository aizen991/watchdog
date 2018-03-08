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
public class MonitorType extends BaseEntity<String> {
	private static final long serialVersionUID = 2997293122550235468L;
	private String name;
	/**
	 * 图标地址
	 */
	private String icon;
	/**
	 * 简介
	 */
	private String description;
}
