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
public class PingRecord extends AbstractRecord {
	/**
	 * 已发送
	 */
	private Integer sent;
	/**
	 * 已接收
	 */
	private Integer success;
	/**
	 * 丢失
	 */
	private Integer lost;
	/**
	 * 最短
	 */
	private Double min;
	/**
	 * 最长
	 */
	private Double max;
	/**
	 * 平均
	 */
	private Double avg;
}
