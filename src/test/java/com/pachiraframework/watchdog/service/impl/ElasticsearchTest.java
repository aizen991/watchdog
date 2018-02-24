package com.pachiraframework.watchdog.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author KevinWang
 *
 */
@Slf4j
public class ElasticsearchTest {
	private static String index = "watchdog_ping_record";
	private static String type = index;
	private static TransportClient client;

	@BeforeClass
	public static void init() throws UnknownHostException {
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
		String[] splitor = "localhost:9300".split(",");
		client = new PreBuiltTransportClient(settings);
		for (String hostPort : splitor) {
			String[] strs = hostPort.split(":");
			client.addTransportAddress(new TransportAddress(InetAddress.getByName(strs[0]), Integer.valueOf(strs[1])));
		}
	}

	@AfterClass
	public static void close() {
		client.close();
	}

	@Test
	@DisplayName("通过id获取单条记录")
	public void testGetById() {
		String id = "CcrJx2EBq5M4rSE-PF8X";
		GetResponse response = client.prepareGet(index, type, id).execute().actionGet();
		String json = response.getSourceAsString();
		log.info("\n{}", json);
		// 输出
		// {"sent":10,"success":10,"lost":0,"min":128.0,"max":128.0,"avg":128.0,"timestamp":"2018-02-24T20:28:29Z","moitorId":83}
	}

	/**
	 * 查询monitorId=83的前100条记录，按照时间倒叙排列
	 * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search.html
	 */
	@Test
	@DisplayName("通过monitorId查询所有监控记录")
	public void testGetByMonitorId() {
		QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("moitorId", 83)).filter(QueryBuilders.rangeQuery("min").lte(127.0));
		SearchResponse response = client.prepareSearch(index).setTypes(type).setQuery(queryBuilder).setFrom(0).setSize(100).addSort("timestamp", SortOrder.DESC).get();
		SearchHits searchHits = response.getHits();
		System.out.println(searchHits.getTotalHits());
		for(SearchHit searchHit : searchHits) {
			System.out.print(searchHit.getId()+":");
			System.out.println(searchHit.getSourceAsString());
		}
	}
	
}
