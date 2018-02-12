package com.pachiraframework.watchdog.inspect;

class ShouldContainStrategy extends ContentCheckerStrategy {
	public ShouldContainStrategy(String content) {
		super(content);
	}

	@Override
	public boolean match(String config) {
		return content.contains(config);
	}
}
