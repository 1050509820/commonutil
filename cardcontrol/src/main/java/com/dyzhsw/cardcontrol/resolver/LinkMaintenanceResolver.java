package com.dyzhsw.cardcontrol.resolver;


import com.dyzhsw.cardcontrol.dto.Message;
import com.dyzhsw.cardcontrol.dto.equipment;
import com.dyzhsw.cardcontrol.mapper.MessageMapper;
import com.dyzhsw.cardcontrol.mapper.equipmentMapper;
import com.dyzhsw.cardcontrol.message.MessageResolver;
import com.dyzhsw.cardcontrol.util.CRC8;
import com.dyzhsw.cardcontrol.response.ResponseUtil;
import com.dyzhsw.cardcontrol.util.IDUtils;
import com.dyzhsw.cardcontrol.util.SaveUtil;
import com.dyzhsw.cardcontrol.util.StringReplaceUtils;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 链路维持解析
 */
@Component
public class LinkMaintenanceResolver implements MessageResolver {
    private static Logger logger = LoggerFactory.getLogger(LinkMaintenanceResolver.class);
    @Autowired
    private equipmentMapper equipmentMapper;
    @Autowired
    private MessageMapper messageMapper;


    @Override
    public List<String> resolver(String afn, ChannelHandlerContext ctx, String msg){
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        try {
            String time = sdf.format(date);
            String header = msg.substring(0, 4);//帧起始符
            String centerAddress = msg.substring(4, 6);//中心站地址
            String telemetryAddress = msg.substring(6, 16);//遥测站地址
            String password = msg.substring(16, 20);//密码
            String functionCode = msg.substring(20, 22);//功能码
            String crc = msg.substring(msg.length() - 4, msg.length());
            String msgData = msg.substring(28, msg.length() - 6);
            String flag = msg.substring(28, 32);//消息流水
            String length = msg.substring(24, 26);
            String sendTime = msg.substring(32,44);
            String myCrc = CRC8.CRC16(CRC8.convertHexToByte(msg.substring(0,msg.length()-4)));
            if(myCrc.equals(crc)){//CRC校验
                this.setData(centerAddress,telemetryAddress,password,flag,ctx);
                SaveUtil.insertCommon(functionCode,msg,telemetryAddress,
                         flag, sendTime, centerAddress, password, "");

                ResponseUtil.convertAndFlush(ctx, msg);
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("解析报文出错，报文：",msg);
            return null;
        }
        return list;
    }
    //保存连接信息
    public  void setData(String centerAddress,String telemetryAddress,String password,String flag,ChannelHandlerContext ctx){
        equipment equipment = new equipment();
        equipment.setTelemetryaddress(telemetryAddress);
        equipment equipment1=equipmentMapper.selectBytelemetryAddress(telemetryAddress);
        if(equipment1!=null){
            equipment.setId(equipment1.getId());
            equipment.setPassword(password);
            equipment.setIsonlie("1");
            equipment.setUpdatetime(new Date());
            equipment.setCenteradress(centerAddress);
            equipment.setChannel(StringReplaceUtils.replace(ctx.channel().id().asLongText(), "-", ""));
            equipmentMapper.updateByPrimaryKeySelective(equipment);
        }else{
            equipment.setId(IDUtils.createUUID());
            equipment.setPassword(password);
            equipment.setIsonlie("1");
            equipment.setCenteradress(centerAddress);
            equipment.setUpdatetime(new Date());
            equipment.setCreatetime(new Date());
            equipment.setChannel(StringReplaceUtils.replace(ctx.channel().id().asLongText(), "-", ""));
            equipmentMapper.insertSelective(equipment);
        }

    }
}

