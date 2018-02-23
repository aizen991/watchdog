package com.pachiraframework.watchdog.action.wechat;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.pachiraframework.watchdog.action.AbstractLimitedAction;
import com.pachiraframework.watchdog.event.event.MetricReportEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * 微信通知
 * 
 * @author KevinWang
 *
 */
@Slf4j
@Component
public class WechatAction extends AbstractLimitedAction {
	@Value("${wechat.corpId?:null}")
	private String corpId;
	@Value("${wechat.secretId?:null}")
	private String secretId;
	private static Gson gson = new Gson();
	@Override
	protected void doExecute(MetricReportEvent context) throws Exception {
		String token = getToken();
		String sendUrl = "http://out.mappings.asura.com/5/qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+token;
		WechatMessage entity = new WechatMessage();
		
// TODO       entity.setToUser(monitorMessagesEntity.getWeixin().replace(",","|"));
        entity.setMessageType("text");
        entity.setAccessToken(token);
// TODO      entity.setAgentId(channelEntity.getWeixinAppId());
        Map<String,Object> map = new HashMap<String,Object>();
        StringWriter out = new StringWriter();
		getTemplate(context).process(context, out);
		String content = out.toString();
        map.put("content", content);
        entity.setText(map);
        log.info(gson.toJson(entity).replace("\\u003e",">"));
        HttpRequest httpRequest = HttpRequest.post(sendUrl,true,gson.toJson(entity).replace("\\u003e", ">"));
        String result = httpRequest.body();
        log.info("{}",result);
        httpRequest.disconnect();
	}
	@Override
	protected String name() {
		return "wechat";
	}
	
	/**
	 * 获取微信token
	 * @param corpId
	 * @param secretId
	 * @return
	 */
	private String getToken() {
		String url = "http://out.mappings.asura.com/4/qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpId+"&corpsecret="+secretId;
		HttpRequest request = HttpRequest.get(url);
		String wechatMessage = request.body();
		log.info("FROM WECHAT={}",wechatMessage);
		request.disconnect();
		WechatMessage message = gson.fromJson(wechatMessage, WechatMessage.class);
		return message.getAccessToken();
	}
}
