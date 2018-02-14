		<div class="panel panel-primary">
			<div class="panel-heading">监控报告</div>
			<div class="panel-body">
				<#list reports as report>
				<#assign statusTextClassMap={"UP":"text-success","DOWN":"text-danger","CLEAR":"text-success","WARNING":"text-warning","CRITICAL":"text-danger"}>
				<div class="row">
					<p class="${statusTextClassMap[report.status]}">监控指标【${report.metric}】:</p>
				</div>
				<div class="row">
					<div class="col-md-5">报告ID：${report.id!""}</div>
					<div class="col-md-5">报告时间：${report.timestamp?string("yyyy-MM-dd HH:mm:ss")}</div>
				</div>
				<div class="row">
					<div class="col-md-5">监控指标：${report.metric}</div>
					<div class="col-md-5">错误级别：${report.status}</div>
				</div>
				<div class="row">
					<div class="col-md-10">消息：${report.message!""}</div>
				</div>
				</#list>
			</div>
		</div>