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
@ToString(callSuper=true)
public class MySQLMonitor extends Monitor {
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
	 * 数据库名称
	 */
	private String instance;
}
