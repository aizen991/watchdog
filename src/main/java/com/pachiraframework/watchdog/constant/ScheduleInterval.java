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
	MIN_1(1), MIN_5(5), MIN_10(10), MIN_20(20), MIN_30(30), MIN_40(40), MIN_50(50), MIN_60(60), MIN_90(90), MIN_120(
			120);
	private int minutes;
}
