package com.pachiraframework.watchdog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wangxuzheng
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class RedisRecord extends AbstractRecord {
	// Server ;
	private String redisVersion;
	private String redisGitSha1;
	private String redisGitDirty;
	private String redisBuildId;
	private String redisMode;
	private String os;
	private String archBits;
	private String multiplexingApi;
	private String gccVersion;
	private String processId;
	private String runId;
	private Integer tcpPort;
	private Integer uptimeInSeconds;
	private Integer uptimeInDays;
	private String hz;
	private String lruClock;
	private String configFile;
	// Clients ;
	private Integer connectedClients;
	private Integer clientLongestOutputList;
	private Integer clientBiggestInputBuf;
	private Integer blockedClients;
	// Memory ;
	private Long usedMemory;
	private String usedMemoryHuman;
	private Long usedMemoryRss;
	private Long usedMemoryPeak;
	private String usedMemoryPeakHuman;
	private Long usedMemoryLua;
	private String memFragmentationRatio;
	private String memAllocator;
	// Persistence ;
	private Double loading;
	private String rdbChangesSinceLastSave;
	private String rdbBgsaveInProgress;
	private String rdbLastSaveTime;
	private String rdbLastBgsaveStatus;
	private String rdbLastBgsaveTimeSec;
	private String rdbCurrentBgsaveTimeSec;
	private Integer aofEnabled;
	private String aofRewriteInProgress;
	private String aofRewriteScheduled;
	private String aofLastRewriteTimeSec;
	private String aofCurrentRewriteTimeSec;
	private String aofLastBgrewriteStatus;
	private String aofLastWriteStatus;
	// Stats ;
	private Integer totalConnectionsReceived;
	private Integer totalCommandsProcessed;
	private Integer instantaneousOpsPerSec;
	private Integer rejectedConnections;
	private Integer syncFull;
	private Integer syncPartialOk;
	private Integer syncPartialErr;
	private Integer expiredKeys;
	private Integer evictedKeys;
	private Integer keyspaceHits;
	private Integer keyspaceMisses;
	private Integer pubsubChannels;
	private Integer pubsubPatterns;
	private Integer latestForkUsec;
	// Replication ;
	private String role;
	private Integer connectedSlaves;
	private Integer masterReplOffset;
	private Integer replBacklogActive;
	private Integer replBacklogSize;
	private Integer replBacklogFirstByteOffset;
	private Integer replBacklogBistlen;
	// CPU ;
	private Double usedCpuSys;
	private Double usedCpuUser;
	private Double usedCpuSysChildren;
	private Double usedCpuUserChildren;;
	private String db0;
	private String db1;
	private String db3;
}
