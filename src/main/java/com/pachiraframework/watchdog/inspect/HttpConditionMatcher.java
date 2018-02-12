package com.pachiraframework.watchdog.inspect;

import java.util.HashMap;
import java.util.Map;

import com.pachiraframework.watchdog.entity.HttpMonitor.HttpConditionEnum;

/**
 * @author wangxuzheng
 *
 */
interface HttpConditionMatcher {
	public boolean match(int code,int target);
	public Map<HttpConditionEnum, HttpConditionMatcher> MATCHER_MAP = new HashMap<HttpConditionEnum, HttpConditionMatcher>(){
		private static final long serialVersionUID = 1L;
		{
			put(HttpConditionEnum.LT, new LTHttpConditionMatcher());
			put(HttpConditionEnum.GT, new GTHttpConditionMatcher());
			put(HttpConditionEnum.EQ, new EQHttpConditionMatcher());
			put(HttpConditionEnum.NE, new NEHttpConditionMatcher());
			put(HttpConditionEnum.LE, new LEHttpConditionMatcher());
			put(HttpConditionEnum.GE, new GEHttpConditionMatcher());
		}
	};
	
	//LT,GT,EQ,NE,LE,GE
	static class LTHttpConditionMatcher implements HttpConditionMatcher{
		@Override
		public boolean match(int code,int target) {
			return code < target;
		}
	}
	
	static class GTHttpConditionMatcher implements HttpConditionMatcher{
		@Override
		public boolean match(int code,int target) {
			return code > target;
		}
	}
	static class EQHttpConditionMatcher implements HttpConditionMatcher{
		@Override
		public boolean match(int code,int target) {
			return code == target;
		}
	}
	static class NEHttpConditionMatcher implements HttpConditionMatcher{
		@Override
		public boolean match(int code,int target) {
			return code != target;
		}
	}
	static class LEHttpConditionMatcher implements HttpConditionMatcher{
		@Override
		public boolean match(int code,int target) {
			return code <= target;
		}
	}
	static class GEHttpConditionMatcher implements HttpConditionMatcher{
		@Override
		public boolean match(int code,int target) {
			return code >= target;
		}
	}
}
