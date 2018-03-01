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
					<div class="col-md-5">端口：${monitor.port}</div>
				</div>
				<div class="row">
					<div class="col-md-5">用户名：${monitor.username!""}</div>
					<div class="col-md-5">密码：${monitor.password!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">主目录：${monitor.baseDir!""}</div>
					<div class="col-md-5">数据目录：${monitor.dataDir!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">类型：${monitor.server!""}</div>
					<div class="col-md-5">主机名称：${monitor.hostName!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">操作系统：${monitor.os!""}</div>
					<div class="col-md-5">版本：${monitor.version!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">编码：${monitor.encoding!""}</div>
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
					<div class="col-md-5">Uptime：${record.uptime!""}</div>
					<div class="col-md-5">Com_select：${record.comSelect!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">Aborted_clients：${record.abortedClients!""}</div>
					<div class="col-md-5">Aborted_connects：${record.abortedConnects!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">Threads_connected：${record.threadsConnected!""}</div>
					<div class="col-md-5">Threads_cached：${record.threadsCached!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">Threads_created：${record.threadsCreated!""}</div>
					<div class="col-md-5">Threads_running：${record.threadsRunning!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">Max_used_connections：${record.maxUsedConnections!""}</div>
					<div class="col-md-5">connections：${record.connections!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">Com_insert：${record.comInsert!""}</div>
					<div class="col-md-5">Com_delete：${record.comDelete!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">Com_update：${record.comUpdate!""}</div>
					<div class="col-md-5">Com_select：${record.comSelect!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">tableLocksWaited：${record.tableLocksWaited!""}</div>
					<div class="col-md-5">tableLocksImmediate：${record.tableLocksImmediate!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">Com_commit：${record.comCommit!""}</div>
					<div class="col-md-5">Com_rollback：${record.comRollback!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">Queries：${record.queries!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">TPS：${(record.comCommit+record.comRollback)/record.uptime}</div>
					<div class="col-md-5">QPS：${record.queries/record.uptime}</div>
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