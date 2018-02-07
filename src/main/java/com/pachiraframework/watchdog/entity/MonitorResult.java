package com.pachiraframework.watchdog.entity;

import com.google.common.base.Enums;
import com.google.common.base.Optional;
import com.pachiraframework.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 监控结果
 * 
 * @author wangxuzheng@aliyun.com
 *
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MonitorResult extends BaseEntity<Long> {
	private static final long serialVersionUID = -4790740212823314408L;
	private Monitor monitor;
	private String health;
	private String message;
	public static enum HealthEnum{
		CLEAR,WARNING,CRITICAL,UNKNOWN;
		public static HealthEnum of(String health){
			Optional<HealthEnum> optional = Enums.getIfPresent(HealthEnum.class, health);
			return optional.get();
		}
	}
}
