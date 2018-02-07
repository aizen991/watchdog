package com.pachiraframework.watchdog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.domain.Page;
import com.pachiraframework.watchdog.dto.SearchMonitorCriteria;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.service.MonitorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author wangxuzheng
 *
 */
@RestController
@RequestMapping("/api/monitor/")
public class MonitorController {
	@Autowired
	private MonitorService monitorService;
	
	@ApiOperation(value = "监控器查询", notes = "根据条件，检索匹配的监控器列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "分页的任务列表信息") })
	@RequestMapping(path = "/search", method = { RequestMethod.GET })
	public ResponseEntity<ExecuteResult<Page<Monitor>>> search(SearchMonitorCriteria criteria) {
		Page<Monitor> page = monitorService.search(criteria);
		ExecuteResult<Page<Monitor>> result = ExecuteResult.newSuccessResult(page);
		return ResponseEntity.ok(result);
	}
}
