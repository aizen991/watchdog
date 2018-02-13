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
	protected AbstractRecord doMonitor(Monitor memcachedMonitor) throws Exception{
		MemcachedMonitor monitor = (MemcachedMonitor) memcachedMonitor;
		Map<SocketAddress, Map<String, String>> statMap = null;
		try {
			MemcachedClient client = new MemcachedClient(new InetSocketAddress(monitor.getHost(), monitor.getPort()));
			statMap = client.getStats();
			client.shutdown();
		}catch(Exception e) {
			MemcachedRecord record = new MemcachedRecord();
			record.setTimestamp(new Date());
			record.setMoitorId(monitor.getId());
			record.setMessage(Throwables.getStackTraceAsString(e));
			memcachedRecordDao.insert(record);
			log.info("monitor.memcached.record.insert.success:插入es成功:{}",record.getId());
			return record;
		}
		
		for(Entry<SocketAddress, Map<String, String>> entry : statMap.entrySet()) {
			MemcachedRecord record = new MemcachedRecord();
			record.setMoitorId(monitor.getId());
			record.setTimestamp(new Date());
			Map<String, String> stats = entry.getValue();
			record.setAcceptingConns(Integer.valueOf(stats.get("accepting_conns")));
			record.setAuthCmds(Long.valueOf(stats.get("auth_cmds")));
			record.setAuthErrors(Long.valueOf(stats.get("auth_errors")));
			record.setBytes(Long.valueOf(stats.get("bytes")));
			record.setBytesRead(Long.valueOf(stats.get("bytes_read")));
			record.setBytesWritten(Long.valueOf(stats.get("bytes_written")));
			record.setCasBadval(Long.valueOf(stats.get("cas_badval")));
			record.setCasHits(Long.valueOf(stats.get("cas_hits")));
			record.setCasMisses(Long.valueOf(stats.get("cas_misses")));
			record.setCmdFlush(Long.valueOf(stats.get("cmd_flush")));
			record.setCmdGet(Long.valueOf(stats.get("cmd_get")));
			record.setCmdSet(Long.valueOf(stats.get("cmd_set")));
			record.setCmdTouch(Long.valueOf(stats.get("cmd_touch")));
			record.setConnectionStructures(Integer.valueOf(stats.get("connection_structures")));
			record.setConnYields(Integer.valueOf(stats.get("conn_yields")));
			record.setCurrConnections(Integer.valueOf(stats.get("curr_connections")));
			record.setCurrItems(Long.valueOf(stats.get("curr_items")));
			record.setDecrHits(Long.valueOf(stats.get("decr_hits")));
			record.setDecrMisses(Long.valueOf(stats.get("decr_misses")));
			record.setDeleteHits(Long.valueOf(stats.get("delete_hits")));
			record.setDeleteMisses(Long.valueOf(stats.get("delete_misses")));
			record.setEvictedUnfetched(Integer.valueOf(stats.get("evicted_unfetched")));
			record.setEvictions(Long.valueOf(stats.get("evictions")));
			record.setExpiredUnfetched(Integer.valueOf(stats.get("expired_unfetched")));
			record.setGetHits(Long.valueOf(stats.get("get_hits")));
			record.setGetMisses(Long.valueOf(stats.get("get_misses")));
			record.setHashBytes(Long.valueOf(stats.get("hash_bytes")));
			record.setHashIsExpanding(Integer.valueOf(stats.get("hash_is_expanding")));
			record.setHashPowerLevel(Integer.valueOf(stats.get("hash_power_level")));
			record.setIncrHits(Long.valueOf(stats.get("incr_hits")));
			record.setIncrMisses(Long.valueOf(stats.get("incr_misses")));
			record.setLibevent(stats.get("libevent"));
			record.setLimitMaxbytes(Long.valueOf(stats.get("limit_maxbytes")));
			record.setListenDisabledNum(Integer.valueOf(stats.get("listen_disabled_num")));
			record.setPid(stats.get("pid"));
			record.setPointerSize(Integer.valueOf(stats.get("pointer_size")));
			record.setReclaimed(Long.valueOf(stats.get("reclaimed")));
			record.setReservedFds(Integer.valueOf(stats.get("reserved_fds")));
//			record.setRusageSystem(Double.valueOf(stats.get("rusage_system")));
//			record.setRusageUser(Double.valueOf(stats.get("rusager_user")));
			record.setThreads(Integer.valueOf(stats.get("threads")));
			record.setTime(Long.valueOf(stats.get("time")));
			record.setTotalConnections(Integer.valueOf(stats.get("total_connections")));
			record.setTotalItems(Long.valueOf(stats.get("total_items")));
			record.setTouchHits(Long.valueOf(stats.get("touch_hits")));
			record.setTouchMisses(Long.valueOf(stats.get("touch_misses")));
			record.setUptime(Long.valueOf(stats.get("uptime")));
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
