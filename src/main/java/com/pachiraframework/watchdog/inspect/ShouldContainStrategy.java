package com.pachiraframework.watchdog.inspect;

class ShouldContainStrategy extends AbstractContentCheckerStrategy {
	public ShouldContainStrategy(String content) {
		super(content);
	}

	@Override
	public boolean match(String config) {
		return content.contains(config);
	}
}
