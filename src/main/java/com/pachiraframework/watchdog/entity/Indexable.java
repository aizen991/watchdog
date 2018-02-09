package com.pachiraframework.watchdog.entity;

import java.util.Date;

public interface Indexable {
	public Date getTimestamp();
	public void setTimestamp(Date timestamp);
	public String getId();
	public void setId(String id);
}
