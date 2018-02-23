package com.pachiraframework.watchdog.action.jira;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.google.gson.stream.JsonWriter;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * {
 * 	"expand": "renderedFields,names,schema,operations,editmeta,changelog,versionedRepresentations",
 * 	"id": "10600",
 * 	"self": "http://jira.rrsjk.com/rest/api/2/issue/10600",
 * 	"key": "HAIERSHUI-27",
 * 	"fields": {
 * 		"issuetype": {
 * 			"self": "http://jira.rrsjk.com/rest/api/2/issuetype/10004",
 * 			"id": "10004",
 * 			"description": "测试过程，维护过程发现影响系统运行的问题。",
 * 			"iconUrl": "http://jira.rrsjk.com/secure/viewavatar?size=xsmall&avatarId=10303&avatarType=issuetype",
 * 			"name": "故障",
 * 			"subtask": false,
 * 			"avatarId": 10303
 * 		},
 * 		"components": [],
 * 		"timespent": null,
 * 		"timeoriginalestimate": null,
 * 		"description": "净水商城把发给网点的短信发给了用户，麻烦修改\r\n有好多用户反映这个问题了",
 * 		"project": {
 * 			"self": "http://jira.rrsjk.com/rest/api/2/project/10101",
 * 			"id": "10101",
 * 			"key": "HAIERSHUI",
 * 			"name": "海尔净水商城",
 * 			"avatarUrls": {
 * 				"48x48": "http://jira.rrsjk.com/secure/projectavatar?pid=10101&avatarId=10601",
 * 				"24x24": "http://jira.rrsjk.com/secure/projectavatar?size=small&pid=10101&avatarId=10601",
 * 				"16x16": "http://jira.rrsjk.com/secure/projectavatar?size=xsmall&pid=10101&avatarId=10601",
 * 				"32x32": "http://jira.rrsjk.com/secure/projectavatar?size=medium&pid=10101&avatarId=10601"
 * 			}
 * 		},
 * 		"fixVersions": [],
 * 		"aggregatetimespent": null,
 * 		"resolution": {
 * 			"self": "http://jira.rrsjk.com/rest/api/2/resolution/10004",
 * 			"id": "10004",
 * 			"description": "GreenHopper Managed Resolution",
 * 			"name": "完成"
 * 		},
 * 		"timetracking": {},
 * 		"customfield_10005": "0|i000d3:",
 * 		"attachment": [{
 * 			"self": "http://jira.rrsjk.com/rest/api/2/attachment/10301",
 * 			"id": "10301",
 * 			"filename": "iHaier图片20180108165217.jpg",
 * 			"author": {
 * 				"self": "http://jira.rrsjk.com/rest/api/2/user?username=niemy@haier.com",
 * 				"name": "niemy@haier.com",
 * 				"key": "niemy@haier.com",
 * 				"emailAddress": "niemy@haier.com",
 * 				"avatarUrls": {
 * 					"48x48": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=48",
 * 					"24x24": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=24",
 * 					"16x16": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=16",
 * 					"32x32": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=32"
 * 				},
 * 				"displayName": "聂 孟义",
 * 				"active": true,
 * 				"timeZone": "PRC"
 * 			},
 * 			"created": "2018-01-08T16:52:33.000+0800",
 * 			"size": 128118,
 * 			"mimeType": "image/jpeg",
 * 			"content": "http://jira.rrsjk.com/secure/attachment/10301/iHaierå¾ç20180108165217.jpg",
 * 			"thumbnail": "http://jira.rrsjk.com/images/broken_thumbnail.png"
 * 		}],
 * 		"aggregatetimeestimate": null,
 * 		"resolutiondate": "2018-01-09T10:09:44.000+0800",
 * 		"workratio": -1,
 * 		"summary": "净水商城把发给网点的短信发给了用户，麻烦修改",
 * 		"lastViewed": "2018-02-23T20:42:26.492+0800",
 * 		"watches": {
 * 			"self": "http://jira.rrsjk.com/rest/api/2/issue/HAIERSHUI-27/watchers",
 * 			"watchCount": 2,
 * 			"isWatching": false
 * 		},
 * 		"creator": {
 * 			"self": "http://jira.rrsjk.com/rest/api/2/user?username=niemy@haier.com",
 * 			"name": "niemy@haier.com",
 * 			"key": "niemy@haier.com",
 * 			"emailAddress": "niemy@haier.com",
 * 			"avatarUrls": {
 * 				"48x48": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=48",
 * 				"24x24": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=24",
 * 				"16x16": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=16",
 * 				"32x32": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=32"
 * 			},
 * 			"displayName": "聂 孟义",
 * 			"active": true,
 * 			"timeZone": "PRC"
 * 		},
 * 		"subtasks": [],
 * 		"created": "2018-01-08T16:53:04.000+0800",
 * 		"reporter": {
 * 			"self": "http://jira.rrsjk.com/rest/api/2/user?username=niemy@haier.com",
 * 			"name": "niemy@haier.com",
 * 			"key": "niemy@haier.com",
 * 			"emailAddress": "niemy@haier.com",
 * 			"avatarUrls": {
 * 				"48x48": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=48",
 * 				"24x24": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=24",
 * 				"16x16": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=16",
 * 				"32x32": "https://www.gravatar.com/avatar/2865ae63478beeb46c906af962af6aeb?d=mm&s=32"
 * 			},
 * 			"displayName": "聂 孟义",
 * 			"active": true,
 * 			"timeZone": "PRC"
 * 		},
 * 		"customfield_10000": null,
 * 		"aggregateprogress": {
 * 			"progress": 0,
 * 			"total": 0
 * 		},
 * 		"priority": {
 * 			"self": "http://jira.rrsjk.com/rest/api/2/priority/1",
 * 			"iconUrl": "http://jira.rrsjk.com/images/icons/priorities/highest.svg",
 * 			"name": "Highest",
 * 			"id": "1"
 * 		},
 * 		"customfield_10100": "{summaryBean=com.atlassian.jira.plugin.devstatus.rest.SummaryBean@211a9f05[summary={pullrequest=com.atlassian.jira.plugin.devstatus.rest.SummaryItemBean@772e23a3[overall=com.atlassian.jira.plugin.devstatus.summary.beans.PullRequestOverallBean@442579c7[stateCount=0,state=OPEN,count=0,lastUpdated=<null>,lastUpdatedTimestamp=<null>],byInstanceType={}], build=com.atlassian.jira.plugin.devstatus.rest.SummaryItemBean@6e4c227[overall=com.atlassian.jira.plugin.devstatus.summary.beans.BuildOverallBean@73f39749[failedBuildCount=0,successfulBuildCount=0,unknownBuildCount=0,count=0,lastUpdated=<null>,lastUpdatedTimestamp=<null>],byInstanceType={}], review=com.atlassian.jira.plugin.devstatus.rest.SummaryItemBean@75c681af[overall=com.atlassian.jira.plugin.devstatus.summary.beans.ReviewsOverallBean@1a423591[stateCount=0,state=<null>,dueDate=<null>,overDue=false,count=0,lastUpdated=<null>,lastUpdatedTimestamp=<null>],byInstanceType={}], deployment-environment=com.atlassian.jira.plugin.devstatus.rest.SummaryItemBean@3a972904[overall=com.atlassian.jira.plugin.devstatus.summary.beans.DeploymentOverallBean@55b1a8c0[topEnvironments=[],showProjects=false,successfulCount=0,count=0,lastUpdated=<null>,lastUpdatedTimestamp=<null>],byInstanceType={}], repository=com.atlassian.jira.plugin.devstatus.rest.SummaryItemBean@14bb8020[overall=com.atlassian.jira.plugin.devstatus.summary.beans.CommitOverallBean@1fded19b[count=0,lastUpdated=<null>,lastUpdatedTimestamp=<null>],byInstanceType={}], branch=com.atlassian.jira.plugin.devstatus.rest.SummaryItemBean@1c02146d[overall=com.atlassian.jira.plugin.devstatus.summary.beans.BranchOverallBean@44ad5885[count=0,lastUpdated=<null>,lastUpdatedTimestamp=<null>],byInstanceType={}]},errors=[],configErrors=[]], devSummaryJson={\"cachedValue\":{\"errors\":[],\"configErrors\":[],\"summary\":{\"pullrequest\":{\"overall\":{\"count\":0,\"lastUpdated\":null,\"stateCount\":0,\"state\":\"OPEN\",\"open\":true},\"byInstanceType\":{}},\"build\":{\"overall\":{\"count\":0,\"lastUpdated\":null,\"failedBuildCount\":0,\"successfulBuildCount\":0,\"unknownBuildCount\":0},\"byInstanceType\":{}},\"review\":{\"overall\":{\"count\":0,\"lastUpdated\":null,\"stateCount\":0,\"state\":null,\"dueDate\":null,\"overDue\":false,\"completed\":false},\"byInstanceType\":{}},\"deployment-environment\":{\"overall\":{\"count\":0,\"lastUpdated\":null,\"topEnvironments\":[],\"showProjects\":false,\"successfulCount\":0},\"byInstanceType\":{}},\"repository\":{\"overall\":{\"count\":0,\"lastUpdated\":null},\"byInstanceType\":{}},\"branch\":{\"overall\":{\"count\":0,\"lastUpdated\":null},\"byInstanceType\":{}}}},\"isStale\":false}}",
 * 		"labels": [],
 * 		"customfield_10004": null,
 * 		"environment": null,
 * 		"timeestimate": null,
 * 		"aggregatetimeoriginalestimate": null,
 * 		"versions": [],
 * 		"duedate": "2018-01-09",
 * 		"progress": {
 * 			"progress": 0,
 * 			"total": 0
 * 		},
 * 		"comment": {
 * 			"comments": [{
 * 				"self": "http://jira.rrsjk.com/rest/api/2/issue/10600/comment/10402",
 * 				"id": "10402",
 * 				"author": {
 * 					"self": "http://jira.rrsjk.com/rest/api/2/user?username=wangxuzheng@haier.com",
 * 					"name": "wangxuzheng@haier.com",
 * 					"key": "wangxuzheng@haier.com",
 * 					"emailAddress": "wangxuzheng@haier.com",
 * 					"avatarUrls": {
 * 						"48x48": "http://jira.rrsjk.com/secure/useravatar?avatarId=10350",
 * 						"24x24": "http://jira.rrsjk.com/secure/useravatar?size=small&avatarId=10350",
 * 						"16x16": "http://jira.rrsjk.com/secure/useravatar?size=xsmall&avatarId=10350",
 * 						"32x32": "http://jira.rrsjk.com/secure/useravatar?size=medium&avatarId=10350"
 * 					},
 * 					"displayName": "王 旭政",
 * 					"active": true,
 * 					"timeZone": "PRC"
 * 				},
 * 				"body": "[~weiguorui@haier.com] 是否是滤芯管家发送的短信",
 * 				"updateAuthor": {
 * 					"self": "http://jira.rrsjk.com/rest/api/2/user?username=wangxuzheng@haier.com",
 * 					"name": "wangxuzheng@haier.com",
 * 					"key": "wangxuzheng@haier.com",
 * 					"emailAddress": "wangxuzheng@haier.com",
 * 					"avatarUrls": {
 * 						"48x48": "http://jira.rrsjk.com/secure/useravatar?avatarId=10350",
 * 						"24x24": "http://jira.rrsjk.com/secure/useravatar?size=small&avatarId=10350",
 * 						"16x16": "http://jira.rrsjk.com/secure/useravatar?size=xsmall&avatarId=10350",
 * 						"32x32": "http://jira.rrsjk.com/secure/useravatar?size=medium&avatarId=10350"
 * 					},
 * 					"displayName": "王 旭政",
 * 					"active": true,
 * 					"timeZone": "PRC"
 * 				},
 * 				"created": "2018-01-08T17:00:00.000+0800",
 * 				"updated": "2018-01-08T17:00:00.000+0800"
 * 			}, {
 * 				"self": "http://jira.rrsjk.com/rest/api/2/issue/10600/comment/10404",
 * 				"id": "10404",
 * 				"author": {
 * 					"self": "http://jira.rrsjk.com/rest/api/2/user?username=weiguorui@haier.com",
 * 					"name": "weiguorui@haier.com",
 * 					"key": "weiguorui@haier.com",
 * 					"emailAddress": "weiguorui@haier.com",
 * 					"avatarUrls": {
 * 						"48x48": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=48",
 * 						"24x24": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=24",
 * 						"16x16": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=16",
 * 						"32x32": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=32"
 * 					},
 * 					"displayName": "位 国锐",
 * 					"active": true,
 * 					"timeZone": "PRC"
 * 				},
 * 				"body": "获取网点联系人对象错误。根据安装单中的网点86码找到网点信息，从网点信息中找到对应的联系人的信息",
 * 				"updateAuthor": {
 * 					"self": "http://jira.rrsjk.com/rest/api/2/user?username=weiguorui@haier.com",
 * 					"name": "weiguorui@haier.com",
 * 					"key": "weiguorui@haier.com",
 * 					"emailAddress": "weiguorui@haier.com",
 * 					"avatarUrls": {
 * 						"48x48": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=48",
 * 						"24x24": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=24",
 * 						"16x16": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=16",
 * 						"32x32": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=32"
 * 					},
 * 					"displayName": "位 国锐",
 * 					"active": true,
 * 					"timeZone": "PRC"
 * 				},
 * 				"created": "2018-01-09T10:10:49.000+0800",
 * 				"updated": "2018-01-09T10:10:49.000+0800"
 * 			}],
 * 			"maxResults": 2,
 * 			"total": 2,
 * 			"startAt": 0
 * 		},
 * 		"issuelinks": [],
 * 		"votes": {
 * 			"self": "http://jira.rrsjk.com/rest/api/2/issue/HAIERSHUI-27/votes",
 * 			"votes": 0,
 * 			"hasVoted": false
 * 		},
 * 		"worklog": {
 * 			"startAt": 0,
 * 			"maxResults": 20,
 * 			"total": 0,
 * 			"worklogs": []
 * 		},
 * 		"assignee": {
 * 			"self": "http://jira.rrsjk.com/rest/api/2/user?username=weiguorui@haier.com",
 * 			"name": "weiguorui@haier.com",
 * 			"key": "weiguorui@haier.com",
 * 			"emailAddress": "weiguorui@haier.com",
 * 			"avatarUrls": {
 * 				"48x48": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=48",
 * 				"24x24": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=24",
 * 				"16x16": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=16",
 * 				"32x32": "https://www.gravatar.com/avatar/eb43d6e6a97505e93247a3ecb92bb100?d=mm&s=32"
 * 			},
 * 			"displayName": "位 国锐",
 * 			"active": true,
 * 			"timeZone": "PRC"
 * 		},
 * 		"updated": "2018-01-09T10:10:49.000+0800",
 * 		"status": {
 * 			"self": "http://jira.rrsjk.com/rest/api/2/status/10002",
 * 			"description": "",
 * 			"iconUrl": "http://jira.rrsjk.com/",
 * 			"name": "完成",
 * 			"id": "10002",
 * 			"statusCategory": {
 * 				"self": "http://jira.rrsjk.com/rest/api/2/statuscategory/3",
 * 				"id": 3,
 * 				"key": "done",
 * 				"colorName": "green",
 * 				"name": "完成"
 * 			}
 * 		}
 * 	}
 * }
 * </pre>
 * @author KevinWang
 *
 */
@Slf4j
public class JiraTest {
	String userName = "wangxuzheng@haier.com";
	String password = "111111";
	JiraClient jiraClient = new JiraClient("http://jira.rrsjk.com", userName, password);
	@Test
	@DisplayName("测试下连接jira")
	public void testGet() {
		String body = jiraClient.get("/rest/api/2/issue/HAIERSHUI-27");
		log.info("{}",body);
	}
	
	@Test
	public void testCreateIssue() throws IOException{
		Writer writer = new StringWriter();
		JsonWriter jsonWriter = new JsonWriter(writer);
		jsonWriter.beginObject()
			.name("fields").beginObject()
				.name("summary").value("something'\\''s wrong")
				.name("environment").value("environment")
				.name("description").value("description")
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
		System.out.println(writer);
		jsonWriter.close();
	}
}
