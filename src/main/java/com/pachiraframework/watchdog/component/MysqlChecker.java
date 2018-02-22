package com.pachiraframework.watchdog.component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.watchdog.dao.MetricReportDao;
import com.pachiraframework.watchdog.dao.MysqlMonitorDao;
import com.pachiraframework.watchdog.dao.MysqlRecordDao;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.MysqlMonitor;
import com.pachiraframework.watchdog.entity.MysqlRecord;
import com.pachiraframework.watchdog.inspect.MysqlInspector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class MysqlChecker extends AbstractChecker {
	@Autowired
	private MysqlMonitorDao mysqlMonitorDao;
	@Autowired
	private MetricReportDao metricReportDao;
	@Autowired
	private MysqlInspector mysqlInspector;
	@Autowired
	private MysqlRecordDao mysqlRecordDao;

	@Override
	protected MysqlRecord doMonitor(Monitor m) {
		MysqlMonitor monitor = (MysqlMonitor) m;
		MysqlRecord record = new MysqlRecord();
		record.setMoitorId(monitor.getId());
		record.setTimestamp(new Date());
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		if (!Strings.isNullOrEmpty(monitor.getUsername())) {
			dataSource.setUsername(monitor.getUsername());
		}
		if (!Strings.isNullOrEmpty(monitor.getPassword())) {
			dataSource.setPassword(monitor.getPassword());
		}
		dataSource.setUrl(String.format("jdbc:mysql://%s:%s", monitor.getHost(), monitor.getPort()));
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			configShowStatus(record, jdbcTemplate);
		}catch(Exception e) {
			record.setMessage(Throwables.getStackTraceAsString(e));
			mysqlRecordDao.insert(record);
			log.info("monitor.mysql.record.insert.success:插入es成功:{}",record.getId());
			return record;
		}
		mysqlRecordDao.insert(record);
		log.info("monitor.mysql.record.insert.success:插入es成功:{}", record.getId());
		return record;
	}

	@Override
	protected List<MetricReport> doInspectRecord(Monitor monitor, AbstractRecord record) {
		List<MetricReport> results = mysqlInspector.inspect(monitor, record);
		metricReportDao.batchInsert(results);
		log.info("monitor.mysql.metric.report.insert.success:record id ={},size={}", record.getId(), results.size());
		return results;
	}

	@Override
	protected Page<Monitor> loadBatch(WrappedPageRequest pageRequest) {
		Page<Monitor> page = mysqlMonitorDao.findByPage(pageRequest);
		return page;
	}

	private void configShowStatus(MysqlRecord record, JdbcTemplate jdbcTemplate) {
		Map<String, StatusBean> status = showStatus(jdbcTemplate);
		record.setAbortedClients(intValue(status.get("Aborted_clients").getValue()));
		record.setThreadsConnected(intValue(status.get("Threads_connected").getValue()));
		record.setThreadsCreated(intValue(status.get("Threads_created").getValue()));
		record.setThreadsRunning(intValue(status.get("Threads_running").getValue()));
		record.setThreadsCached(intValue(status.get("Threads_cached").getValue()));
		record.setAbortedConnects(intValue(status.get("Aborted_connects").getValue()));
		record.setComDelete(intValue(status.get("Com_delete").getValue()));
		record.setComInsert(intValue(status.get("Com_insert").getValue()));
		record.setComSelect(intValue(status.get("Com_select").getValue()));
		record.setComUpdate(intValue(status.get("Com_update").getValue()));
		record.setUptime(intValue(status.get("Uptime").getValue()));
		record.setConnections(intValue(status.get("Connections").getValue()));
		record.setTableLocksImmediate(intValue(status.get("Table_locks_immediate").getValue()));
		record.setTableLocksWaited(intValue(status.get("Table_locks_waited").getValue()));
		record.setComCommit(intValue(status.get("Com_commit").getValue()));
		record.setComRollback(intValue(status.get("Com_rollback").getValue()));
		record.setQueries(intValue(status.get("Queries").getValue()));
	}

	private Map<String, StatusBean> showStatus(JdbcTemplate jdbcTemplate) {
		Map<String, StatusBean> map = Maps.newHashMap();
		jdbcTemplate.query("show status", new ResultSetExtractor<List<StatusBean>>() {
			@Override
			public List<StatusBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<StatusBean> list = Lists.newArrayList();
				while (rs.next()) {
					StatusBean bean = new StatusBean(rs.getString(1), rs.getString(2));
					map.put(bean.getName(), bean);
				}
				return list;
			}
		});
		return map;
	}

	/**
	 * @author wangxuzheng
	 *
	 */
	@Data
	@AllArgsConstructor
	private static class StatusBean {
		private String name;
		private String value;
	}

}
