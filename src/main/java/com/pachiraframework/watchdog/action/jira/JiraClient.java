package com.pachiraframework.watchdog.action.jira;

import java.util.Map;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;

/**
 * @author KevinWang
 *
 */
public class JiraClient {
	private String url;
	private String user;
	private String password;

	/**
	 * @param url
	 *            jira访问地址
	 * @param user
	 *            jira用户名
	 * @param password
	 *            jira密码
	 */
	public JiraClient(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	/**
	 * post操作
	 * 
	 * @param api
	 * @param data
	 * @return
	 */
	public String post(String api, String data) {
		String apiUrl = this.url + api;
		Map<String, Object> map = Maps.newHashMap();
		map.put("data", data);
		HttpRequest httpRequest = HttpRequest.post(apiUrl, map, true);
		warpHttpRequest(httpRequest);
		String body = httpRequest.body();
		httpRequest.disconnect();
		return body;
	}
	
	/**
	 * GET请求
	 * @param api
	 * @return
	 */
	public String get(String api) {
		String apiUrl = this.url + api;
		HttpRequest httpRequest = HttpRequest.get(apiUrl);
		warpHttpRequest(httpRequest);
		String body = httpRequest.body();
		httpRequest.disconnect();
		return body;
	}
	
	/**
	 * 创建jira issue
	 * @param data
	 * @return
	 */
	public String createIssue(String data) {
		return post(JiraApi.CREATE_ISSUE, data);
	}

	private void warpHttpRequest(HttpRequest httpRequest) {
		httpRequest.header("Accept", "application/json");
		httpRequest.header("Content-Type", "application/json");
		httpRequest.basic(this.user, this.password);
	}
}
