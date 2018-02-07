package com.pachiraframework.watchdog.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxuzheng
 *
 */
@Configuration
public class ElasticsearchConfig {
	@Value("${es.cluster.name?:elasticsearch}")
	private String clusterName;
	@Value("${es.hosts?:localhost:9300}")
	private String hosts;

	@Bean(destroyMethod = "close")
	public TransportClient transportClient() throws UnknownHostException {
		Settings settings = Settings.builder().put("cluster.name", this.clusterName).build();
		String[] splitor = this.hosts.split(",");
		TransportClient client = new PreBuiltTransportClient(settings);
		for (String hostPort : splitor) {
			String[] strs = hostPort.split(":");
			client.addTransportAddress(new TransportAddress(InetAddress.getByName(strs[0]), Integer.valueOf(strs[1])));
		}
		return client;
	}
}
