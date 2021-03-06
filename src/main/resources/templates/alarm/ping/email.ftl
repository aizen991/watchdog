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
					<div class="col-md-5">主机：${monitor.host}</div>
					<div class="col-md-5">调度器：${monitor.schedulerId}</div>
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
					<div class="col-md-5">发送次数：${record.sent}</div>
					<div class="col-md-5">成功次数：${record.success}</div>
				</div>
				<div class="row">
					<div class="col-md-5">失败次数：${record.lost}</div>
					<div class="col-md-5">最大响应时间：${record.max}</div>
				</div>
				<div class="row">
					<div class="col-md-5">最短响应时间：${record.min}</div>
					<div class="col-md-5">平均响应时间：${record.avg}</div>
				</div>
				<div class="row">
					<div class="col-md-10">消息：${record.message!""}</div>
				</div>
			</div>
		</div>
		<#include "/alarm/common/email_reports.ftl">
	</div>
  </body>
</html>