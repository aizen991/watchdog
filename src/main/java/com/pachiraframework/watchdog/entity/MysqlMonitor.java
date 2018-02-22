package com.pachiraframework.watchdog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wangxuzheng
 *
 */
/**
 * @author wangxuzheng
 *
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MysqlMonitor extends Monitor {
	private static final long serialVersionUID = 3684121193752640323L;
	private String host;
	private Integer port;
	private String username;
	private String password;
	private String baseDir;
	private String dataDir;
	private String hostName;
	private String os;
	private String version;
	/**
	 * @see ServerEnum
	 */
	private String server;
	private String encoding;

	/**
	 * 数据库类型
	 * 
	 * @author wangxuzheng
	 *
	 */
	public static enum ServerEnum {
		/**
		 * master
		 */
		MASTER,
		/**
		 * slave
		 */
		SLAVE,
		/**
		 * 独立服务器
		 */
		STANDALONE;
	}
}
