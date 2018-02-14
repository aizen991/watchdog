package com.pachiraframework.watchdog.component;

import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Throwables;
import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.watchdog.dao.MetricReportDao;
import com.pachiraframework.watchdog.dao.RedisMonitorDao;
import com.pachiraframework.watchdog.dao.RedisRecordDao;
import com.pachiraframework.watchdog.entity.AbstractRecord;
import com.pachiraframework.watchdog.entity.MetricReport;
import com.pachiraframework.watchdog.entity.Monitor;
import com.pachiraframework.watchdog.entity.RedisMonitor;
import com.pachiraframework.watchdog.entity.RedisRecord;
import com.pachiraframework.watchdog.inspect.RedisInspector;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class RedisChecker extends AbstractChecker {
	@Autowired
	private RedisMonitorDao redisMonitorDao;
	@Autowired
	private RedisInspector redisInspector;
	@Autowired
	private MetricReportDao metricReportDao;
	@Autowired
	private RedisRecordDao redisRecordDao;
	@Override
	protected AbstractRecord doMonitor(Monitor redisMonitor) {
		RedisMonitor monitor = (RedisMonitor)redisMonitor;
		try {
			Jedis jedis = new Jedis(monitor.getHost(), monitor.getPort());
			String info = jedis.info();
			jedis.close();
			Properties properties = new Properties();
			properties.load(new StringReader(info));
			RedisRecord record = buildRedisRecord(properties, monitor);
			redisRecordDao.insert(record);
			log.info("monitor.redis.record.insert.success:插入es成功:{}",record.getId());
			return record;
		}catch(Exception e) {
			RedisRecord record = new RedisRecord();
			record.setTimestamp(new Date());
			record.setMoitorId(monitor.getId());
			record.setMessage(Throwables.getStackTraceAsString(e));
			redisRecordDao.insert(record);
			log.info("monitor.redis.record.insert.success:插入es成功:{}",record.getId());
			return record;
		}
	}
	
	private RedisRecord buildRedisRecord(Properties properties,RedisMonitor monitor) {
		RedisRecord record = new RedisRecord();
		record.setMoitorId(monitor.getId());
		record.setTimestamp(new Date());
		record.setAofCurrentRewriteTimeSec(properties.getProperty("aof_current_rewrite_time_sec"));
		record.setAofEnabled(intValue(properties.getProperty("aof_enabled")));
		record.setAofLastBgrewriteStatus(properties.getProperty("aof_last_rewrite_time_sec"));
		record.setAofLastRewriteTimeSec(properties.getProperty("aof_last_rewrite_time_sec"));
		record.setAofLastWriteStatus(properties.getProperty("aof_last_write_status"));
		record.setAofRewriteInProgress(properties.getProperty("aof_rewrite_in_progress"));
		record.setAofRewriteScheduled(properties.getProperty("aof_rewrite_scheduled"));
		record.setBlockedClients(intValue(properties.getProperty("blocked_clients")));
		record.setClientBiggestInputBuf(intValue(properties.getProperty("client-biggest_input_buf")));
		record.setClientLongestOutputList(intValue(properties.getProperty("client-longest_output_list")));
		record.setConfigFile(properties.getProperty("config_file"));
		record.setConnectedClients(intValue(properties.getProperty("connected_clients")));
		record.setConnectedSlaves(intValue(properties.getProperty("connected_slaves")));
		record.setDb0(properties.getProperty("db0"));
		record.setDb1(properties.getProperty("db1"));
		record.setDb3(properties.getProperty("db3"));
		record.setEvictedKeys(intValue(properties.getProperty("evicted_keys")));
		record.setExpiredKeys(intValue(properties.getProperty("expired_keys")));
		record.setGccVersion(properties.getProperty("gcc_version"));
		record.setHz(properties.getProperty("hz"));
		record.setInstantaneousOpsPerSec(intValue(properties.getProperty("instantaneous_ops_per_sec")));
		record.setKeyspaceHits(intValue(properties.getProperty("keyspace_hits")));
		record.setKeyspaceMisses(intValue(properties.getProperty("keyspace_misses")));
		record.setLatestForkUsec(intValue(properties.getProperty("latest_fork_usec")));
		record.setLoading(doubleValue(properties.getProperty("loading")));
		record.setLruClock(properties.getProperty("lru_clock"));
		record.setMasterReplOffset(intValue(properties.getProperty("master_repl_offset")));
		record.setMemAllocator(properties.getProperty("mem_allocator"));
		record.setMemFragmentationRatio(properties.getProperty("mem_fragmentation_ratio"));
		record.setMultiplexingApi(properties.getProperty("multiplexing_api"));
		record.setOs(properties.getProperty("os"));
		record.setProcessId(properties.getProperty("process_id"));
		record.setPubsubChannels(intValue(properties.getProperty("pubsub_channels")));
		record.setPubsubPatterns(intValue(properties.getProperty("pubsub_patterns")));
		record.setRdbBgsaveInProgress(properties.getProperty("rdb_bgsave_in_progress"));
		record.setRdbChangesSinceLastSave(properties.getProperty("rdb_changes_since_last_save"));
		record.setRdbCurrentBgsaveTimeSec(properties.getProperty("rdb_current_bgsave_time_sec"));
		record.setRdbLastBgsaveStatus(properties.getProperty("rdb_last_bgsave_status"));
		record.setRdbLastSaveTime(properties.getProperty("rdb_last_save_time"));
		record.setRedisBuildId(properties.getProperty("redis_build_id"));
		record.setRedisGitDirty(properties.getProperty("redis_git_dirty"));
		record.setRedisGitSha1(properties.getProperty("redis_git_sha1"));
		record.setRedisMode(properties.getProperty("redis_mode"));
		record.setRedisVersion(properties.getProperty("redis_version"));
		record.setRejectedConnections(intValue(properties.getProperty("rejected_connections")));
		record.setReplBacklogActive(intValue(properties.getProperty("repl_backlog_active")));
		record.setReplBacklogBistlen(intValue(properties.getProperty("repl_backlog_histlen")));
		record.setReplBacklogFirstByteOffset(intValue(properties.getProperty("repl_backlog_first_byte_offset")));
		record.setReplBacklogSize(intValue(properties.getProperty("repl_backlog_size")));
		record.setRole(properties.getProperty("role"));
		record.setRunId(properties.getProperty("run_id"));
		record.setSyncFull(intValue(properties.getProperty("sync_full")));
		record.setSyncPartialErr(intValue(properties.getProperty("sync_partial_err")));
		record.setSyncPartialOk(intValue(properties.getProperty("sync_partial_ok")));
		record.setTcpPort(intValue(properties.getProperty("tcp_port")));
		record.setTotalCommandsProcessed(intValue(properties.getProperty("total_commands_processed")));
		record.setTotalConnectionsReceived(intValue(properties.getProperty("total_connections_received")));
		record.setUptimeInDays(intValue(properties.getProperty("uptime_in_days")));
		record.setUptimeInSeconds(intValue(properties.getProperty("uptime_in_seconds")));
		record.setUsedCpuSys(doubleValue(properties.getProperty("used_cpu_sys")));
		record.setUsedCpuSysChildren(doubleValue(properties.getProperty("used_cpu_sys_children")));
		record.setUsedCpuUser(doubleValue(properties.getProperty("used_cpu_user")));
		record.setUsedCpuUserChildren(doubleValue(properties.getProperty("used_cpu_user_children")));
		record.setUsedMemory(longValue(properties.getProperty("used_memory")));
		record.setUsedMemoryHuman(properties.getProperty("used_memory_human"));
		record.setUsedMemoryLua(longValue(properties.getProperty("used_memory_lua")));
		record.setUsedMemoryPeak(longValue(properties.getProperty("used_memory_peak")));
		record.setUsedMemoryPeakHuman(properties.getProperty("used_memory_peak_human"));
		record.setUsedMemoryRss(longValue(properties.getProperty("used_memory_rss")));
		return record;
	}

	@Override
	protected List<MetricReport> doInspectRecord(Monitor redisMonitor, AbstractRecord redisRecord) {
		RedisMonitor monitor = (RedisMonitor)redisMonitor;
		RedisRecord record = (RedisRecord)redisRecord;
		List<MetricReport> results = redisInspector.inspect(monitor, record);
		metricReportDao.batchInsert(results);
		log.info("monitor.redis.metric.report.insert.success:record id ={},size={}", record.getId(), results.size());
		return results;
	}

	@Override
	protected Page<Monitor> loadBatch(WrappedPageRequest pageRequest) {
		Page<Monitor> page = redisMonitorDao.findByPage(pageRequest);
		return page;
	}

}
