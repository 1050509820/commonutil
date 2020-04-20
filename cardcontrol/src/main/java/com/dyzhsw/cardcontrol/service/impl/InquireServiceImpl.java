package com.dyzhsw.cardcontrol.service.impl;

import com.dyzhsw.cardcontrol.dto.OutMessage;
import com.dyzhsw.cardcontrol.mapper.OutMessageMapper;
import com.dyzhsw.cardcontrol.message.Resolver;
import com.dyzhsw.cardcontrol.service.InquireService;
import com.dyzhsw.cardcontrol.util.CRC8;
import com.dyzhsw.cardcontrol.util.IDUtils;
import com.dyzhsw.cardcontrol.util.equipment_base;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class InquireServiceImpl implements InquireService {

    @Autowired
    private OutMessageMapper outMessageMapper;

    private static Logger logger = LoggerFactory.getLogger(Resolver.class);
    //中心站查询遥测站实时值
    @Override
    public OutMessage inquireTiming(ChannelHandlerContext ctx, equipment_base equipment_base){
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header ="7E7E";//帧起始符
        String centerAddress =equipment_base.getCenterAdress();//中心站地址
        String telemetryAddress =equipment_base.getTelemetryAddress();//遥测站地址
        String password = equipment_base.getPassword();//密码
        String functionCode = "37";//功能码
        String length = "08";//长度
        String flag = "0000";//消息流水
        String respose=header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02"+flag+time+"05";
        String myCrc = null;
        try {
            myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
            respose=respose+myCrc;
            OutMessage outMessage = new OutMessage();
            outMessage.setId(IDUtils.createUUID());
            outMessage.setSendtime(new Date());
            outMessage.setFlag(flag);
            outMessage.setFunctioncode(functionCode);
            outMessage.setMessage(respose);
            outMessage.setTelemetryaddress(telemetryAddress);
            outMessageMapper.insertSelective(outMessage);

            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            logger.info("发送报文成功，功能码:{},报文：{}",functionCode,respose);
            return outMessage;
        } catch (DecoderException e) {
            e.printStackTrace();
            logger.info("发送报文失败，功能码{}，报文：{}",functionCode,respose);
            return null;
        }
    }
    //中心站查询遥测站状态和报警信息
    @Override
    public OutMessage inquireWarning(ChannelHandlerContext ctx, equipment_base equipment_base) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header ="7E7E";//帧起始符
        String centerAddress =equipment_base.getCenterAdress();//中心站地址
        String telemetryAddress =equipment_base.getTelemetryAddress();//遥测站地址
        String password = equipment_base.getPassword();//密码
        String functionCode = "46";//功能码
        String length = "08";//长度
        String flag = "0000";//消息流水
        String respose=header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02"+flag+time+"05";
        String myCrc = null;
        try {
            myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
            respose=respose+myCrc;

            OutMessage outMessage = new OutMessage();
            outMessage.setId(IDUtils.createUUID());
            outMessage.setSendtime(new Date());
            outMessage.setFlag(flag);
            outMessage.setFunctioncode(functionCode);
            outMessage.setMessage(respose);
            outMessage.setTelemetryaddress(telemetryAddress);
            outMessageMapper.insertSelective(outMessage);


            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            logger.info("发送报文成功，功能码:{},报文：{}",functionCode,respose);

            return outMessage;

        } catch (DecoderException e) {
            e.printStackTrace();
            logger.info("发送报文失败，功能码{}，报文：{}",functionCode,respose);
            return null;
        }
    }
    //中心站查询遥测站开关泵记录
    @Override
    public OutMessage inquirePump(ChannelHandlerContext ctx, equipment_base equipment_base,String mark) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header ="7E7E";//帧起始符
        String centerAddress =equipment_base.getCenterAdress();//中心站地址
        String telemetryAddress =equipment_base.getTelemetryAddress();//遥测站地址
        String password = equipment_base.getPassword();//密码
        String functionCode = "E1";//功能码
        String length = "0E";//长度
        String flag = "0000";//消息流水
        mark = "FF0F18"+mark;
        String respose=header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02"+flag+time+mark+"05";
        String myCrc = null;
        try {
            myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
            respose=respose+myCrc;

            OutMessage outMessage = new OutMessage();
            outMessage.setId(IDUtils.createUUID());
            outMessage.setSendtime(new Date());
            outMessage.setFlag(flag);
            outMessage.setFunctioncode(functionCode);
            outMessage.setMessage(respose);
            outMessage.setTelemetryaddress(telemetryAddress);
            outMessageMapper.insertSelective(outMessage);

            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            logger.info("发送报文成功，功能码:{},报文：{}",functionCode,respose);

            return outMessage;
        } catch (DecoderException e) {
            e.printStackTrace();
            logger.info("发送报文失败，功能码{}，报文：{}",functionCode,respose);
            return null;
        }
    }
    //中心站设置药测站时钟
    @Override
    public OutMessage setClock(ChannelHandlerContext ctx, equipment_base equipment_base) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header ="7E7E";//帧起始符
        String centerAddress =equipment_base.getCenterAdress();//中心站地址
        String telemetryAddress =equipment_base.getTelemetryAddress();//遥测站地址
        String password = equipment_base.getPassword();//密码
        String functionCode = "4A";//功能码
        String length = "08";//长度
        String flag = "0000";//消息流水
        String respose=header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02"+flag+time+"05";
        String myCrc = null;
        try {
            myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
            respose=respose+myCrc;

            OutMessage outMessage = new OutMessage();
            outMessage.setId(IDUtils.createUUID());
            outMessage.setSendtime(new Date());
            outMessage.setFlag(flag);
            outMessage.setFunctioncode(functionCode);
            outMessage.setMessage(respose);
            outMessage.setTelemetryaddress(telemetryAddress);
            outMessageMapper.insertSelective(outMessage);

            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            logger.info("发送报文成功，功能码:{},报文：{}",functionCode,respose);

            return outMessage;

        } catch (DecoderException e) {
            e.printStackTrace();
            logger.info("发送报文失败，功能码{}，报文：{}",functionCode,respose);
            return null;
        }
    }
    //中心站查询遥测站时钟
    @Override
    public OutMessage queryClock(ChannelHandlerContext ctx, equipment_base equipment_base) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header ="7E7E";//帧起始符
        String centerAddress =equipment_base.getCenterAdress();//中心站地址
        String telemetryAddress =equipment_base.getTelemetryAddress();//遥测站地址
        String password = equipment_base.getPassword();//密码
        String functionCode = "51";//功能码
        String length = "08";//长度
        String flag = "0000";//消息流水
        String respose=header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02"+flag+time+"05";
        String myCrc = null;
        try {
            myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
            respose=respose+myCrc;
            OutMessage outMessage = new OutMessage();
            outMessage.setId(IDUtils.createUUID());
            outMessage.setSendtime(new Date());
            outMessage.setFlag(flag);
            outMessage.setFunctioncode(functionCode);
            outMessage.setMessage(respose);
            outMessage.setTelemetryaddress(telemetryAddress);
            outMessageMapper.insertSelective(outMessage);

            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            logger.info("发送报文成功，功能码:{},报文：{}",functionCode,respose);

            return outMessage;
        } catch (DecoderException e) {
            e.printStackTrace();
            logger.info("发送报文失败，功能码{}，报文：{}",functionCode,respose);
            return null;
        }
    }

    @Override
    public OutMessage modifyConfiguration(ChannelHandlerContext ctx, equipment_base equipment_base) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header ="7E7E";//帧起始符
        String centerAddress =equipment_base.getCenterAdress();//中心站地址
        String telemetryAddress =equipment_base.getTelemetryAddress();//遥测站地址
        String password = equipment_base.getPassword();//密码
        String functionCode = "40";//功能码
        String length = "";//长度
        String flag = "0000";//消息流水
        String mainBody = flag+time;//报文正文
        if(equipment_base.getCenterAdress4()!=null){
            mainBody+="0120"+equipment_base.getCenterAdress4();
        }
        if(equipment_base.getChannel1()!=null){
            mainBody+="0400"+equipment_base.getChannel1();
        }
        if(equipment_base.getChannel2()!=null){
            mainBody+="0600"+equipment_base.getChannel2();
        }
        if(equipment_base.getChannel3()!=null){
            mainBody+="0800"+equipment_base.getChannel3();
        }
        if(equipment_base.getChannel4()!=null){
            mainBody+="0A00"+equipment_base.getChannel4();
        }
        if(equipment_base.getWorkStyle()!=null){
            mainBody+="0C08"+equipment_base.getWorkStyle();
        }
        if(equipment_base.getColletKey()!=null){
            mainBody+="0D40"+equipment_base.getColletKey();
        }
        length = Integer.toHexString(mainBody.length()/2).toUpperCase();
        if(length.length()==1){
            length="0"+length;
        }
        String head = header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02";
        String end = "05";
        String respose=head+mainBody+end;
        String myCrc = null;
        try {
            myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
            respose=respose+myCrc;
            OutMessage outMessage = new OutMessage();
            outMessage.setId(IDUtils.createUUID());
            outMessage.setSendtime(new Date());
            outMessage.setFlag(flag);
            outMessage.setFunctioncode(functionCode);
            outMessage.setMessage(respose);
            outMessage.setTelemetryaddress(telemetryAddress);
            outMessageMapper.insertSelective(outMessage);
            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            logger.info("发送报文成功，功能码:{},报文：{}",functionCode,respose);
            return outMessage;
        } catch (DecoderException e) {
            e.printStackTrace();
            logger.info("发送报文失败，功能码{}，报文：{}",functionCode,respose);
            return null;
        }
    }

    @Override
    public OutMessage queryConfiguration(ChannelHandlerContext ctx, equipment_base equipment_base) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header ="7E7E";//帧起始符
        String centerAddress =equipment_base.getCenterAdress();//中心站地址
        String telemetryAddress =equipment_base.getTelemetryAddress();//遥测站地址
        String password = equipment_base.getPassword();//密码
        String functionCode = "41";//功能码
        String length = "";//长度
        String flag = "0000";//消息流水
        String mainBody = flag+time;//报文正文
        if(equipment_base.getCenterAdress4()!=null){
            mainBody+="0120";
        }
        if(equipment_base.getChannel1()!=null){
            mainBody+="0400";
        }
        if(equipment_base.getChannel2()!=null){
            mainBody+="0600";
        }
        if(equipment_base.getChannel3()!=null){
            mainBody+="0800";
        }
        if(equipment_base.getChannel4()!=null){
            mainBody+="0A00";
        }
        if(equipment_base.getWorkStyle()!=null){
            mainBody+="0C08";
        }
        if(equipment_base.getColletKey()!=null){
            mainBody+="0D40";
        }
        length = Integer.toHexString(mainBody.length()/2).toUpperCase();
        if(length.length()==1){
            length="0"+length;
        }
        String head = header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02";
        String end = "05";
        String respose=head+mainBody+end;
        String myCrc = null;
        try {
            myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
            respose=respose+myCrc;
            OutMessage outMessage = new OutMessage();
            outMessage.setId(IDUtils.createUUID());
            outMessage.setSendtime(new Date());
            outMessage.setFlag(flag);
            outMessage.setFunctioncode(functionCode);
            outMessage.setMessage(respose);
            outMessage.setTelemetryaddress(telemetryAddress);
            outMessageMapper.insertSelective(outMessage);
            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            logger.info("发送报文成功，功能码:{},报文：{}",functionCode,respose);
            return outMessage;
        } catch (DecoderException e) {
            e.printStackTrace();
            logger.info("发送报文失败，功能码{}，报文：{}",functionCode,respose);
            return null;
        }
    }

    @Override
    public OutMessage modifyRunningParam(ChannelHandlerContext ctx, equipment_base equipment_base) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header ="7E7E";//帧起始符
        String centerAddress =equipment_base.getCenterAdress();//中心站地址
        String telemetryAddress =equipment_base.getTelemetryAddress();//遥测站地址
        String password = equipment_base.getPassword();//密码
        String functionCode = "42";//功能码
        String length = "";//长度
        String flag = "0000";//消息流水
        String mainBody = flag+time;//报文正文
        if(equipment_base.getTimeIdentifer()!=null){
            mainBody+="2008"+equipment_base.getTimeIdentifer();
        }
        if(equipment_base.getHeartBeat()!=null){
            mainBody+="FF0008"+equipment_base.getHeartBeat();
        }
        if(equipment_base.getWaterLimit()!=null){
            mainBody+="FF0120"+equipment_base.getWaterLimit();
        }
        if(equipment_base.getBalanceLimit()!=null){
            mainBody+="FF0210"+equipment_base.getBalanceLimit();
        }
        if(equipment_base.getWaterPrice()!=null){
            mainBody+="FF0312"+equipment_base.getWaterPrice();
        }
        if(equipment_base.getPowerPrice()!=null){
            mainBody+="FF0412"+equipment_base.getPowerPrice();
        }
        if(equipment_base.getFlowmeterProtocol()!=null){
            mainBody+="FF0508"+equipment_base.getFlowmeterProtocol();
        }
        if(equipment_base.getColletCycle()!=null){
            mainBody+="FF0610"+equipment_base.getColletCycle();
        }
        if(equipment_base.getEnergyProtocol()!=null){
            mainBody+="FF0708"+equipment_base.getEnergyProtocol();
        }
        if(equipment_base.getEnergyCycle()!=null){
            mainBody+="FF0810"+equipment_base.getEnergyCycle();
        }
        if(equipment_base.getBillingMethod()!=null){
            mainBody+="FF0908"+equipment_base.getBillingMethod();
        }
        length = Integer.toHexString(mainBody.length()/2).toUpperCase();
        if(length.length()==1){
            length="0"+length;
        }
        String head = header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02";
        String end = "05";
        String respose=head+mainBody+end;
        String myCrc = null;
        try {
            myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
            respose=respose+myCrc;
            OutMessage outMessage = new OutMessage();
            outMessage.setId(IDUtils.createUUID());
            outMessage.setSendtime(new Date());
            outMessage.setFlag(flag);
            outMessage.setFunctioncode(functionCode);
            outMessage.setMessage(respose);
            outMessage.setTelemetryaddress(telemetryAddress);
            outMessageMapper.insertSelective(outMessage);
            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            logger.info("发送报文成功，功能码:{},报文：{}",functionCode,respose);
            return outMessage;
        } catch (DecoderException e) {
            e.printStackTrace();
            logger.info("发送报文失败，功能码{}，报文：{}",functionCode,respose);
            return null;
        }
    }

    @Override
    public OutMessage queryRunningParam(ChannelHandlerContext ctx, equipment_base equipment_base) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header ="7E7E";//帧起始符
        String centerAddress =equipment_base.getCenterAdress();//中心站地址
        String telemetryAddress =equipment_base.getTelemetryAddress();//遥测站地址
        String password = equipment_base.getPassword();//密码
        String functionCode = "43";//功能码
        String length = "";//长度
        String flag = "0000";//消息流水
        String mainBody = flag+time;//报文正文
        if(equipment_base.getTimeIdentifer()!=null){
            mainBody+="2008";
        }
        if(equipment_base.getHeartBeat()!=null){
            mainBody+="FF0008";
        }
        if(equipment_base.getWaterLimit()!=null){
            mainBody+="FF0120";
        }
        if(equipment_base.getBalanceLimit()!=null){
            mainBody+="FF0210";
        }
        if(equipment_base.getWaterPrice()!=null){
            mainBody+="FF0312";
        }
        if(equipment_base.getPowerPrice()!=null){
            mainBody+="FF0412";
        }
        if(equipment_base.getFlowmeterProtocol()!=null){
            mainBody+="FF0508";
        }
        if(equipment_base.getColletCycle()!=null){
            mainBody+="FF0610";
        }
        if(equipment_base.getEnergyProtocol()!=null){
            mainBody+="FF0708";
        }
        if(equipment_base.getEnergyCycle()!=null){
            mainBody+="FF0810";
        }
        if(equipment_base.getBillingMethod()!=null){
            mainBody+="FF0908";
        }
        length = Integer.toHexString(mainBody.length()/2).toUpperCase();
        if(length.length()==1){
            length="0"+length;
        }
        String head = header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02";
        String end = "05";
        String respose=head+mainBody+end;
        String myCrc = null;
        try {
            myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
            respose=respose+myCrc;
            OutMessage outMessage = new OutMessage();
            outMessage.setId(IDUtils.createUUID());
            outMessage.setSendtime(new Date());
            outMessage.setFlag(flag);
            outMessage.setFunctioncode(functionCode);
            outMessage.setMessage(respose);
            outMessage.setTelemetryaddress(telemetryAddress);
            outMessageMapper.insertSelective(outMessage);
            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            logger.info("发送报文成功，功能码:{},报文：{}",functionCode,respose);
            return outMessage;
        } catch (DecoderException e) {
            e.printStackTrace();
            logger.info("发送报文失败，功能码{}，报文：{}",functionCode,respose);
            return null;
        }
    }

    @Override
    public OutMessage remotePump(ChannelHandlerContext ctx, equipment_base equipment_base) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header ="7E7E";//帧起始符
        String centerAddress =equipment_base.getCenterAdress();//中心站地址
        String telemetryAddress =equipment_base.getTelemetryAddress();//遥测站地址
        String password = equipment_base.getPassword();//密码
        String functionCode = "4C";//功能码
        String length = "";//长度
        String flag = "0000";//消息流水
        String mainBody = flag+time;//报文正文
        if(equipment_base.getPumpIsopen()!=null){
            mainBody+="FF1008"+equipment_base.getPumpIsopen();
        }
        length = Integer.toHexString(mainBody.length()/2).toUpperCase();
        if(length.length()==1){
            length="0"+length;
        }
        String head = header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02";
        String end = "05";
        String respose=head+mainBody+end;
        String myCrc = null;
        try {
            myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
            respose=respose+myCrc;
            OutMessage outMessage = new OutMessage();
            outMessage.setId(IDUtils.createUUID());
            outMessage.setSendtime(new Date());
            outMessage.setFlag(flag);
            outMessage.setFunctioncode(functionCode);
            outMessage.setMessage(respose);
            outMessage.setTelemetryaddress(telemetryAddress);
            outMessageMapper.insertSelective(outMessage);
            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            logger.info("发送报文成功，功能码:{},报文：{}",functionCode,respose);
            return outMessage;
        } catch (DecoderException e) {
            e.printStackTrace();
            logger.info("发送报文失败，功能码{}，报文：{}",functionCode,respose);
            return null;
        }
    }

    @Override
    public OutMessage initRegister(ChannelHandlerContext ctx, equipment_base equipment_base) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header ="7E7E";//帧起始符
        String centerAddress =equipment_base.getCenterAdress();//中心站地址
        String telemetryAddress =equipment_base.getTelemetryAddress();//遥测站地址
        String password = equipment_base.getPassword();//密码
        String functionCode = "47";//功能码
        String length = "";//长度
        String flag = "0000";//消息流水
        String mainBody = flag+time;//报文正文
        length = Integer.toHexString(mainBody.length()/2).toUpperCase();
        if(length.length()==1){
            length="0"+length;
        }
        String head = header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02";
        String end = "05";
        String respose=head+mainBody+end;
        String myCrc = null;
        try {
            myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
            respose=respose+myCrc;
            OutMessage outMessage = new OutMessage();
            outMessage.setId(IDUtils.createUUID());
            outMessage.setSendtime(new Date());
            outMessage.setFlag(flag);
            outMessage.setFunctioncode(functionCode);
            outMessage.setMessage(respose);
            outMessage.setTelemetryaddress(telemetryAddress);
            outMessageMapper.insertSelective(outMessage);
            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            logger.info("发送报文成功，功能码:{},报文：{}",functionCode,respose);
            return outMessage;
        } catch (DecoderException e) {
            e.printStackTrace();
            logger.info("发送报文失败，功能码{}，报文：{}",functionCode,respose);
            return null;
        }
    }

}
