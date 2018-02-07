package com.pachiraframework.watchdog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wangxuzheng@aliyun.com
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class PingMonitor extends Monitor {
	private static final long serialVersionUID = -7677112512564363405L;
	private String host;
	private Integer timeout;
}
