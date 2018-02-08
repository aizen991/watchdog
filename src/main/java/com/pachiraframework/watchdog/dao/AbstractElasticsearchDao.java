package com.pachiraframework.watchdog.dao;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.pachiraframework.watchdog.entity.Indexable;

/**
 * @author wangxuzheng
 *
 */
public abstract class AbstractElasticsearchDao {
	@Autowired
	private TransportClient transportClient;
	/**
	 * 索引名称
	 * @return
	 */
	protected abstract String indexName();
	
	/**
	 * 索引类型
	 * @return
	 */
	protected abstract String indexType();
	
	public void insert(Indexable pingRecord) {
		Gson gson = createGson();
		String json = gson.toJson(pingRecord);
		IndexResponse response = this.transportClient.prepareIndex(indexName(), indexType())
				.setSource(json, XContentType.JSON).get();
		String id = response.getId();
		pingRecord.setId(id);
	}
	private Gson createGson() {
		return new Gson();
	}
}
