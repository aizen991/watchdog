package com.pachiraframework.watchdog.constant;

import java.util.Map;

import com.google.common.collect.Maps;
import com.pachiraframework.watchdog.entity.Metric;

/**
 * 系统指标库定义
 * @author KevinWang
 *
 */
public class Metrics {
	private static final Map<String, Metric> METRICS = Maps.newLinkedHashMap();
	
	public static Metric getMetric(String id) {
		return METRICS.get(id);
	}
	public static final class Ping{
		private static final String PREFIX = "ping.";
		public static final String AVAILABLE = PREFIX+"available";
		/**
		 * ping成功率=success/total
		 */
		public static final String SUCCESS_RATE = PREFIX+"success.rate";
		/**
		 * ping的平均响应时间
		 */
		public static final String RESPONSE_TIME = PREFIX+"response.time";
//		public static final String LOST_COUNT = PREFIX+"lost.count";
//		public static final String SENT_COUNT = PREFIX+"sent.count";
//		public static final String TIME_MAX = PREFIX+"time.max";
//		public static final String TIME_MIN = PREFIX+"time.min";
//		public static final String TIME_AVG = PREFIX+"time.avg";
	}
	
	public static final class Http{
		private static final String PREFIX = "http.";
		public static final String AVAILABLE = PREFIX+"available";
		public static final String RESPONSE_TIME = PREFIX+"response.time";
		public static final String RESPONSE_BODY = PREFIX+"response.body";
	}
	
	public static final class Telnet{
		private static final String PREFIX = "telnet.";
		public static final String AVAILABLE = PREFIX+"available";
		public static final String RESPONSE_TIME = PREFIX+"response.time";
	}
	
	public static final class Mysql{
		private static final String PREFIX = "mysql.";
		public static final String AVAILABLE = PREFIX+"available";
		public static final String TABLE_LOCK = PREFIX + "table_lock";
		public static final String QPS = PREFIX + "qps";
		public static final String TPS = PREFIX + "tps";
		/**
		 * 废弃的客户端连接数
		 */
		public static final String ABORTED_CLIENTS = PREFIX+"status.aborted_clients";
		public static final String OPENED_TABLES = PREFIX+"status.opened_tables";
		public static final String QUESTIONS = PREFIX+"status.questions";
		public static final String SELECT_FULL_JOIN = PREFIX+"status.select_full_join";
		public static final String SELECT_SCAN = PREFIX+"status.select_scan";
		public static final String SLAVE_RUNNING = PREFIX+"status.slave_running";
		public static final String THREADS_CONNECTED = PREFIX+"status.threads_connected";
		public static final String THREADS_CREATED = PREFIX+"status.threads_created";
		public static final String THREADS_RUNNING = PREFIX+"status.threads_running";
	}
	
	public static final class Memcached{
		private static final String PREFIX = "memcached.";
		public static final String AVAILABLE = PREFIX+"available";
		public static final String CURR_CONNECTIONS = PREFIX+"stats.curr_connections";
		/**
		 * get命中率
		 */
		public static final String GET_HITS_RATE = PREFIX+"stats.get_hits_rate";
		/**
		 * 内存利用率
		 */
		public static final String MEMORY_USED_RATE = PREFIX + "stats.memory_used_rate";
	}
	
	public static final class Redis{
		private static final String PREFIX = "redis.";
		public static final String AVAILABLE = PREFIX+"available";
	}
	
	public static final class Tomcat{
		private static final String PREFIX = "tomcat.";
		public static final String AVAILABLE = PREFIX+"available";
	}
	
	public static final class Trace{
		private static final String PREFIX = "trace.";
		public static final String AVAILABLE = PREFIX+"available";
	}
}
