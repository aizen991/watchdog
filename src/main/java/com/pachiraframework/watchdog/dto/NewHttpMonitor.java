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
public class NewHttpMonitor extends AbstractNewMonitor {
	private String url;
	/**
	 * 超时时间，单位秒
	 */
	private Integer timeout;
	private String method;
	private String requestParams;
	private String userid;
	private String password;
	private String userAgent;
	private String httpHeader;
	private String shouldContain;
	private String notContain;
	private String caseSensitive;
}
