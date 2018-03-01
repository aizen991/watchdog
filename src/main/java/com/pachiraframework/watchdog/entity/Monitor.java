package com.pachiraframework.watchdog.entity;

import com.pachiraframework.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 监控对象，如jboss服务器
 * @author wangxuzheng@aliyun.com
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class Monitor extends BaseEntity<Long> {
	private static final long serialVersionUID = 9159124495806864947L;
	/**
	 * 监控器名称
	 */
	private String name;
	/**
	 * 监控器类型
	 */
	private String type;
	private Long schedulerId;
//	private List<MonitorGroup> grops;
}
