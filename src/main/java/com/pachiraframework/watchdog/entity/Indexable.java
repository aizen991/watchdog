package com.pachiraframework.watchdog.entity;

import java.util.Date;

/**
 * 可以被索引的对象
 * @author wangxuzheng
 *
 */
public interface Indexable {
	/**
	 * 时间戳，生成索引的时间
	 * @return
	 */
	public Date getTimestamp();
	/**
	 * 设置时间戳
	 * @param timestamp
	 */
	public void setTimestamp(Date timestamp);
	/**
	 * 返回索引ID
	 * @return
	 */
	public String getId();
	/**
	 * 设置索引ID
	 * @param id
	 */
	public void setId(String id);
}
