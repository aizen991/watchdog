package com.pachiraframework.watchdog.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

import com.google.common.base.Splitter;
import com.pachiraframework.watchdog.entity.elasticsearch.PingRecord;

import lombok.Cleanup;
import lombok.Data;

abstract class Ping {
	static Ping WINDOWS_PING = new WindowsPing();
	static Ping LINUX_PING = new LinuxPing();
	static final Integer PING_TIMES =  10;//ping10次
	static Ping PING = System.getProperty("os.name").toUpperCase().contains("WINDOWS")?Ping.WINDOWS_PING:Ping.LINUX_PING;
	
	static PingRecord ping(String ip){
		PingRecord result = new PingRecord();
		result.setSent(PING_TIMES);
		try{
			PingResult rs = PING.executePing(ip);
			TreeSet<Double> respTime = rs.getRespTime();
			if(!respTime.isEmpty()){
				result.setMax(respTime.last());
				result.setMin(respTime.first());
				Double total = 0D;
				for(Double resp : respTime){
					total += resp;
				}
				result.setAvg(total/respTime.size());
				result.setSuccess(rs.getSize());
				result.setLost(result.getSent()-result.getSuccess());
			}else{
				result.setLost(PING_TIMES);
				result.setSuccess(0);
				result.setAvg(-100D);
				result.setMin(-100D);
				result.setMax(-100D);
			}
		}catch(IOException e){
			result.setMessage(e.getMessage());
		}
		return result;
	}
	abstract PingResult executePing(String ip) throws IOException;
	
	/**
	 * C:\Users\Administrator>ping 10.1.137.121 -n 10 -w 4
	 * 正在 Ping 10.1.137.121 具有 32 字节的数据:
	 * 请求超时。
	 * 来自 10.1.137.121 的回复: 字节=32 时间=38ms TTL=63
	 * 来自 10.1.137.121 的回复: 字节=32 时间=38ms TTL=63
	 * 来自 10.1.137.121 的回复: 字节=32 时间=39ms TTL=63
	 * 来自 10.1.137.121 的回复: 字节=32 时间=38ms TTL=63
	 * 来自 10.1.137.121 的回复: 字节=32 时间=38ms TTL=63
	 * 来自 10.1.137.121 的回复: 字节=32 时间=39ms TTL=63
	 * 来自 10.1.137.121 的回复: 字节=32 时间=40ms TTL=63
	 * 来自 10.1.137.121 的回复: 字节=32 时间=39ms TTL=63
	 * 来自 10.1.137.121 的回复: 字节=32 时间=40ms TTL=63
	
	 * 10.1.137.121 的 Ping 统计信息:
	 *    数据包: 已发送 = 10，已接收 = 9，丢失 = 1 (10% 丢失)，
	 * 往返行程的估计时间(以毫秒为单位):
	 *     最短 = 38ms，最长 = 40ms，平均 = 38ms
	 * 
	 * C:\Users\Administrator>
	 * 
	 */
	static class WindowsPing extends Ping{
		@Override
		PingResult executePing(String ip) throws IOException {
			PingResult result = new PingResult();
			StringBuffer command = new StringBuffer();
			command.append("ping ").append(ip).append(" -n "+PING_TIMES+" -w 4");
			Process process = Runtime.getRuntime().exec(command.toString());
			@Cleanup
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
			String line = null;
			String successFlag = "TTL";
			TreeSet<Double> respTime = new TreeSet<Double>();
			int size = 0;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.contains(successFlag)){
					Iterator<String> iterator = Splitter.on("=").split(line).iterator();
					iterator.next();
					iterator.next();
					String str = iterator.next();
					Double time = Double.valueOf(str.split("ms")[0]);
					respTime.add(time);
					size++;
				}
			}
			result.setSize(size);
			result.setRespTime(respTime);
			return result;
		}
	}
	
	/**
	 * [root@localhost ~]# ping 10.1.137.121 -c 10
	 * PING 10.1.137.121 (10.1.137.121) 56(84) bytes of data.
	 * 64 bytes from 10.1.137.121: icmp_seq=1 ttl=64 time=0.178 ms
	 * 64 bytes from 10.1.137.121: icmp_seq=2 ttl=64 time=0.175 ms
	 * 64 bytes from 10.1.137.121: icmp_seq=3 ttl=64 time=0.174 ms
	 * 64 bytes from 10.1.137.121: icmp_seq=4 ttl=64 time=0.174 ms
	 * 64 bytes from 10.1.137.121: icmp_seq=5 ttl=64 time=0.175 ms
	 * 64 bytes from 10.1.137.121: icmp_seq=6 ttl=64 time=0.173 ms
	 * 64 bytes from 10.1.137.121: icmp_seq=7 ttl=64 time=0.176 ms
	 * 64 bytes from 10.1.137.121: icmp_seq=8 ttl=64 time=0.178 ms
	 * 64 bytes from 10.1.137.121: icmp_seq=9 ttl=64 time=0.168 ms
	 * 64 bytes from 10.1.137.121: icmp_seq=10 ttl=64 time=0.206 ms
	 * 
	 * --- 10.1.137.121 ping statistics ---
	 * 10 packets transmitted, 10 received, 0% packet loss, time 9000ms
	 * rtt min/avg/max/mdev = 0.168/0.177/0.206/0.018 ms
	 * [root@localhost ~]# 
	 * 
	 * @author wangxuzheng@aliyun.com
	 *
	 */
	static class LinuxPing extends Ping{
		@Override
		PingResult executePing(String ip) throws IOException {
			PingResult result = new PingResult();
			StringBuffer command = new StringBuffer();
			command.append("ping ").append(ip).append(" -c "+PING_TIMES+"");
			Process process = Runtime.getRuntime().exec(command.toString());
			@Cleanup
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(),"UTF-8"));
			String line = null;
			String successFlag = "ttl";
			TreeSet<Double> respTime = new TreeSet<Double>();
			int size = 0;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.contains(successFlag)){
					Iterator<String> iterator = Splitter.on("time=").split(line).iterator();
					iterator.next();
					String str = iterator.next();
					Double time = Double.valueOf(str.split(" ")[0]);
					respTime.add(time);
					size++;
				}
			}
			result.setSize(size);
			result.setRespTime(respTime);
			return result;
		}
	}
	
	@Data
	static class PingResult{
		private TreeSet<Double> respTime;
		private int size;
	}
	
}
