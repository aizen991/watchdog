package com.pachiraframework.watchdog.action;

import java.io.IOException;

import org.assertj.core.util.Throwables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pachiraframework.watchdog.event.event.MetricReportEvent;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * 告警通知策略
 * 
 * @author Administrator
 *
 */
@Slf4j
public abstract class AbstractAction{
	private static final String TEMPLATE_NAME = "alarm/%s/%s.ftl";
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
	
	protected abstract void doExecute(MetricReportEvent context)throws Exception;
	
	/**
	 * 模版名称
	 * @param context
	 * @return
	 */
	protected String templateName(MetricReportEvent context) {
		return String.format(TEMPLATE_NAME, context.getMonitor().getType().toLowerCase(),name());
	}
	/**
	 * 组件名称
	 * @return
	 */
	protected abstract String name();
	public Template getTemplate(MetricReportEvent context) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
		String name = templateName(context);
		return this.freeMarkerConfigurer.getConfiguration().getTemplate(name);
	}
}
