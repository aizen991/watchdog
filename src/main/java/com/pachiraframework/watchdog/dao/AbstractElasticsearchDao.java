package com.pachiraframework.watchdog.dao;

import java.beans.PropertyDescriptor;
import java.util.List;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.watchdog.entity.Indexable;

import lombok.Getter;
import lombok.SneakyThrows;

/**
 * @author wangxuzheng
 *
 */
public abstract class AbstractElasticsearchDao {
	protected static final String INDEX_PREFIX = "watchdog_";
	@Getter
	@Autowired
	private TransportClient transportClient;

	private String indexNameType() {
		return INDEX_PREFIX + index();
	}

	/**
	 * 索引类型
	 * 
	 * @return
	 */
	protected abstract String index();

	public void insert(Indexable indexAble) {
		String json = bean2Json(indexAble);
		IndexResponse response = transportClient.prepareIndex(indexNameType(), indexNameType())
				.setSource(json, XContentType.JSON).get();
		String id = response.getId();
		indexAble.setId(id);
	}

	/**
	 * 批量插入
	 * 
	 * @param reports
	 */
	public void batchInsert(List<? extends Indexable> reports) {
		BulkRequestBuilder bulkRequest = transportClient.prepareBulk();
		for (Indexable report : reports) {
			String json = bean2Json(report);
			bulkRequest.add(
					transportClient.prepareIndex(indexNameType(), indexNameType()).setSource(json, XContentType.JSON));
		}
		BulkResponse response = bulkRequest.execute().actionGet();
		BulkItemResponse[] items = response.getItems();
		for (int i = 0; i < items.length; i++) {
			BulkItemResponse item = items[i];
			Indexable report = reports.get(i);
			report.setId(item.getResponse().getId());
		}
	}

	@SneakyThrows
	protected String bean2Json(Object indexAble) {
		XContentBuilder builder = XContentFactory.jsonBuilder();
		builder.startObject();
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(indexAble.getClass());
		for (PropertyDescriptor descriptor : propertyDescriptors) {
			if (!descriptor.getName().equals("class")) {
				builder.field(descriptor.getName(), descriptor.getReadMethod().invoke(indexAble));
			}
		}
		builder.endObject();
		String json = builder.string();
		return json;
	}
}
