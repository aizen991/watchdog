package com.pachiraframework.watchdog.dto;

import com.pachiraframework.domain.PageRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangxuzheng
 *
 */
@Getter
@Setter
public class SearchMonitorCriteria extends PageRequest {
	private static final long serialVersionUID = 1981830829612028220L;
	private String name;
	private String type;
}
