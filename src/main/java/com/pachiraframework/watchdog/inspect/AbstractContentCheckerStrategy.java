package com.pachiraframework.watchdog.inspect;

/**
 * http检查返回内容匹配
 * @author wangxuzheng@aliyun.com
 *
 */
abstract class AbstractContentCheckerStrategy {
	protected String content;
	public AbstractContentCheckerStrategy(String content){
		this.content = content;
	}
	/**
	 * 是否匹配
	 * @param config
	 * @return
	 */
	public abstract boolean match(String config);
}
