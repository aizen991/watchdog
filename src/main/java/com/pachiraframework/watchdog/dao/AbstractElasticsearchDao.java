package com.pachiraframework.watchdog.dao;

import java.util.List;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.pachiraframework.watchdog.entity.Indexable;

import lombok.Getter;

/**
 * @author wangxuzheng
 *
 */
public abstract class AbstractElasticsearchDao {
	protected static final String INDEX_NAME = "watchdog";
	@Getter
	@Autowired
	private TransportClient transportClient;

	/**
	 * 索引名称
	 * 
	 * @return
	 */
	protected String indexName() {
		return INDEX_NAME;
	}

	/**
	 * 索引类型
	 * 
	 * @return
	 */
	protected abstract String indexType();

	public void insert(Indexable pingRecord) {
		Gson gson = createGson();
		String json = gson.toJson(pingRecord);
		IndexResponse response = transportClient.prepareIndex(indexName(), indexType())
				.setSource(json, XContentType.JSON).get();
		String id = response.getId();
		pingRecord.setId(id);
	}

	/**
	 * 批量插入
	 * 
	 * @param reports
	 */
	public void batchInsert(List<? extends Indexable> reports) {
		Gson gson = createGson();
		BulkRequestBuilder bulkRequest = transportClient.prepareBulk();
		for (Indexable report : reports) {
			String json = gson.toJson(report);
			bulkRequest.add(transportClient.prepareIndex(indexName(), indexType()).setSource(json, XContentType.JSON));
		}
		BulkResponse response = bulkRequest.execute().actionGet();
		BulkItemResponse[] items = response.getItems();
		for (int i = 0; i < items.length; i++) {
			BulkItemResponse item = items[i];
			Indexable report = reports.get(i);
			report.setId(item.getId());
		}
	}

	protected Gson createGson() {
		return new Gson();
	}
}
