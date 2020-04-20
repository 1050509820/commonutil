package com.dyzhsw.cardcontrol.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * netty端口配置类
 * @author Administrator
 *
 */
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyConfig {
	private int nettyMonitorPort;

	public int getNettyMonitorPort() {
		return nettyMonitorPort;
	}

	public void setNettyMonitorPort(int nettyMonitorPort) {
		this.nettyMonitorPort = nettyMonitorPort;
	}
}
