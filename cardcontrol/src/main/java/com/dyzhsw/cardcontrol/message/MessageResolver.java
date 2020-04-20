package com.dyzhsw.cardcontrol.message;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.codec.DecoderException;

/**
 *   响应、上报数据解析父接口
 * @author Administrator
 *
 */
public interface MessageResolver {

	/**
	 * 解析业务
	 * @param afn 功能码
	 * @param ctx 通道上下文
	 * @param message 报文（16进制或者字符串）
	 * @return
	 */
	public List<String> resolver(String afn, ChannelHandlerContext ctx, String message) throws DecoderException;

}
