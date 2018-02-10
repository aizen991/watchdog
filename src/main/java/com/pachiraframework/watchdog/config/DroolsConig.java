package com.pachiraframework.watchdog.config;

import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Message.Level;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DroolsConig {
	@Bean
	public KieContainer kieContainer() {
		 System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm:ss");
		KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
		Results results = kieContainer.verify();
		for(Message message : results.getMessages()) {
			log.info("Level={},Messate:{}",message.getLevel(),message.getText());
			if(message.getLevel().equals(Level.ERROR)) {
				throw new RuntimeException(message.getText());
			}
		}
		return kieContainer;
	}
	
}
