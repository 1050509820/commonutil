package com.dyzhsw.cardcontrol.message;

import javax.annotation.Resource;

import com.dyzhsw.cardcontrol.util.BCC;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dyzhsw.cardcontrol.cache.ChannelHandlerContextThreadLocal.LocalVar;
import com.dyzhsw.cardcontrol.redis.RedisUtil;
import com.dyzhsw.cardcontrol.util.StringReplaceUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 多线程共享 服务端处理io
 * 
 * @author Administrator
 *
 */
@Component
@Sharable
public class ServerChannelHandlerAdapter extends SimpleChannelInboundHandler<Object> {
	/**
	 * 日志处理
	 */
	private Logger logger = LoggerFactory.getLogger(ServerChannelHandlerAdapter.class);
	@Autowired
	Resolver resolver;
	@Resource
	private RedisUtil redisUtil;

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.error("客户端异常", cause);
		// 关闭通道
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg == null) {
			return;
		}
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String message;
		message = BCC.bytesToHexString(req).toUpperCase();
		logger.info(message);
		message.replaceAll("\r\n|\r|\n", "");
		if (null != message) {
			logger.info("客户端地址{} 上报数据:{}", ctx.channel().remoteAddress(), message);
			this.resolver.resolveReportedData(message, ctx);
		} else {
			logger.info("客户端地址{}报文为空 ", ctx.channel().remoteAddress());
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("客户端地址{} 连接成功", ctx.channel().remoteAddress());
		// ctx.writeAndFlush("连接成功！");
		super.channelActive(ctx);
		logger.info("clientId-asLongText:" + StringReplaceUtils.replace(ctx.channel().id().asLongText(), "-", ""));
		LocalVar.GETINSTANCE.get().set(StringReplaceUtils.replace(ctx.channel().id().asLongText(), "-", ""), ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		logger.info("客户端地址{} 退出连接", ctx.channel().remoteAddress());
		ctx.writeAndFlush("退出成功！");
		String key = StringReplaceUtils.replace(ctx.channel().id().asLongText(), "-", "");
		LocalVar.GETINSTANCE.get().remove(key);
	}

	// 心跳超时
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		String key = StringReplaceUtils.replace(ctx.channel().id().asLongText(), "-", "");
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			switch (event.state()) {
			case ALL_IDLE:
				logger.info("客户端地址{} 所有类型超时 ", ctx.channel().remoteAddress());
				LocalVar.GETINSTANCE.get().remove(key);
				ctx.close();
				break;
			case READER_IDLE:
				logger.info("客户端地址{} Reader读超时 ", ctx.channel().remoteAddress());
				// 超时关闭channel
				ctx.close();
				break;
			case WRITER_IDLE:
				logger.info("客户端地址{} Writer写超时 ", ctx.channel().remoteAddress());
				ctx.close();
				break;
			default:
				break;
			}
		}
		super.userEventTriggered(ctx, evt);
	}

}
