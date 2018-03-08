package com.pachiraframework.watchdog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pachiraframework.watchdog.interceptor.JwtInterceptor;


/**
 * @author wangxuzheng
 *
 */
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement 
public class WatchdogApplication extends WebMvcConfigurerAdapter {
	@Autowired
	private JwtInterceptor jwtInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(WatchdogApplication.class, args);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
}
