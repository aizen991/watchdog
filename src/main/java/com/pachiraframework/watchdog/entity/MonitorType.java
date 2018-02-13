package com.pachiraframework.watchdog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 监控类型
 * 
 * @author wangxuzheng
 *
 */
@Getter
@AllArgsConstructor
public enum MonitorType {
	/**
	 * PING 主机
	 */
	PING("PING", "ping.png"),
	/**
	 * 
	 */
	TELNET("TELNET", "telnet.png"),
	/**
	 * HTTP URL监控
	 */
	HTTP("HTTP", "url.png"),
	/**
	* memcached
	*/
	MEMCACHED("MEMCACHED", "memcached.png");
	private String name;
	/**
	 * 图标地址
	 */
	private String icon;
}
