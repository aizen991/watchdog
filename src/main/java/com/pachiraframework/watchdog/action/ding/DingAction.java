package com.pachiraframework.watchdog.action.ding;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.pachiraframework.watchdog.action.AbstractLimitedAction;
import com.pachiraframework.watchdog.event.event.MetricReportEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * 钉钉通知策略.<br/>
 * 获取token:<br/>
 * https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.i4Zcr5&treeId=385&articleId=104980&docType=1#s2<br/>
 * 发送消息：<br/>
 * https://open-doc.dingtalk.com/doc2/detail.htm?treeId=172&articleId=104973&docType=1
 * 
 * @author wangxuzheng@aliyun.com
 *
 */
@Slf4j
@Component
public class DingAction extends AbstractLimitedAction {
	private Gson gson = new Gson();
	/**
	 * 企业Id
	 */
	@Value("${ding.corpId?:null}")
	private String corpId;
	/**
	 * 企业应用的凭证密钥
	 */
	@Value("${ding.corpSecret?:null}")
	private String corpSecret;

	@Override
	protected void doExecute(MetricReportEvent context) throws Exception {
		String accessToken = getToken();
		String url = "https://oapi.dingtalk.com/message/send?access_token=" + accessToken;
		Map<String, Object> params = Maps.newHashMap();
		// 员工id列表（消息接收者，多个接收者用|分隔）
		params.put("touser", "");
		// 部门id列表，多个接收者用|分隔。touser或者toparty 二者有一个必填，不支持递归发送，如果需要给部门下面子部门发送消息则需要查询出子部门id
		params.put("toparty", "");
		// 企业应用id，这个值代表以哪个应用的名义发送消息
		params.put("agentid", "");
		HttpRequest httpRequest = HttpRequest.post(url, params, true);
		String body = httpRequest.body();
		httpRequest.disconnect();
		DingMessage message = gson.fromJson(body,DingMessage.class);
		log.info("{}",message);
	}

	@Override
	protected String name() {
		return "ding";
	}

	private String getToken() {
		String url = String.format("https://oapi.dingtalk.com/gettoken?corpid=%s&corpsecret=%s", this.corpId,
				this.corpSecret);
		HttpRequest httpRequest = HttpRequest.get(url);
		String body = httpRequest.body();
		log.info(body);
		httpRequest.disconnect();
		TokenMessage message = gson.fromJson(body, TokenMessage.class);
		return message.getAccessToken();
	}
}
