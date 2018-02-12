package com.pachiraframework.watchdog.entity;

import com.google.common.base.Enums;
import com.google.common.base.Optional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wangxuzheng
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class HttpMonitor extends Monitor {
	private static final long serialVersionUID = -3642660116581605407L;
	private String url;
	private Integer timeout;//秒
	private String method;
	private String requestParams;
	private String userid;
	private String password;
	private String httpCondition;
	private Integer httpValue;
	private String userAgent;
	private String httpHeader;
	private String shouldContain;
	private String shouldNotContain;
	private String caseSensitive;
	public static enum MethodEnum{
		GET,POST,HEAD,PUT,DELETE;
		public static MethodEnum of(String condition){
			Optional<MethodEnum> optional = Enums.getIfPresent(MethodEnum.class, condition);
			return optional.get();
		}
	}
	
	public static enum CaseSensitiveEnum{
		YES,NO;
		public static CaseSensitiveEnum of(String condition){
			Optional<CaseSensitiveEnum> optional = Enums.getIfPresent(CaseSensitiveEnum.class, condition);
			return optional.get();
		}
	}
	
	public static enum HttpConditionEnum{
		LT,GT,EQ,NE,LE,GE;
		public static HttpConditionEnum of(String condition){
			Optional<HttpConditionEnum> optional = Enums.getIfPresent(HttpConditionEnum.class, condition);
			return optional.get();
		}
	}
}
