package com.pachiraframework.watchdog.action;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.MetricReport.StatusEnum;
import com.pachiraframework.watchdog.event.event.MetricReportEvent;

import lombok.Getter;

/**
 * 加了发送时间限制的action组件，如检查每分钟执行一次，但是发送告警邮件10分钟一次，以免邮件撑爆
 * @author wangxuzheng
 *
 */
public abstract class LimitedAction extends AbstractAction {
	@Getter
	private Cache<String, Long> cache = buildCache();
	protected Cache<String, Long> buildCache(){
		Cache<String, Long> cache = CacheBuilder.newBuilder().initialCapacity(100).concurrencyLevel(5).maximumSize(10000).expireAfterWrite(duration(), TimeUnit.MINUTES).build();
		return cache;
	}
	@Override
	public void execute(MetricReportEvent context){
		boolean needExecute = needExecute(context);
		if(needExecute) {
			super.execute(context);
		}
	}
	
	/**
	 * 是否需要执行，默认实现：状态为UP或者CLEAR的不需要执行后续逻辑
	 * @param context
	 * @return
	 */
	protected boolean needExecute(MetricReportEvent context) {
		boolean needExecute = false;
		for(MetricReport report : context.getReports()) {
			StatusEnum status = StatusEnum.valueOf(report.getStatus());
			if(StatusEnum.UP.equals(status)|| StatusEnum.CLEAR.equals(status)) {
				continue;
			}
			String key = report.getMetric()+":"+report.getMoitorId();
			Long last = cache.getIfPresent(key);
			if(last == null) {
				cache.put(key, System.currentTimeMillis());
				needExecute = true;
				break;
			}
		}
		return needExecute;
	}
	/**
	 * 发送间隔分钟数，默认20分钟
	 * @return
	 */
	protected int duration() {
		return 20;
	}

}
