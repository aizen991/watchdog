package com.pachiraframework.watchdog.service.impl;

import java.beans.PropertyDescriptor;
import java.util.Date;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Maps;
import com.pachiraframework.watchdog.entity.PingRecord;

import lombok.SneakyThrows;

public class JsonTest {
	@Test
	@SneakyThrows
	public void bean2Json() {
		PingRecord indexAble = new PingRecord();
		indexAble.setAvg(20d);
		indexAble.setId("AAAAAA");
		indexAble.setLost(2);
		indexAble.setMax(30D);
		indexAble.setMessage("ok");
		indexAble.setMin(5D);
		indexAble.setMonitorId(1234L);
		indexAble.setSent(10);
		indexAble.setSuccess(15);
		indexAble.setTimestamp(new Date());
		XContentBuilder builder = XContentFactory.jsonBuilder();
		builder.startObject();
		// .field("user", "kimchy")
		// .field("postDate", new Date())
		// .field("message", "trying out Elasticsearch")
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(indexAble.getClass());
		for (PropertyDescriptor descriptor : propertyDescriptors) {
			if (!descriptor.getName().equals("class")) {
				builder.field(descriptor.getName(), descriptor.getReadMethod().invoke(indexAble));
			}
		}
		builder.endObject();
		String json = builder.string();
		System.out.println(json);
	}
}
