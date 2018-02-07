package com.pachiraframework.watchdog.entity.elasticsearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wangxuzheng@aliyun.com
 *
 */
@Getter
@Setter
@ToString(callSuper = true)
public class TelnetRecord extends MonitorRecord {
	private Long responseTime;
}
