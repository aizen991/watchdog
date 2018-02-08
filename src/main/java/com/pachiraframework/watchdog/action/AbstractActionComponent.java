package com.pachiraframework.watchdog.action;

import org.assertj.core.util.Throwables;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pachiraframework.watchdog.event.event.MetricReportEvent;

import freemarker.template.Template;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 告警通知策略
 * 
 * @author Administrator
 *
 */
@Slf4j
public abstract class AbstractActionComponent implements InitializingBean{
	@Getter
	private Template template;
	@Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
	
	protected Gson gson = new GsonBuilder().create();

	public void execute(MetricReportEvent context){
		try{
			doExecute(context);
		}catch(Exception e){
			log.error(Throwables.getStackTrace(e));
		}
	}
	
	protected abstract void doExecute(MetricReportEvent context);

	@Override
	public void afterPropertiesSet() throws Exception {
		this.template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName());
	}
	
	protected abstract String templateName();
	
}
