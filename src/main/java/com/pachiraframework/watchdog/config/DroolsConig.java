package com.pachiraframework.watchdog.config;

import java.io.IOException;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.builder.Message.Level;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.Results;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DroolsConig {
	private static final String RULES_PATH = "drools/";
	private KieFileSystem kieFileSystem() throws IOException {
		KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
		for (Resource file : getRuleFiles()) {
//			kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
			log.info("加载规则文件：{}",file.getFile().getAbsolutePath());
			kieFileSystem.write(ResourceFactory.newFileResource(file.getFile()));
		}
		return kieFileSystem;
	}

	private Resource[] getRuleFiles() throws IOException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.drl");
	}

	private KieContainer kieContainer() throws IOException {
//		ReleaseId releaseId = getKieServices().newReleaseId("com.pachiraframework", "kepler", "0.0.1-SNAPSHOT");
		final KieRepository kieRepository = getKieServices().getRepository();

		kieRepository.addKieModule(new KieModule() {
			public ReleaseId getReleaseId() {
				return kieRepository.getDefaultReleaseId();
			}
		});
		KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
		kieBuilder.buildAll();
		Results results = kieBuilder.getResults();
		for(Message message : results.getMessages()){
			if(Level.ERROR.equals(message.getLevel())){
				log.error("规则文件中有语法错误：{}",message);
				throw new RuntimeException(message.toString());
			}else if(Level.INFO.equals(message.getLevel())){
				log.info("{}",message);
			}else if(Level.WARNING.equals(message.getLevel())){
				log.warn("{}",message);
			}
		}
		return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
	}
//	@Bean
//	public KieScanner kieScanner(KieContainer kieContainer){
//		KieScanner scaner = getKieServices().newKieScanner(kieContainer);
//		scaner.start(10000L);//每10秒钟检查下maven私服中的最新版本
//		return scaner;
//	}
	private KieServices getKieServices() {
		return KieServices.Factory.get();
	}

	@Bean
	public KieBase kieBase() throws IOException {
		KieBaseConfiguration config = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
//		config.setOption(MBeansOption.ENABLED);//启用jmx监控
		config.setOption( EventProcessingOption.STREAM );
		return kieContainer().newKieBase(config);
	}

	@Bean
	@ConditionalOnMissingBean(KModuleBeanFactoryPostProcessor.class)
	public KModuleBeanFactoryPostProcessor kiePostProcessor() {
		return new KModuleBeanFactoryPostProcessor();
	}
	
}
