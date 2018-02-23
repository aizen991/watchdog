package com.pachiraframework.watchdog.action.ding;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * 钉钉消息
 * <pre>
 * {
 *     "errcode": 0,
 *     "errmsg": "ok",
 *     "invaliduser": "UserID1|UserID2",
 *     "invalidparty":"PartyID1",
 *     "forbiddenUserId": "UserID1|UserID2",
 *     "messageId":"xxxxxxxxxxxxxxxx"
 * }
 * <pre>
 * @author wangxuzheng
 *
 */
@Data
public class DingMessage {
	@SerializedName("errcode")
	private Integer errorCode;
	@SerializedName("errmsg")
	private String errorMessage;
	@SerializedName("invaliduser")
	private String invalidUser;
	@SerializedName("invalidparty")
	private String invalidParty;
	private String forbiddenUserId;
	private String messageId;
}
