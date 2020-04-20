package com.dyzhsw.cardcontrol.response;

import com.dyzhsw.cardcontrol.dto.OutMessage;
import com.dyzhsw.cardcontrol.mapper.OutMessageMapper;
import com.dyzhsw.cardcontrol.util.CRC8;
import com.dyzhsw.cardcontrol.util.IDUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ResponseUtil {
    @Autowired
    private  OutMessageMapper outMessageMapper;

    //重点二：建一个静态的本类
    private static ResponseUtil responseUtil;

    //重点三：初始化
    @PostConstruct
    public void init() {
        responseUtil= this;
        responseUtil.outMessageMapper= this.outMessageMapper;
    }

    private static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);
    public static void convertAndFlush(ChannelHandlerContext ctx, String msg) throws DecoderException {
        try {
            System.out.println("等待");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        String header = msg.substring(0,4);//帧起始符
        String centerAddress = msg.substring(4,6);//中心站地址
        String telemetryAddress = msg.substring(6,16);//遥测站地址
        String password = msg.substring(16,20);//密码
        String functionCode = msg.substring(20,22);//功能码
        String length = msg.substring(24,26);//长度
        String crc = msg.substring(msg.length()-4,msg.length());
        String flag = msg.substring(28,32);//消息流水
        String respose=header+telemetryAddress+centerAddress+password+functionCode+"80"+length+"02"+flag+time+"1B";
        String myCrc = CRC8.CRC16(CRC8.convertHexToByte(respose));
        respose=respose+myCrc;
        try {
            ctx.writeAndFlush(Unpooled.copiedBuffer(Hex.decodeHex(respose)));
            insertOutMessage(flag,functionCode,respose,telemetryAddress);
            logger.info("响应功能码{}成功,response:{}",functionCode,respose);
        } catch (DecoderException e) {
            logger.info("发送响应数据失败,msg：{}",msg);
            e.printStackTrace();
        }
    }
    public  static void insertOutMessage(String flag,String functioncode,String msg,String telemetryAddress){
        OutMessage outMessage = new OutMessage();
        outMessage.setId(IDUtils.createUUID());
        outMessage.setFlag(flag);
        outMessage.setFunctioncode(functioncode);
        outMessage.setSendtime(new Date());
        outMessage.setTelemetryaddress(telemetryAddress);
        outMessage.setMessage(msg);
        responseUtil.outMessageMapper.insertSelective(outMessage);
    }



}
