package com.pachiraframework.watchdog.action.jira;

import java.io.StringWriter;
import java.net.URI;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.pachiraframework.watchdog.action.AbstractLimitedAction;
import com.pachiraframework.watchdog.entity.MonitorType;
import com.pachiraframework.watchdog.event.event.MetricReportEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
//	@Value("${jira.user}")
//	private String user;
//	@Value("${jira.password}")
//	private String password;
//	@Value("${jira.url}")
//	private String url;
//	@Value("${jira.bug.id}")
//	private Long typeId;
//	private JiraRestClient jiraRestClient;

	@Override
	protected void doExecute(MetricReportEvent context) throws Exception {
		log.info("jira action");
//		IssueInputBuilder issueBuilder = new IssueInputBuilder();
//		issueBuilder.setProjectKey("");
//		// BUG
//		issueBuilder.setIssueTypeId(this.typeId);
//		StringWriter out = new StringWriter();
//		getTemplate(context).process(context, out);
//		String content = out.toString();
//		issueBuilder.setDescription(content);
//		issueBuilder.setSummary("(" + context.getMonitor().getType() + ")" + context.getMonitor().getName());
//		issueBuilder.setAssigneeName(this.user);//分配给负责人
//		IssueInput issueInput = issueBuilder.build();
//		BasicIssue issue = jiraRestClient.getIssueClient().createIssue(issueInput).claim();
//		log.info("告警信息【{}】已经创建jira BUG:{}",context.getMonitor().getId(),issue.getKey());
	}

	@Override
	protected String name() {
		return "jira";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
//		URI jiraServerUri = new URI(this.url);
//		this.jiraRestClient = factory.createWithBasicHttpAuthentication(jiraServerUri, this.user, this.password);
	}

	@AllArgsConstructor
	@Getter
	private static enum IssueTypeEnum {
		Epic(10200L), Task(3L), Test(10101L), Improvement(10400L), Bug(10300L), SubRequirement(10401L), SubTask(
				10300L), Story(10100L);

		private Long id;
	}
}
