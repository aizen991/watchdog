package com.pachiraframework.watchdog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wangxuzheng
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.pachiraframework.watchdog"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("监控中心")
				.description("应用监控、中间件监控、日志异常监控")
				.contact(new Contact("Kevin", "http://blog.csdn.net/crazycoder2010/", "wangxuzheng@aliyun.com"))
				.version("1.0")
				.build();
	}
}
