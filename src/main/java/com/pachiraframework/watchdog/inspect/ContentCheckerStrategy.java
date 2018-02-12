package com.pachiraframework.watchdog.inspect;

/**
 * http检查返回内容匹配
 * @author wangxuzheng@aliyun.com
 *
 */
abstract class ContentCheckerStrategy {
	protected String content;
	public ContentCheckerStrategy(String content){
		this.content = content;
	}
	public abstract boolean match(String config);
}
