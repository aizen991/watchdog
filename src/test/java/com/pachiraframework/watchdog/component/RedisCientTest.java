package com.pachiraframework.watchdog.component;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import redis.clients.jedis.Jedis;

/**
 * @author wangxuzheng
 *
 */
public class RedisCientTest {
	public static void main(String[] args) throws IOException {
		Jedis jedis = new Jedis("localhost", 6379);
		String info = jedis.info();
		Properties properties = new Properties();
		properties.load(new StringReader(info));
		System.out.println(properties);
		jedis.close();
	}
}
