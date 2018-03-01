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
					<div class="col-md-5">pid：${record.pid!""}</div>
					<div class="col-md-5">uptime：${record.uptime!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">time：${record.time!""}</div>
					<div class="col-md-5">version：${record.version!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">libevent：${record.libevent!""}</div>
					<div class="col-md-5">pointer_size：${record.pointerSize!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">rusage_user：${record.rusageUser!""}</div>
					<div class="col-md-5">rusage_system：${record.rusageSystem!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">curr_connections：${record.currConnections!""}</div>
					<div class="col-md-5">total_connections：${record.totalConnections!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">connection_structures：${record.connectionStructures!""}</div>
					<div class="col-md-5">reserved_fds：${record.reservedFds!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">cmd_get：${record.cmdGet!""}</div>
					<div class="col-md-5">cmd_set：${record.cmdSet!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">cmd_flush：${record.cmdFlush!""}</div>
					<div class="col-md-5">cmd_touch：${record.cmdTouch!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">get_hits：${record.getHits!""}</div>
					<div class="col-md-5">get_misses：${record.getMisses!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">delete_misses：${record.deleteMisses!""}</div>
					<div class="col-md-5">delete_hits：${record.deleteHits!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">incr_misses：${record.incrMisses!""}</div>
					<div class="col-md-5">incr_hits：${record.incrHits!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">decr_misses：${record.decrMisses!""}</div>
					<div class="col-md-5">decr_hits：${record.decrHits!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">cas_misses：${record.casMisses!""}</div>
					<div class="col-md-5">cas_hits：${record.casHits!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">cas_badval：${record.casBadval!""}</div>
					<div class="col-md-5">touch_hits：${record.touchHits!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">touch_misses：${record.touchMisses!""}</div>
					<div class="col-md-5">auth_cmds：${record.authCmds!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">auth_errors：${record.authErrors!""}</div>
					<div class="col-md-5">bytes_read：${record.bytesRead!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">bytes_written：${record.bytesWritten!""}</div>
					<div class="col-md-5">limit_maxbytes：${record.limitMaxbytes!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">accepting_conns：${record.acceptingConns!""}</div>
					<div class="col-md-5">listen_disabled_num：${record.listenDisabledNum!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">threads：${record.threads!""}</div>
					<div class="col-md-5">conn_yields：${record.connYields!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">hash_power_level：${record.hashPowerLevel!""}</div>
					<div class="col-md-5">hash_bytes：${record.hashBytes!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">hash_is_expanding：${record.hashIsExpanding!""}</div>
					<div class="col-md-5">expired_unfetched：${record.expiredUnfetched!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">evicted_unfetched：${record.evictedUnfetched!""}</div>
					<div class="col-md-5">bytes：${record.bytes!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">curr_items：${record.currItems!""}</div>
					<div class="col-md-5">total_items：${record.totalItems!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">evictions：${record.evictions!""}</div>
					<div class="col-md-5">reclaimed：${record.reclaimed!""}</div>
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