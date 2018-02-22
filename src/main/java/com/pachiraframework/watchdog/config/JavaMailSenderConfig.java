package com.pachiraframework.watchdog.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 发送邮件
 * @author wangxuzheng
 *
 */
@Configuration
public class JavaMailSenderConfig {
	@Bean
	public JavaMailSender javaMailSender(){
		JavaMailSenderImpl mail = new JavaMailSenderImpl();
		mail.setHost("smtp.163.com");
		mail.setPort(25);
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
		javaMailProperties.setProperty("mail.smtp.timeout", "true");
		javaMailProperties.setProperty("mail.transport.protocol", "smtp");
		mail.setJavaMailProperties(javaMailProperties);
		mail.setDefaultEncoding("utf-8");
		mail.setUsername("kepler2017@163.com");
		mail.setPassword("3edc4rfv");
		return mail;
	}
}
