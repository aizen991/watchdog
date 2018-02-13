package com.pachiraframework.watchdog.entity;

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
public class MemcachedRecord extends AbstractRecord {
	/**
	 * memcache服务器进程ID
	 */
	private String pid;
	/**
	 * 服务器已运行秒数
	 */
	private Long uptime;
	/**
	 * 服务器当前Unix时间戳
	 */
	private Long time;
	/**
	 * memcache版本
	 */
	private String version;
	private String libevent;
	/**
	 * 操作系统指针大小
	 */
	private Integer pointerSize;
	/**
	 * 进程累计用户时间
	 */
	private Double rusageUser;
	/**
	 * 进程累计系统时间
	 */
	private Double rusageSystem;
	/**
	 * 当前连接数量
	 */
	private Integer currConnections;
	/**
	 * Memcached运行以来连接总数
	 */
	private Integer totalConnections;
	/**
	 * Memcached分配的连接结构数量
	 */
	private Integer connectionStructures;
	private Integer reservedFds;
	/**
	 * get命令请求次数
	 */
	private Long cmdGet;
	/**
	 * set命令请求次数
	 */
	private Long cmdSet;
	/**
	 * flush命令请求次数
	 */
	private Long cmdFlush;
	private Long cmdTouch;
	/**
	 * get命令命中次数
	 */
	private Long getHits;
	/**
	 * get命令未命中次数
	 */
	private Long getMisses;
	/**
	 * delete命令未命中次数
	 */
	private Long deleteMisses;
	/**
	 * delete命令命中次数
	 */
	private Long deleteHits;
	/**
	 * incr命令未命中次数
	 */
	private Long incrMisses;
	/**
	 * incr命令命中次数
	 */
	private Long incrHits;
	/**
	 * decr命令未命中次数
	 */
	private Long decrMisses;
	/**
	 * decr命令命中次数
	 */
	private Long decrHits;
	/**
	 * cas命令未命中次数
	 */
	private Long casMisses;
	/**
	 * cas命令命中次数
	 */
	private Long casHits;
	/**
	 * 使用擦拭次数
	 */
	private Long casBadval;
	private Long touchHits;
	private Long touchMisses;
	/**
	 * 认证命令处理的次数
	 */
	private Long authCmds;
	/**
	 * 认证失败数目
	 */
	private Long authErrors;
	/**
	 * 读取总字节数
	 */
	private Long bytesRead;
	/**
	 * 发送总字节数
	 */
	private Long bytesWritten;
	/**
	 * 分配的内存总大小（字节）
	 */
	private Long limitMaxbytes;
	/**
	 * 服务器是否达到过最大连接（0/1）
	 */
	private Integer acceptingConns;
	/**
	 * 失效的监听数
	 */
	private Integer listenDisabledNum;
	/**
	 * 当前线程数
	 */
	private Integer threads;
	/**
	 * 连接操作主动放弃数目
	 */
	private Integer connYields;
	private Integer hashPowerLevel;
	private Long hashBytes;
	private Integer hashIsExpanding;
	private Integer expiredUnfetched;
	private Integer evictedUnfetched;
	/**
	 * 当前存储占用的字节数
	 */
	private Long bytes;
	/**
	 * 当前存储的数据总数
	 */
	private Long currItems;
	/**
	 * 启动以来存储的数据总数
	 */
	private Long totalItems;
	/**
	 * LRU释放的对象数目
	 */
	private Long evictions;
	/**
	 * 已过期的数据条目来存储新数据的数目
	 */
	private Long reclaimed;
}
