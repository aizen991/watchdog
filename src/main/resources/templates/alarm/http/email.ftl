<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<#include "/alarm/common/email_css.ftl">
  </head>
  <body>
    <div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">监控器信息</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-5">名称：<a href="${monitor.id}"/>${monitor.name}</a></div>
					<div class="col-md-5">类型：${monitor.type}</div>
				</div>
				<div class="row">
					<div class="col-md-5">链接地址：${monitor.url}</div>
					<div class="col-md-5">方法：${monitor.method}</div>
				</div>
				<div class="row">
					<div class="col-md-5">请求参数：${monitor.requestParams!""}</div>
					<div class="col-md-5">用户：${monitor.userid!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">密码：${monitor.password!""}</div>
					<div class="col-md-5">Agent：${monitor.userAgent!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">间隔：${monitor.pollingInterval}</div>
					<div class="col-md-5">请求头：${monitor.httpHeader!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">应该包含：${monitor.shouldContain!""}</div>
					<div class="col-md-5">不该包含：${monitor.shouldNotContain!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">大小写敏感：${monitor.caseSensitive!""}</div>
				</div>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">检查结果</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-5">检查ID：${record.id}</div>
					<div class="col-md-5">检查时间：${record.timestamp?string("yyyy-MM-dd HH:mm:ss")}</div>
				</div>
				<div class="row">
					<div class="col-md-5">响应时间：${record.responseTime}(毫秒)</div>
					<div class="col-md-5">消息：${record.message!""}</div>
				</div>
				<div class="row">
					<div class="col-md-10">响应内容：</div>
				</div>
				<div class="row">
					<div class="col-md-10">${record.message!""}</div>
				</div>
				<div class="row">
					
				</div>
			</div>
		</div>
		<#include "/alarm/common/email_reports.ftl">
	</div>
  </body>
</html>