package com.pachiraframework.watchdog.component;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Throwables;
import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.watchdog.dao.MemcachedMonitorDao;
import com.pachiraframework.watchdog.dao.MemcachedRecordDao;
import com.pachiraframework.watchdog.dao.MetricReportDao;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MemcachedMonitor;
import com.pachiraframework.watchdog.entity.MemcachedRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.inspect.MemcachedInspector;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;

/**
 * 支持的memcached版本：1.4.22  1.4.23
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class MemcachedChecker extends AbstractChecker {
	@Autowired
	private MemcachedInspector memcachedInspector;
	@Autowired
	private MemcachedMonitorDao memcachedMonitorDao;
	@Autowired
	private MemcachedRecordDao memcachedRecordDao;
	@Autowired
	private MetricReportDao metricReportDao;
	@Override
	protected AbstractRecord doMonitor(Monitor memcachedMonitor){
		MemcachedMonitor monitor = (MemcachedMonitor) memcachedMonitor;
		Map<SocketAddress, Map<String, String>> statMap = null;
		try {
			MemcachedClient client = new MemcachedClient(new InetSocketAddress(monitor.getHost(), monitor.getPort()));
			statMap = client.getStats();
			client.shutdown();
		}catch(Exception e) {
			MemcachedRecord record = new MemcachedRecord();
			record.setTimestamp(new Date());
			record.setMonitorId(monitor.getId());
			record.setMessage(Throwables.getStackTraceAsString(e));
			memcachedRecordDao.insert(record);
			log.info("monitor.memcached.record.insert.success:插入es成功:{}",record.getId());
			return record;
		}
		
		for(Entry<SocketAddress, Map<String, String>> entry : statMap.entrySet()) {
			MemcachedRecord record = new MemcachedRecord();
			record.setMonitorId(monitor.getId());
			record.setTimestamp(new Date());
			Map<String, String> stats = entry.getValue();
			record.setAcceptingConns(intValue(stats.get("accepting_conns")));
			record.setAuthCmds(longValue(stats.get("auth_cmds")));
			record.setAuthErrors(longValue(stats.get("auth_errors")));
			record.setBytes(longValue(stats.get("bytes")));
			record.setBytesRead(longValue(stats.get("bytes_read")));
			record.setBytesWritten(longValue(stats.get("bytes_written")));
			record.setCasBadval(longValue(stats.get("cas_badval")));
			record.setCasHits(longValue(stats.get("cas_hits")));
			record.setCasMisses(longValue(stats.get("cas_misses")));
			record.setCmdFlush(longValue(stats.get("cmd_flush")));
			record.setCmdGet(longValue(stats.get("cmd_get")));
			record.setCmdSet(longValue(stats.get("cmd_set")));
			record.setCmdTouch(longValue(stats.get("cmd_touch")));
			record.setConnectionStructures(intValue(stats.get("connection_structures")));
			record.setConnYields(intValue(stats.get("conn_yields")));
			record.setCurrConnections(intValue(stats.get("curr_connections")));
			record.setCurrItems(longValue(stats.get("curr_items")));
			record.setDecrHits(longValue(stats.get("decr_hits")));
			record.setDecrMisses(longValue(stats.get("decr_misses")));
			record.setDeleteHits(longValue(stats.get("delete_hits")));
			record.setDeleteMisses(longValue(stats.get("delete_misses")));
			record.setEvictedUnfetched(intValue(stats.get("evicted_unfetched")));
			record.setEvictions(longValue(stats.get("evictions")));
			record.setExpiredUnfetched(intValue(stats.get("expired_unfetched")));
			record.setGetHits(longValue(stats.get("get_hits")));
			record.setGetMisses(longValue(stats.get("get_misses")));
			record.setHashBytes(longValue(stats.get("hash_bytes")));
			record.setHashIsExpanding(intValue(stats.get("hash_is_expanding")));
			record.setHashPowerLevel(intValue(stats.get("hash_power_level")));
			record.setIncrHits(longValue(stats.get("incr_hits")));
			record.setIncrMisses(longValue(stats.get("incr_misses")));
			record.setLibevent(stats.get("libevent"));
			record.setLimitMaxbytes(longValue(stats.get("limit_maxbytes")));
			record.setListenDisabledNum(intValue(stats.get("listen_disabled_num")));
			record.setPid(stats.get("pid"));
			record.setPointerSize(intValue(stats.get("pointer_size")));
			record.setReclaimed(longValue(stats.get("reclaimed")));
			record.setReservedFds(intValue(stats.get("reserved_fds")));
//			record.setRusageSystem(Double.valueOf(stats.get("rusage_system")));
//			record.setRusageUser(Double.valueOf(stats.get("rusager_user")));
			record.setThreads(intValue(stats.get("threads")));
			record.setTime(longValue(stats.get("time")));
			record.setTotalConnections(intValue(stats.get("total_connections")));
			record.setTotalItems(longValue(stats.get("total_items")));
			record.setTouchHits(longValue(stats.get("touch_hits")));
			record.setTouchMisses(longValue(stats.get("touch_misses")));
			record.setUptime(longValue(stats.get("uptime")));
			record.setVersion(stats.get("version"));
			memcachedRecordDao.insert(record);
			log.info("monitor.memcached.record.insert.success:插入es成功:{}",record.getId());
			return record;
		}
		throw new IllegalStateException("未知错误，memcached检查stat返回null");
	}

	@Override
	protected List<MetricReport> doInspectRecord(Monitor monitor, AbstractRecord record) {
		List<MetricReport> reports = memcachedInspector.inspect(monitor,record);
		metricReportDao.batchInsert(reports);
		log.info("monitor.memcached.metric.report.insert.success:record id ={},size={}",record.getId(),reports.size());
		return reports;
	}

	@Override
	protected Page<Monitor> loadBatch(WrappedPageRequest pageRequest) {
		Page<Monitor> page = memcachedMonitorDao.findByPage(pageRequest);
		return page;
	}

}
