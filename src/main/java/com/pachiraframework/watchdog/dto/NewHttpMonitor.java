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
	private Integer timeout;//秒
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
