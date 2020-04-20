package com.dyzhsw.cardcontrol.util;

import com.dyzhsw.cardcontrol.dto.Message;
import com.dyzhsw.cardcontrol.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SaveUtil {
    @Autowired
    private MessageMapper messageMapper;

    private static  SaveUtil saveUtil;

    @PostConstruct
    public void init() {
        saveUtil= this;
        saveUtil.messageMapper= this.messageMapper;
    }
    public static void insertCommon(String functioncode,String msg,String telemetryAddress,
                              String flag,String sendTime,String centerAddress,String password,String resolve) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Message message =new Message();
        message.setId(IDUtils.createUUID());
        message.setCenteraddress(centerAddress);
        message.setCreatetime(new Date());
        message.setTelemetryaddress(telemetryAddress);
        message.setFlag(flag);
        message.setFunctioncode(functioncode);
        message.setMessage(msg);
        message.setPassword(password);
        message.setResolve(resolve);
        message.setSendtime(sdf.parse(sendTime));
        saveUtil.messageMapper.insertSelective(message);
    }

}
