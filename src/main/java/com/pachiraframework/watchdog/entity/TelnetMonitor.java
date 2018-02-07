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
public class TelnetMonitor extends Monitor {
	private static final long serialVersionUID = 5750500075567844597L;
	private String host;
	private Integer port;
	private Integer timeout;
}
