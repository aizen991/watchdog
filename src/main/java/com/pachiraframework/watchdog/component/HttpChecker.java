package com.pachiraframework.watchdog.component;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.watchdog.dao.HttpMonitorDao;
import com.pachiraframework.watchdog.dao.HttpRecordDao;
import com.pachiraframework.watchdog.dao.MetricReportDao;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.HttpMonitor;
import com.pachiraframework.watchdog.entity.HttpMonitor.MethodEnum;
import com.pachiraframework.watchdog.entity.HttpRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.inspect.HttpInspector;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class HttpChecker extends AbstractChecker {
	@Autowired
	private HttpMonitorDao urlMonitorDao;
	@Autowired
	private MetricReportDao metricReportDao;
	@Autowired
	private HttpInspector httpInspector;
	@Autowired
	private HttpRecordDao httpRecordDao;
	@Override
	protected AbstractRecord doMonitor(Monitor monitor) {
		HttpMonitor httpUrlMonitor = (HttpMonitor)monitor;
		Stopwatch stopwatch = Stopwatch.createStarted();
		HttpRecord record = new HttpRecord();
		record.setTimestamp(new Date());
		record.setMoitorId(monitor.getId());
		HttpRequest httpRequest = null;
		try{
			if(MethodEnum.GET.equals(MethodEnum.of(httpUrlMonitor.getMethod()))){
				httpRequest = getRequest(httpUrlMonitor.getUrl());
			}else{
				httpRequest = postRequest(httpUrlMonitor.getUrl(), httpUrlMonitor.getRequestParams());
			}
			if(!Strings.isNullOrEmpty(httpUrlMonitor.getHttpHeader())){
				Properties headers = new Properties();
				headers.load(new StringReader(httpUrlMonitor.getHttpHeader()));
				for(Entry<Object, Object> entry : headers.entrySet()){
					httpRequest.header((String)entry.getKey(),(String)entry.getValue());
				}
			}
			if(!Strings.isNullOrEmpty(httpUrlMonitor.getUserAgent())){
				httpRequest.userAgent(httpUrlMonitor.getUserAgent());
			}
//			httpRequest.connectTimeout(monitor.getTimeout());//毫秒
			httpRequest.readTimeout(httpUrlMonitor.getTimeout());
			int code = httpRequest.code();
			String body = httpRequest.body();
			long milliseconds = stopwatch.elapsed(TimeUnit.MILLISECONDS);
			record.setResponseTime(milliseconds);
			record.setCode(code);
			record.setBody(body);
		}catch(Exception e){
			record.setResponseTime(-1L);
			record.setMessage(e.getMessage());
			record.setCode(-200);
			//忽略异常，保证下面的监控继续运行
			log.debug(Throwables.getStackTraceAsString(e));
		}finally{
			stopwatch.stop();
			httpRequest.disconnect();
		}
		httpRecordDao.insert(record);
		log.info("monitor.http.record.insert.success:插入es成功:{}",record);
		return record;
	}
	private HttpRequest getRequest(String url){
		HttpRequest httpRequest = HttpRequest.get(url);
		return httpRequest;
	}
	
	private HttpRequest postRequest(String url,String requestParams) throws IOException{
		Map<String, String> param = Maps.newLinkedHashMap();
		if(!Strings.isNullOrEmpty(requestParams)){
			Properties properties = new Properties();
			properties.load(new StringReader(requestParams));
			for(Entry<Object, Object> entry : properties.entrySet()){
				param.put((String)entry.getKey(),(String)entry.getValue());
			}
		}
		HttpRequest httpRequest = HttpRequest.post(url,param,true);
		return httpRequest;
	}
	@Override
	protected List<MetricReport> doInspectRecord(Monitor monitor,AbstractRecord record) {
		List<MetricReport> reports = httpInspector.inspect(monitor,(HttpRecord)record);
		metricReportDao.batchInsert(reports);
		log.info("monitor.http.metric.report.insert.success:record id ={},size={}",record.getId(),reports.size());
		return reports;
	}

	@Override
	protected Page<Monitor> loadBatch(WrappedPageRequest pageRequest) {
		Page<Monitor> page = urlMonitorDao.findByPage(pageRequest);
		return page;
	}

}
