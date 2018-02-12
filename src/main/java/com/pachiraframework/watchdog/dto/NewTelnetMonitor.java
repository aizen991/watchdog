package com.pachiraframework.watchdog.dto;

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
public class NewTelnetMonitor extends AbstractNewMonitor {
	/**
	 * 主机地址或者IP
	 */
	private String host;
	/**
	 * 检测端口
	 */
	private Integer port;
	/**
	 * 超时时间，5秒钟
	 */
	private Integer timeout = 5*1000;
}
