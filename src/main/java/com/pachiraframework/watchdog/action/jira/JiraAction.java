package com.pachiraframework.watchdog.action.jira;

import java.io.StringWriter;
import java.io.Writer;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.stream.JsonWriter;
import com.pachiraframework.watchdog.action.AbstractLimitedAction;
import com.pachiraframework.watchdog.event.event.MetricReportEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建Jira bug
 * https://developer.atlassian.com/cloud/jira/platform/rest/#api-api-2-issue-post
 * @author KevinWang
 *
 */
@Slf4j
@Component
public class JiraAction extends AbstractLimitedAction implements InitializingBean {
	@Value("${jira.user?:null}")
	private String user;
	@Value("${jira.password?:null}")
	private String password;
	@Value("${jira.url?:null}")
	private String url;
	@Value("${jira.bugId?:1}")
	private Long typeId;
	private JiraClient jiraClient;

	@Override
	protected void doExecute(MetricReportEvent context) throws Exception {
		log.info("jira action");
		StringWriter out = new StringWriter();
		getTemplate(context).process(context, out);
		String description = out.toString();
		Writer writer = new StringWriter();
		JsonWriter jsonWriter = new JsonWriter(writer);
		jsonWriter.beginObject()
			.name("fields").beginObject()
				.name("summary").value("something'\\''s wrong")
				.name("environment").value("environment")
				.name("description").value(description)
				.name("duedate").value("2011-03-11")
				.name("project").beginObject()
					.name("id").value("10000")
				.endObject()
				.name("issuetype").beginObject()
					.name("id").value("5555")
				.endObject()
				.name("assignee").beginObject()
					.name("name").value("homer")
				.endObject()
				.name("priority").beginObject()
					.name("id").value("20000")
				.endObject()
			.endObject();
		jsonWriter.close();
		String data = writer.toString();
		log.info("{}",data);
		String result = jiraClient.createIssue(data);
		log.info(result);
	}

	@Override
	protected String name() {
		return "jira";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		jiraClient = new JiraClient(url, user, password);
	}

}
