package com.dyzhsw.cardcontrol.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
/**
  *    缓存上线文
 * @author Administrator
 *
 */
public class ChannelHandlerContextThreadLocal {
	private Logger logger = LoggerFactory.getLogger(ChannelHandlerContextThreadLocal.class);
	private final ConcurrentMap<String, ChannelHandlerContext> threadLocal = new ConcurrentHashMap<String, ChannelHandlerContext>();

	private ChannelHandlerContextThreadLocal() {
	}

	public ChannelHandlerContext get(String key) {
		ChannelHandlerContext ctx = null;
		if (threadLocal.size() > 0) {
			ctx = threadLocal.get(key);
		}
		return ctx;
	}

	public void set(String key, ChannelHandlerContext ctx) {
		this.threadLocal.put(key, ctx);
		logger.info("set map key:" + key+"---客户端缓存个数---"+threadLocal.size());
	}

	public boolean remove(String key, ChannelHandlerContext ctx) {
		boolean result = this.threadLocal.remove(key, ctx);
		logger.info("remove map key:" + key+"---客户端缓存个数---"+threadLocal.size());
		return result;
	}

	public void remove(String key) {
		this.threadLocal.remove(key);
	}

	public enum LocalVar {
		GETINSTANCE;
		ChannelHandlerContextThreadLocal ctl;

		private LocalVar() {
			ctl = new ChannelHandlerContextThreadLocal();
		}

		public ChannelHandlerContextThreadLocal get() {
			return ctl;
		}
	}
}
