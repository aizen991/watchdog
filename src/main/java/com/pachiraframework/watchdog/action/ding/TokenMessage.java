package com.pachiraframework.watchdog.action.ding;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * <pre>
 * a)正确的Json返回结果:
 * {
 *     "errcode": 0,
 *     "errmsg": "ok",
 *     "access_token": "fw8ef8we8f76e6f7s8df8s"
 * }
 * b)错误的Json返回示例:
 * {
 *    "errcode": 43003,
 *    "errmsg": "require https"
 * }
 * </pre>
 * @author wangxuzheng
 *
 */
@Data
public class TokenMessage {
	@SerializedName("errcode")
	private Integer errorCode;
	@SerializedName("errmsg")
	private String errorMessage;
	@SerializedName("access_token")
	private String accessToken;
}
