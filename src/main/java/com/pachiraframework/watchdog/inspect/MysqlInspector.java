package com.pachiraframework.watchdog.inspect;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.pachiraframework.watchdog.constant.Metrics;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.MetricReport.StatusEnum;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.MonitorType;
import com.pachiraframework.watchdog.entity.MysqlRecord;

/**
 * 
 * @author KevinWang
 *
 */
@Component
public class MysqlInspector extends AbstractInspector {
	@Override
	public List<MetricReport> inspect(Monitor monitor, AbstractRecord mysqlRecord) {
		MysqlRecord record = (MysqlRecord) mysqlRecord;
		List<MetricReport> list = Lists.newArrayList();

		MetricReport available = availableReport(record);
		list.add(available);
		if (available.getStatus().equals(StatusEnum.DOWN.toString())) {
			return list;
		}
		list.add(tableLockReport(record));
		list.add(tpsReport(record));
		list.add(qpsReport(record));
		return list;
	}

	private MetricReport createTelnetReport(MysqlRecord record) {
		MetricReport report = createReport(record);
		report.setType(MonitorType.MYSQL.getName());
		report.setTimestamp(new Date());
		return report;
	}

	/**
	 * ping成功数量为0视为DOWN
	 * 
	 * @param record
	 * @return
	 */
	private MetricReport availableReport(MysqlRecord record) {
		MetricReport available = createTelnetReport(record);
		available.setMetric(Metrics.Mysql.AVAILABLE);
		available.setStatus(record.getUptime() == null ? StatusEnum.DOWN.toString() : StatusEnum.UP.toString());
		available.setMessage(record.getMessage());
		return available;
	}

	/**
	 * Table_locks_waited/Table_locks_immediate=0.3% 如果这个比值比较大的话，说明表锁造成的阻塞比较严重
	 * 
	 * @param record
	 * @return
	 */
	private MetricReport tableLockReport(MysqlRecord record) {
		MetricReport report = createTelnetReport(record);
		report.setMetric(Metrics.Mysql.TABLE_LOCK);
		Double rate = record.getTableLocksWaited() * 1D / record.getTableLocksImmediate();
		String message = String.format("Table_locks_waited/Table_locks_immediate=%s", rate * 100)+"%";
		report.setMessage(message);
		if (rate > 0.6D) {
			report.setStatus(StatusEnum.CRITICAL.name());
		} else if (rate > 0.3) {
			report.setStatus(StatusEnum.WARNING.name());
		} else {
			report.setStatus(StatusEnum.CLEAR.name());
		}
		return report;
	}

	/**
	 * TPS = (Com_commit + Com_rollback) / seconds
	 * 
	 * @param record
	 * @return
	 */
	private MetricReport tpsReport(MysqlRecord record) {
		MetricReport report = createTelnetReport(record);
		report.setMetric(Metrics.Mysql.TPS);
		Double tps = (record.getComCommit() + record.getComRollback()) * 1D / record.getUptime();
		String message = String.format("TPS = (Com_commit + Com_rollback) / seconds = %s", tps);
		report.setMessage(message);
		if (tps >= 1000) {
			report.setStatus(StatusEnum.CRITICAL.name());
		} else if (tps >= 500) {
			report.setStatus(StatusEnum.WARNING.name());
		} else {
			report.setStatus(StatusEnum.CLEAR.name());
		}
		if (tps <= 1) {
			report.setStatus(StatusEnum.WARNING.name());
			report.setMessage("TPS=" + tps + "过低");
		}
		return report;
	}

	/**
	 * QPS(每秒Query量) QPS = Questions(or Queries) / seconds
	 * 
	 * @param record
	 * @return
	 */
	private MetricReport qpsReport(MysqlRecord record) {
		MetricReport report = createTelnetReport(record);
		report.setMetric(Metrics.Mysql.QPS);
		Double qps = record.getQueries() * 1D / record.getUptime();
		String message = String.format("QPS = Questions(or Queries) / seconds = %s", qps);
		report.setMessage(message);
		if (qps >= 3000) {
			report.setStatus(StatusEnum.CRITICAL.name());
		} else if (qps >= 1000) {
			report.setStatus(StatusEnum.WARNING.name());
		} else {
			report.setStatus(StatusEnum.CLEAR.name());
		}
		if (qps <= 5) {
			report.setStatus(StatusEnum.WARNING.name());
			report.setMessage("QPS=" + qps + "过低");
		}
		return report;
	}
}
