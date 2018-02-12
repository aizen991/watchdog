package com.pachiraframework.watchdog.inspect;

import com.google.common.base.Strings;

class ShouldNotContainStrategy extends ContentCheckerStrategy {
	public ShouldNotContainStrategy(String content) {
		super(content);
	}

	@Override
	public boolean match(String config) {
		if(Strings.isNullOrEmpty(content)){
			return true;
		}
		return !content.contains(config);
	}

}
