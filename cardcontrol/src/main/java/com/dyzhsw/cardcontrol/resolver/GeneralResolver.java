package com.dyzhsw.cardcontrol.resolver;

import com.dyzhsw.cardcontrol.dto.*;
import com.dyzhsw.cardcontrol.mapper.*;
import com.dyzhsw.cardcontrol.message.MessageResolver;
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
 * 通用查询回复解析解析，不用回报文
 */
@Component
public class GeneralResolver implements MessageResolver {
    @Autowired
    private StationTimeMapper stationTimeMapper;
    @Autowired
    private StationAddMapper stationAddMapper;
    @Autowired
    private StationClockMapper stationClockMapper;
    @Autowired
    private ConfigurationMapper configurationMapper;
    @Autowired
    private StationModifyMapper stationModifyMapper;

    private static Logger logger = LoggerFactory.getLogger(LinkMaintenanceResolver.class);
    @Override
    public List<String> resolver(String afn, ChannelHandlerContext ctx, String msg) throws DecoderException {
        List<String> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(); //上传参数值
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
            String F1F1 ="";  // 遥测站地址标识符
            if("4A".equals(functionCode)||"51".equals(functionCode)||"40".equals(functionCode)||
            "41".equals(functionCode)||"42".equals(functionCode)||"43".equals(functionCode)||"47".equals(functionCode)||"4C".equals(functionCode)){
                F1F1 = msgData.substring(4,14);
                msgData = msgData.substring(14,msgData.length());
            }
            if(myCrc.equals(crc)){//CRC校验
                    if("40".equals(functionCode)||"41".equals(functionCode)){//配置参数的解析
                        map=ResolverConfigurationMsg.resolver(msgData);
                    }else{
                        map = ResolverMsgData.resolver(msgData);//上传数值map通用解析
                    }
                    if(map.get("遥测站地址")==null){
                        map.put("遥测站地址",F1F1);
                    }
                    map.put("头部中心站地址",centerAddress);
                    map.put("头部遥测站地址",telemetryAddress);
                    map.put("密码",password);
                    map.put("功能码",functionCode);
                    map.put("消息流水",flag);
                    map.put("发报时间",time);
                    map.put("报文长度",length);
                    SaveUtil.insertCommon(functionCode,msg,telemetryAddress,flag,time,centerAddress,password,map.toString());
                    if("37".equals(functionCode)){//将遥测站实时值插入定时报表
                        this.insertStationTime(telemetryAddress,centerAddress,password,flag,functionCode,time,map);
                    }else if("46".equals(functionCode)){
                        this.insertStationStatus(telemetryAddress,centerAddress,password,flag,functionCode,time,map);
                    }else if("40".equals(functionCode)||"41".equals(functionCode)){
                        this.insertConfigeration(telemetryAddress,centerAddress,password,flag,functionCode,time,map);
                    }else if ("42".equals(functionCode)||"43".equals(functionCode)){
                        this.insertStationModify(telemetryAddress,centerAddress,password,flag,functionCode,time,map);
                    }else if("4A".equals(functionCode)||"51".equals(functionCode)||"47".equals(functionCode)||"4C".equals(functionCode)){
                        this.insertStationClock(telemetryAddress,centerAddress,password,flag,functionCode,time,map);
                    }
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("解析报文出错，报文{}",msg);
            return null;
        }
        return list;
    }

    public void  insertStationModify(String telemetryAddress,String centerAddress,String password,String flag,String functionCode,String sendTime,Map map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
        SimpleDateFormat sdf1 =  new SimpleDateFormat("yyMMddhhmm");
        StationModify stationModify = new StationModify();
        stationModify.setId(IDUtils.createUUID());
        stationModify.setTelemetryaddress(telemetryAddress);
        stationModify.setCenteradress(centerAddress);
        stationModify.setPassword(password);
        stationModify.setSerialnumber(flag);
        stationModify.setFuctioncode(functionCode);
        stationModify.setSendtime(sdf.parse(sendTime));
        stationModify.setCreatetime(new Date());

        stationModify.setBillingmethod((String) map.get("计费方式"));
        stationModify.setFlowmeterprotocol((String) map.get("流量计协议"));
        stationModify.setColletcycle((String) map.get("流量计采集周期"));
        stationModify.setEnergycycle((String) map.get("计费方式"));
        stationModify.setBalancelimit((String) map.get("年机井用水量下限"));
        stationModify.setHeartbeat((String) map.get("CSQ信号强度"));
        stationModify.setEnergyprotocol((String) map.get("电能表协议"));
        stationModify.setPowerprice((String) map.get("电价"));
        stationModify.setWaterlimit((String) map.get("年机井用水量上限"));
        stationModify.setWaterprice((String) map.get("水价"));
        stationModify.setTimeidentifer((String) map.get("定时报时间间隔"));
        stationModify.setResolve(map.toString());
        stationModifyMapper.insertSelective(stationModify);
    }

    public void  insertStationTime(String telemetryAddress,String centerAddress,String password,String flag,String functionCode,String sendTime,Map map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
        SimpleDateFormat sdf1 =  new SimpleDateFormat("yyMMddhhmm");
        StationTime stationTime = new StationTime();
        stationTime.setId(IDUtils.createUUID());
        stationTime.setTelemetryaddress(telemetryAddress);
        stationTime.setCenteradress(centerAddress);
        stationTime.setPassword(password);
        stationTime.setSerialnumber(flag);
        stationTime.setFuctioncode(functionCode);
        stationTime.setSendtime(sdf.parse(sendTime));
        stationTime.setCreatetime(new Date());

        stationTime.setObservationtime(sdf1.parse((String) map.get("观测时间")));
        stationTime.setCumulativeflowrate((String) map.get("累计流量"));
        stationTime.setElectricc((String) map.get("交流C相电流"));
        stationTime.setCumulativetime((String) map.get("累计用时"));
        stationTime.setElectricb((String) map.get("交流B相电流"));
        stationTime.setStatus((String) map.get("遥测站报警"));
        stationTime.setVoltage((String) map.get("电源电压"));
        stationTime.setInstantaneousflowrate((String) map.get("瞬时流量"));
        stationTime.setCsqsignalintensity((String) map.get("CSQ信号强度"));
        stationTime.setTypecode(((String) map.get("遥测站地址")).substring(10,12));
        stationTime.setCumulativeelectricityrate((String) map.get("累计电量"));
        stationTime.setVoltagea((String) map.get("交流A相电压"));
        stationTime.setVoltagec((String) map.get("交流C相电压"));
        stationTime.setVoltageb((String) map.get("交流B相电压"));
        stationTime.setElectrica((String) map.get("交流A相电流"));
        stationTime.setResolve(map.toString());
        stationTimeMapper.insertSelective(stationTime);
    }

    public void  insertStationClock(String telemetryAddress,String centerAddress,String password,String flag,String functionCode,String sendTime,Map map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
        SimpleDateFormat sdf1 =  new SimpleDateFormat("yyMMddhhmm");
        StationClock stationAdd = new StationClock();
        stationAdd.setId(IDUtils.createUUID());
        stationAdd.setTelemetryaddress(telemetryAddress);
        stationAdd.setCenteradress(centerAddress);
        stationAdd.setPassword(password);
        stationAdd.setSerialnumber(flag);
        stationAdd.setFuctioncode(functionCode);
        stationAdd.setSendtime(sdf.parse(sendTime));
        stationAdd.setCreatetime(new Date());
        stationAdd.setResolve(map.toString());
        stationClockMapper.insertSelective(stationAdd);
    }

    public void  insertStationStatus(String telemetryAddress,String centerAddress,String password,String flag,String functionCode,String sendTime,Map map) throws ParseException {
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
        stationAdd.setStatus((String) map.get("遥测站报警"));
        stationAdd.setTypecode(((String) map.get("遥测站地址")).substring(10,12));
        stationAdd.setResolve(map.toString());
        stationAddMapper.insertSelective(stationAdd);
    }


    public void insertConfigeration(String telemetryAddress,String centerAddress,String password,String flag,String functionCode,String sendTime,Map map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
        SimpleDateFormat sdf1 =  new SimpleDateFormat("yyMMddhhmm");
        Configuration configuration =new Configuration();
        configuration.setId(IDUtils.createUUID());
        configuration.setCenteradress(centerAddress);
        configuration.setTelemetryaddress(telemetryAddress);
        configuration.setPassword(password);
        configuration.setFlag(flag);
        configuration.setFunctioncode(functionCode);
        if(sendTime!=null){
            configuration.setSendtime(sdf.parse(sendTime));
        }
        configuration.setCreatetime(new Date());
        configuration.setCenteradress4((String) map.get("中心站地址"));
        configuration.setChanel1((String) map.get("中心站1主信道及地址"));
        configuration.setChanel2((String) map.get("中心站2主信道及地址"));
        configuration.setChanel3((String) map.get("中心站3主信道及地址"));
        configuration.setChanel4((String) map.get("中心站4主信道及地址"));
        configuration.setColletkey((String) map.get("遥测站采集要素"));
        configuration.setWorkstyle((String) map.get("工作方式"));
        configuration.setResolve(map.toString());
        configurationMapper.insertSelective(configuration);
    }

}
