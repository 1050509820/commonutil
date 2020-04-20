package com.dyzhsw.cardcontrol.message;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.dyzhsw.cardcontrol.util.StringReplaceUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dyzhsw.cardcontrol.redis.RedisUtil;

import io.netty.channel.ChannelHandlerContext;

/**
 * 解析入口
 * 
 * @author Administrator
 *
 */
@Component
public class Resolver {
	@Resource
	private RedisUtil redisUtil;
	@Resource
	MessageResolverImpl messageResolver;


	private static Logger logger = LoggerFactory.getLogger(Resolver.class);
	public List<String> resolveReportedData(String message, ChannelHandlerContext ctx) {
		List<String> list=new ArrayList<>();
		List<String> listMsg=new ArrayList<>();
		if(message.length()<4 || StringUtils.isBlank(message)){
			return list;
		}
		try{
			listMsg.add(message);
			for(String msg:listMsg){
				String header = msg.substring(0,4);//帧起始符
				String centerAddress = msg.substring(4,6);//中心站地址
				String telemetryAddress = msg.substring(6,16);//遥测站地址
				String password = msg.substring(16,20);//密码
				String functionCode = msg.substring(20,22);//功能码
				String crc = msg.substring(msg.length()-4,msg.length());
				logger.info("-----------------------消息类别:"+functionCode+"--------------------");
				List<String> result = this.messageResolver.resolver(functionCode,ctx,message);
				if(result==null){
					logger.error("------------------客户端地址{} 发生断包/粘包 ,请注意}--------------",
							ctx.channel().remoteAddress());
				}
			}


		}catch (Exception e){
			logger.error("解析入口失败--------解析内容{}--{}", message, e);
		}

		return null;
	}
}
