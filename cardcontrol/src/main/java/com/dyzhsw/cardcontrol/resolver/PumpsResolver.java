package com.dyzhsw.cardcontrol.resolver;

import com.dyzhsw.cardcontrol.dto.StationAdd;
import com.dyzhsw.cardcontrol.dto.StationTime;
import com.dyzhsw.cardcontrol.dto.SwitchPump;
import com.dyzhsw.cardcontrol.mapper.SwitchPumpMapper;
import com.dyzhsw.cardcontrol.message.MessageResolver;
import com.dyzhsw.cardcontrol.response.ResponseUtil;
import com.dyzhsw.cardcontrol.util.CRC8;
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
 * 7、中心站查询遥测站开关泵记录
 */
@Component
public class PumpsResolver implements MessageResolver {
    private static Logger logger = LoggerFactory.getLogger(PumpsResolver.class);

    @Autowired
    SwitchPumpMapper switchPumpMapper;

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
            String f1f1=msgData.substring(0,16);
            msgData = msgData.substring(16,msgData.length());
            if(myCrc.equals(crc)){//CRC校验
                Map<String, Object> map = new HashMap<>(); //上传参数值
                if(msgData.length()!=0) {
                    List<Map<String,Object>>  list1 = ResolvePumpsData.resolver(msgData);//上传数值map集合
                    this.insertQueryStation(telemetryAddress,centerAddress,password,flag,functionCode,time,list1);
                }
                SaveUtil.insertCommon(functionCode,msg,telemetryAddress,flag,time,centerAddress,password,map.toString());
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

    public void insertQueryStation(String telemetryAddress,String centerAddress,String password,String flag,String functionCode,String sendTime,List<Map<String,Object>> list) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
        SimpleDateFormat sdf1 =  new SimpleDateFormat("yyMMddhhmm");
        Map<String,Object> map;
        String time;
        Date date = new Date();
        for (int i=0;i<list.size();i++){
            SwitchPump switchPump = new SwitchPump();
            switchPump.setId(IDUtils.createUUID());
            switchPump.setTelemetryaddress(telemetryAddress);
            switchPump.setCenteradress(centerAddress);
            switchPump.setPassword(password);
            switchPump.setSerialnumber(flag);
            switchPump.setFuctioncode(functionCode);
            switchPump.setSendtime(sdf.parse(sendTime));
            switchPump.setCreatetime(date);

            map = list.get(i);
            time = (String) map.get("观测时间");
            switchPump.setObservationtime(!"".equals(time)&&time!=null?sdf1.parse(time):null);
            switchPump.setCumulativeflowrate((String) map.get("累计流量"));
            switchPump.setElectricc((String) map.get("交流C相电流"));
            switchPump.setElectricb((String) map.get("交流B相电流"));
            switchPump.setElectrica((String) map.get("交流A相电流"));
            switchPump.setStatus((String) map.get("遥测站报警"));
            switchPump.setIccard((String) map.get("用水户IC卡地址"));
            switchPump.setElectricityconsumption((String) map.get("累计电量"));
            time = (String) map.get("用水户本次用水结束时间");
            switchPump.setEndtime((!"".equals(time)&&time!=null)?sdf.parse(time):null);
            switchPump.setComtrol((String) map.get("水泵开关控制"));
            switchPump.setConsumption((String) map.get("用水户本次消费金额"));
            switchPump.setVoltagea((String) map.get("交流A相电压"));
            switchPump.setVoltageb((String) map.get("交流B相电压"));
            switchPump.setVoltagec((String) map.get("交流C相电压"));
            switchPump.setComtrol((String) map.get("水泵开关控制"));
            switchPump.setUsetime((String)map.get("用水户本次用水用时"));
            switchPump.setElectricityconsumption((String) map.get("用水户本次用电量"));
            switchPump.setWaterconsumption((String) map.get("用水户本次用水量"));
            time = (String) map.get("用水户本次用水开始时间");
            switchPump.setStarttime((!"".equals(time)&&time!=null)?sdf.parse(time):null);
            switchPump.setResolve(map.toString());
            switchPumpMapper.insertSelective(switchPump);

        }
    }

}
