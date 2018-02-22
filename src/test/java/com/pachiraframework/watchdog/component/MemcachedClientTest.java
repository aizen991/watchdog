package com.pachiraframework.watchdog.component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.pachiraframework.watchdog.entity.MemcachedRecord;

import net.spy.memcached.MemcachedClient;

/**
 * @author wangxuzheng
 *
 */
public class MemcachedClientTest {
	public static void main(String[] args) throws IOException {
		MemcachedClient client = new MemcachedClient(new InetSocketAddress("localhost", 11211));
		Map<SocketAddress, Map<String, String>> statMap = client.getStats();
		for(Entry<SocketAddress, Map<String, String>> entry : statMap.entrySet()) {
			MemcachedRecord record = new MemcachedRecord();
			record.setMoitorId(12L);
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
			System.out.println(record);
			break;
		}
		client.shutdown();
	}
}
