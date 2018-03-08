package com.pachiraframework.watchdog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.watchdog.entity.Scheduler;
import com.pachiraframework.watchdog.service.SchedulerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 调度器相关接口
 * @author wangxuzheng
 *
 */
@Api("调度器管理")
@RestController
@RequestMapping("/api")
public class SchedulerController {
	@Autowired
	private SchedulerService schedulerService;
	@ApiOperation(value = "调度器列表", notes = "系统中支持的调度器列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "调度器列表") })
	@RequestMapping(path = "/schedulers", method = { RequestMethod.GET })
	public ResponseEntity<ExecuteResult<List<Scheduler>>> add() {
		List<Scheduler> result = schedulerService.findAll();
		return ResponseEntity.ok(ExecuteResult.newSuccessResult(result));
	}
}
