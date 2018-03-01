package com.pachiraframework.watchdog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wangxuzheng@aliyun.com
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class MysqlRecord extends AbstractRecord {
	private String host;
	/**
	 * 当前打开的连接的数量。
	 */
	private Integer threadsConnected;
	/**
	 * 线程缓存内的线程的数量。
	 */
	private Integer threadsCached;
	/**
	 * 创建用来处理连接的线程数。如果Threads_created较大，你可能要增加thread_cache_size值。缓存访问率的计算方法Threads_created/Connections。
	 */
	private Integer threadsCreated;
	/**
	 * 激活的（非睡眠状态）线程数。
	 */
	private Integer threadsRunning;
	/**
	 * 服务器启动后已经同时使用的连接的最大数量。
	 */
	private Integer maxUsedConnections;
	/**
	 * 由于客户端没有正确关闭连接导致客户端终止而中断的连接数
	 */
	private Integer abortedClients;
	/**
	 * 试图连接到MySQL服务器而失败的连接数
	 */
	private Integer abortedConnects;
	/**
	 * 试图连接到(不管是否成功)MySQL服务器的连接数
	 */
	private Integer connections;
	private Integer comInsert;
	private Integer comDelete;
	private Integer comUpdate;
	private Integer comSelect;
	/**
	 *  服务器已经运行的时间（以秒为单位）。
	 */
	private Integer uptime;
	/**
	 * 表示不能立即获取锁的次数
	 */
	private Integer tableLocksWaited;
	/**
	 * 表示可以立即获取锁的查询次数
	 */
	private Integer tableLocksImmediate;
	private Integer comCommit;
	private Integer comRollback;
	private Integer queries;
}
