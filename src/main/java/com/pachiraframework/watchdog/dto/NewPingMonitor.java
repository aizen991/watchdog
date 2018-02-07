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
public class NewPingMonitor extends AbstractNewMonitor {
	private String host;
	private Integer timeout = 50;
}
