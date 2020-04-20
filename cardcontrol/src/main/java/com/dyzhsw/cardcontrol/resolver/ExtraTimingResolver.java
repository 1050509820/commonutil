package com.dyzhsw.cardcontrol.resolver;

import com.dyzhsw.cardcontrol.dto.StationAdd;
import com.dyzhsw.cardcontrol.mapper.StationAddMapper;
import com.dyzhsw.cardcontrol.message.MessageResolver;
import com.dyzhsw.cardcontrol.util.CRC8;
import com.dyzhsw.cardcontrol.response.ResponseUtil;
import com.dyzhsw.cardcontrol.util.IDUtils;
import com.dyzhsw.cardcontrol.util.SaveUtil;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.codec.DecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 额外报解析
 */
@Component
public class ExtraTimingResolver implements MessageResolver {
    @Autowired
    private StationAddMapper stationAddMapper;

    private static Logger logger = LoggerFactory.getLogger(LinkMaintenanceResolver.class);
    @Override
    public List<String> resolver(String afn, ChannelHandlerContext ctx, String msg) throws DecoderException {
        List<String> list = new ArrayList<>();
        try {
            String header = msg.substring(0, 4);//帧起始符
            String centerAddress = msg.substring(4, 6);//中心站地址
            String telemetryAddress = msg.substring(6, 16);//遥测站地址
            String password = msg.substring(16, 20);//密码
            String functionCode = msg.substring(20, 22);//功能码
            String crc = msg.substring(msg.length() - 4, msg.length());//crc
            String length = msg.substring(24, 26);//报文长度
            String flag = msg.substring(28, 32);//消息流水
            String time=msg.substring(32,44);  //发报时间
            String msgData = msg.substring(44, msg.length() - 6);//报文数据 正文发报时间之后-报文结束符之前
            String myCrc = CRC8.CRC16(CRC8.convertHexToByte(msg.substring(0,msg.length()-4)));
            if(myCrc.equals(crc)){//CRC校验
                Map<String, Object> map = new HashMap<>(); //上传参数值
                if(msgData.length()!=0) {
                    map = ResolverMsgData.resolver(msgData);//上传数值map
                }
                SaveUtil.insertCommon(functionCode,msg,telemetryAddress,
                        flag, time, centerAddress, password,map.toString());
                this.setData(telemetryAddress,centerAddress,password,flag,functionCode,time,map);

                ResponseUtil.convertAndFlush(ctx, msg);//发送响应
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
    public void  setData(String telemetryAddress,String centerAddress,String password,String flag,String functionCode,String sendTime,Map map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
        SimpleDateFormat sdf1 =  new SimpleDateFormat("yyMMddhhmm");
        StationAdd stationAdd = new StationAdd();
        stationAdd.setId(IDUtils.createUUID());
        stationAdd.setTelemetryaddress(telemetryAddress);
        stationAdd.setCenteradress(centerAddress);
        stationAdd.setPassword(password);
        stationAdd.setSerialnumber(flag);
        stationAdd.setFuctioncode(functionCode);
        stationAdd.setSendtime(sdf.parse(sendTime));
        stationAdd.setCreatetime(new Date());

        stationAdd.setObservationtime(sdf1.parse((String) map.get("观测时间")));
        stationAdd.setStatus((String) map.get("遥测站报警"));
        stationAdd.setTypecode(((String) map.get("遥测站地址")).substring(10,12));

        stationAddMapper.insertSelective(stationAdd);
    }
}

