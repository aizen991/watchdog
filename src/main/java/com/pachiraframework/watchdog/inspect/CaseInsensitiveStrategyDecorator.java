package com.pachiraframework.watchdog.inspect;

import com.google.common.base.Strings;

/**
 * 大小写不敏感匹配
 * @author Administrator
 *
 */
public class CaseInsensitiveStrategyDecorator extends ContentCheckerStrategy {
	private ContentCheckerStrategy strategy;
	public CaseInsensitiveStrategyDecorator(ContentCheckerStrategy strategy) {
		super(strategy.content);
		this.strategy = strategy;
	}
	
	@Override
	public boolean match(String config) {
		if(Strings.isNullOrEmpty(content)){
			return false;
		}
		if(!Strings.isNullOrEmpty(config)){
			this.content = this.content.toLowerCase();
			return strategy.match(config.toLowerCase());
		}
		return strategy.match(config);
	}

}
