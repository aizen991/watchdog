package com.pachiraframework.watchdog.action.wechat;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * 微信消息对象封装
 * @author wangxuzheng
 *
 */
@Data
public class WechatMessage {
	@SerializedName("touser")
	private String toUser;
	@SerializedName("msgtype")
	private String messageType;
	@SerializedName("agentid")
	private String agentId;
	private Map<String, Object> text;
	@SerializedName("access_token")
	private String accessToken;
}
