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
public class HttpRecord extends AbstractRecord {
	/**
	 * 响应时间
	 */
	private Long responseTime;
	/**
	 * 响应状态码
	 */
	private Integer code;
	/**
	 * 响应内容
	 */
	private String body;
}
