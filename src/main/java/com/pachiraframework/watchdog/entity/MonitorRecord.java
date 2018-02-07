package com.pachiraframework.watchdog.entity;

import com.google.common.base.Enums;
import com.google.common.base.Optional;
import com.pachiraframework.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 监控output
 * @author wangxuzheng@aliyun.com
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class MonitorRecord extends BaseEntity<Long> {
	private static final long serialVersionUID = -4147085931879798808L;
	private Monitor monitor;
	private String availability;
	private String message;
	
	public static enum AvailabilityEnum{
		UP,DOWN;
		public static AvailabilityEnum of(String condition){
			Optional<AvailabilityEnum> optional = Enums.getIfPresent(AvailabilityEnum.class, condition);
			return optional.get();
		}
	}
}
