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
					<div class="col-md-5">间隔：${monitor.pollingInterval}</div>
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
					<div class="col-md-5">redisVersion：${record.redisVersion!""}</div>
					<div class="col-md-5">redisGitSha1：${record.redisGitSha1!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">redisGitDirty：${record.redisGitDirty!""}</div>
					<div class="col-md-5">redisBuildId：${record.redisBuildId!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">redisMode：${record.redisMode!""}</div>
					<div class="col-md-5">os：${record.os!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">archBits：${record.archBits!""}</div>
					<div class="col-md-5">multiplexingApi：${record.multiplexingApi!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">gccVersion：${record.gccVersion!""}</div>
					<div class="col-md-5">processId：${record.processId!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">runId：${record.runId!""}</div>
					<div class="col-md-5">tcpPort：${record.tcpPort!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">uptimeInSeconds：${record.uptimeInSeconds!""}</div>
					<div class="col-md-5">uptimeInDays：${record.uptimeInDays!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">hz：${record.hz!""}</div>
					<div class="col-md-5">lruClock：${record.lruClock!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">configFile：${record.configFile!""}</div>
					<div class="col-md-5">connectedClients：${record.connectedClients!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">clientLongestOutputList：${record.clientLongestOutputList!""}</div>
					<div class="col-md-5">clientBiggestInputBuf：${record.clientBiggestInputBuf!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">blockedClients：${record.blockedClients!""}</div>
					<div class="col-md-5">usedMemory：${record.usedMemory!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">usedMemoryHuman：${record.usedMemoryHuman!""}</div>
					<div class="col-md-5">usedMemoryRss：${record.usedMemoryRss!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">usedMemoryPeak：${record.usedMemoryPeak!""}</div>
					<div class="col-md-5">usedMemoryPeakHuman：${record.usedMemoryPeakHuman!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">usedMemoryLua：${record.usedMemoryLua!""}</div>
					<div class="col-md-5">memFragmentationRatio：${record.memFragmentationRatio!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">memAllocator：${record.memAllocator!""}</div>
					<div class="col-md-5">loading：${record.loading!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">rdbChangesSinceLastSave：${record.rdbChangesSinceLastSave!""}</div>
					<div class="col-md-5">rdbBgsaveInProgress：${record.rdbBgsaveInProgress!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">rdbLastSaveTime：${record.rdbLastSaveTime!""}</div>
					<div class="col-md-5">rdbLastBgsaveStatus：${record.rdbLastBgsaveStatus!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">rdbLastBgsaveTimeSec：${record.rdbLastBgsaveTimeSec!""}</div>
					<div class="col-md-5">rdbCurrentBgsaveTimeSec：${record.rdbCurrentBgsaveTimeSec!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">aofEnabled：${record.aofEnabled!""}</div>
					<div class="col-md-5">aofRewriteInProgress：${record.aofRewriteInProgress!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">aofRewriteScheduled：${record.aofRewriteScheduled!""}</div>
					<div class="col-md-5">aofLastRewriteTimeSec：${record.aofLastRewriteTimeSec!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">aofCurrentRewriteTimeSec：${record.aofCurrentRewriteTimeSec!""}</div>
					<div class="col-md-5">aofLastBgrewriteStatus：${record.aofLastBgrewriteStatus!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">aofLastWriteStatus：${record.aofLastWriteStatus!""}</div>
					<div class="col-md-5">totalConnectionsReceived：${record.totalConnectionsReceived!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">totalCommandsProcessed：${record.totalCommandsProcessed!""}</div>
					<div class="col-md-5">instantaneousOpsPerSec：${record.instantaneousOpsPerSec!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">rejectedConnections：${record.rejectedConnections!""}</div>
					<div class="col-md-5">syncFull：${record.syncFull!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">syncPartialOk：${record.syncPartialOk!""}</div>
					<div class="col-md-5">syncPartialErr：${record.syncPartialErr!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">expiredKeys：${record.expiredKeys!""}</div>
					<div class="col-md-5">evictedKeys：${record.evictedKeys!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">keyspaceHits：${record.keyspaceHits!""}</div>
					<div class="col-md-5">keyspaceMisses：${record.keyspaceMisses!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">pubsubChannels：${record.pubsubChannels!""}</div>
					<div class="col-md-5">pubsubPatterns：${record.pubsubPatterns!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">latestForkUsec：${record.latestForkUsec!""}</div>
					<div class="col-md-5">role：${record.role!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">connectedSlaves：${record.connectedSlaves!""}</div>
					<div class="col-md-5">masterReplOffset：${record.masterReplOffset!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">replBacklogActive：${record.replBacklogActive!""}</div>
					<div class="col-md-5">replBacklogSize：${record.replBacklogSize!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">replBacklogFirstByteOffset：${record.replBacklogFirstByteOffset!""}</div>
					<div class="col-md-5">replBacklogBistlen：${record.replBacklogBistlen!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">usedCpuSys：${record.usedCpuSys!""}</div>
					<div class="col-md-5">usedCpuUser：${record.usedCpuUser!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">usedCpuSysChildren：${record.usedCpuSysChildren!""}</div>
					<div class="col-md-5">usedCpuUserChildren：${record.usedCpuUserChildren!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">db0：${record.db0!""}</div>
					<div class="col-md-5">db1：${record.db1!""}</div>
				</div>
				<div class="row">
					<div class="col-md-5">db3：${record.db3!""}</div>
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