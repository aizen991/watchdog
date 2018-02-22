package com.pachiraframework.watchdog.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 可以支持
 * 
 * @author wangxuzheng
 *
 */
@Getter
@AllArgsConstructor
public enum ScheduleInterval {
	/**
	 * 1分钟
	 */
	MIN_1(1),
	/**
	 * 5分钟
	 */
	MIN_5(5),
	/**
	 * 10分钟
	 */
	MIN_10(10),
	/**
	 * 20分钟
	 */
	MIN_20(20),
	/**
	 * 30分钟
	 */
	MIN_30(30),
	/**
	 * 40分钟
	 */
	MIN_40(40),
	/**
	 * 50分钟
	 */
	MIN_50(50),
	/**
	 * 60分钟
	 */
	MIN_60(60),
	/**
	 * 90分钟
	 */
	MIN_90(90),
	/**
	 * 120分钟
	 */
	MIN_120(120);
	private int minutes;

	/**
	 * 毫秒数
	 * 
	 * @return
	 */
	public int milliseconds() {
		return 60 * this.minutes * 1000;
	}
}
