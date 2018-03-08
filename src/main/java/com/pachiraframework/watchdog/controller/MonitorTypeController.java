package com.pachiraframework.watchdog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.watchdog.entity.MonitorType;
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
public class MonitorTypeController {
	@Autowired
	private MonitorService monitorService;
	@ApiOperation(value = "监控器类型列表", notes = "展示系统中所有的监控器", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "系统中所有的监控器信息") })
	@RequestMapping(path = "/types", method = { RequestMethod.GET })
	public ResponseEntity<ExecuteResult<List<MonitorType>>> types() {
		List<MonitorType> result = monitorService.findAll();
		return ResponseEntity.ok(ExecuteResult.newSuccessResult(result));
	}
}
