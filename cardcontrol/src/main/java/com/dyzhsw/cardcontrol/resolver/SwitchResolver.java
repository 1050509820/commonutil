package com.dyzhsw.cardcontrol.resolver;

import com.dyzhsw.cardcontrol.dto.SwitchPump;
import com.dyzhsw.cardcontrol.mapper.SwitchPumpMapper;
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
 * 遥测站开关泵报解析
 */
@Component
public class SwitchResolver implements MessageResolver {

    @Autowired
    private SwitchPumpMapper switchPumpMapper;

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
                map.put("头部中心站地址",centerAddress);
                map.put("头部遥测站地址",telemetryAddress);
                map.put("密码",password);
                map.put("功能码",functionCode);
                map.put("消息流水",flag);
                map.put("发报时间",time);
                map.put("报文长度",length);
                //插入message
                SaveUtil.insertCommon(functionCode,msg,telemetryAddress,
                        flag, time, centerAddress, password,map.toString());
                //插入
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
        SwitchPump switchPump = new SwitchPump();
        switchPump.setId(IDUtils.createUUID());
        switchPump.setTelemetryaddress(telemetryAddress);
        switchPump.setCenteradress(centerAddress);
        switchPump.setPassword(password);
        switchPump.setSerialnumber(flag);
        switchPump.setFuctioncode(functionCode);
        switchPump.setSendtime(sdf.parse(sendTime));
        switchPump.setCreatetime(new Date());
        if(map.get("观测时间")!=null){
            switchPump.setObservationtime(sdf1.parse((String) map.get("观测时间")));
        }
        switchPump.setTypecode(((String)map.get("遥测站地址")).substring(10));
        switchPump.setCumulativeflowrate((String) map.get("累计流量"));
        switchPump.setElectricc((String) map.get("交流C相电流"));
        switchPump.setElectricb((String) map.get("交流B相电流"));
        switchPump.setElectrica((String) map.get("交流A相电流"));
        switchPump.setStatus((String) map.get("遥测站报警"));
        switchPump.setIccard((String) map.get("用水户IC卡地址"));
        switchPump.setUsernumber((String )map.get("用水户号"));
        switchPump.setCumulativeelectricityrate((String) map.get("累计电量"));
        if(map.get("用水户本次用水结束时间")!=null){
            switchPump.setEndtime(sdf.parse((String) map.get("用水户本次用水结束时间")));
        }
        switchPump.setComtrol((String) map.get("水泵开关控制"));
        switchPump.setConsumption((String) map.get("用水户本次消费金额"));
        switchPump.setVoltagea((String) map.get("交流A相电压"));
        switchPump.setVoltageb((String) map.get("交流B相电压"));
        switchPump.setVoltagec((String) map.get("交流C相电压"));
        switchPump.setComtrol((String) map.get("水泵开关控制"));
        switchPump.setElectricityconsumption((String) map.get("用水户本次用电量"));
        switchPump.setWaterconsumption((String) map.get("用水户本次用水量"));
        switchPump.setUsetime((String)map.get("用水户本次用水用时"));
        if(map.get("用水户本次用水开始时间")!=null) {
            switchPump.setStarttime(sdf.parse((String) map.get("用水户本次用水开始时间")));
        }
        switchPump.setType((String) map.get("本次开关泵类型"));
        switchPump.setBalance((String) map.get("用水户余额"));
        switchPump.setResolve(map.toString());
        switchPumpMapper.insertSelective(switchPump);

    }
}
