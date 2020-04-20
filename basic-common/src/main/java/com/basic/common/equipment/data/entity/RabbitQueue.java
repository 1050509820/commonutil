package com.basic.common.equipment.data.entity;

//rabbitmq队列
public class RabbitQueue {

	//上下线通知消息
	public final static String onlineStatus="onlineStatus";
	//上报数据转发
	public final static String sewage = "sewage";
	//系统日志消息
	public final static String systemLog="systemLog";
	//设置系统key值，和在线状态
	public final static String systemKey="systemKey";
	//手动参数上报
	public final static String manual="manual";
}
