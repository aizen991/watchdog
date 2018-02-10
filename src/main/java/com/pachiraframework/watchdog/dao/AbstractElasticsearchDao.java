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
import com.google.gson.GsonBuilder;
import com.pachiraframework.watchdog.entity.Indexable;

import lombok.Getter;

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

	public void insert(Indexable pingRecord) {
		Gson gson = createGson();
		String json = gson.toJson(pingRecord);
		IndexResponse response = transportClient.prepareIndex(indexNameType(), indexNameType())
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
			bulkRequest.add(transportClient.prepareIndex(indexNameType(), indexNameType()).setSource(json, XContentType.JSON));
		}
		BulkResponse response = bulkRequest.execute().actionGet();
		BulkItemResponse[] items = response.getItems();
		for (int i = 0; i < items.length; i++) {
			BulkItemResponse item = items[i];
			Indexable report = reports.get(i);
			report.setId(item.getResponse().getId());
		}
	}

	protected Gson createGson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();   
		return gson;
	}
}
