package com.dyzhsw.cardcontrol.server;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dyzhsw.cardcontrol.config.NettyConfig;
import com.dyzhsw.cardcontrol.message.ServerChannelHandlerAdapter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

@Component
public class NettyServer {

	/**
	 * NettyServerListener 日志输出器
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);
	/**
	 * 创建bootstrap
	 */
	ServerBootstrap serverBootstrap = new ServerBootstrap();
	/**
	 * BOSS
	 */
	EventLoopGroup boss = new NioEventLoopGroup();
	/**
	 * Worker
	 */
	EventLoopGroup work = new NioEventLoopGroup();
	/**
	 * 通道适配器
	 */
	@Resource
	private ServerChannelHandlerAdapter channelHandlerAdapter;
	/**
	 * NETT服务器配置类
	 */
	@Resource
	private NettyConfig nettyConfig;

	/**
	 * 关闭服务器方法
	 */
	@PreDestroy
	public void close() {
		LOGGER.info("关闭服务器....");
		// 优雅退出
		boss.shutdownGracefully();
		work.shutdownGracefully();
	}

	/**
	 * 开启及服务线程
	 */
	public void start() {
		// 从配置文件中(application.yml)获取服务端监听端口号
		int port = nettyConfig.getNettyMonitorPort();
		serverBootstrap.group(boss, work).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
				.handler(new LoggingHandler(LogLevel.INFO));
		try {
			// 设置事件处理
			serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					// 处理分包，拆包/粘包，格式要一致
					// pipeline.addLast(new
					// LengthFieldBasedFrameDecoder(NettyConstant.getMaxFrameLength(), 3, 2, 0, 0));
					// ByteBuf delimiter1 = Unpooled.copiedBuffer(new byte[]{(byte)0x7e});
					// pipeline.addLast(new
					// DelimiterBasedFrameDecoder(NettyConstant.getMaxFrameLength(), true,
					// delimiter1));
					// pipeline.addLast(new ObjectCodec());
					// 参数1 指定读操作空闲秒数
					// 参数2 指定写操作的空闲秒数
					// 参数3读写空闲秒数
					// pipeline.addLast("ping", new
					// IdleStateHandler(NettyConstant.getReadIdelTimeOut(),
					// NettyConstant.getWriteIdelTimeOut(), NettyConstant.getAllIdelTimeOut(),
					// TimeUnit.SECONDS));
					pipeline.addLast(channelHandlerAdapter);
				}
			});
			LOGGER.info("netty服务器在[{}]端口启动监听", port);
			ChannelFuture f = serverBootstrap.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			LOGGER.info("[出现异常] 释放资源");
			boss.shutdownGracefully();
			work.shutdownGracefully();
		}
	}
}
