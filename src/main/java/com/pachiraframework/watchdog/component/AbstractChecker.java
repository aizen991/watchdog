package com.pachiraframework.watchdog.component;

/**
 * 监控器检查
 * @author wangxuzheng
 *
 */
public abstract class AbstractChecker {
	/**
	 * 批量抓去数据的
	 */
	protected static final Integer BATCH_FETCH_SIZE = 20;
	/**
	 * 执行检查
	 * @param interval 检查的间隔
	 */
	public abstract void check(Integer interval);
}
