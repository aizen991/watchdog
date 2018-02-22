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
@ToString(callSuper = true)
public class HttpMonitor extends Monitor {
	private static final long serialVersionUID = -3642660116581605407L;
	private String url;
	/**
	 * 秒
	 */
	private Integer timeout;
	private String method;
	private String requestParams;
	private String userid;
	private String password;
	private String userAgent;
	private String httpHeader;
	private String shouldContain;
	private String shouldNotContain;
	private String caseSensitive;

	public static enum MethodEnum {
		/**
		 * GET方法
		 */
		GET,
		/**
		 * POST方法
		 */
		POST;
		public static MethodEnum of(String condition) {
			Optional<MethodEnum> optional = Enums.getIfPresent(MethodEnum.class, condition);
			return optional.get();
		}
	}

	public static enum CaseSensitiveEnum {
		/**
		 * 大消息敏感
		 */
		YES,
		/**
		 * 大小写不敏感
		 */
		NO;
		public static CaseSensitiveEnum of(String condition) {
			Optional<CaseSensitiveEnum> optional = Enums.getIfPresent(CaseSensitiveEnum.class, condition);
			return optional.get();
		}
	}
}
