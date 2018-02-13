package com.pachiraframework.watchdog.entity;

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
public class MemcachedMonitor extends Monitor {
	private static final long serialVersionUID = -9174584609774100969L;
	private String host;
	private Integer port = 11211;
}
