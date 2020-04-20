package com.dyzhsw.cardcontrol.message;

import com.dyzhsw.cardcontrol.resolver.*;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageResolverImpl implements  MessageResolver{
    @Autowired
    private LinkMaintenanceResolver linkMaintenanceResolver;
    @Autowired
    TimingResolver timingResolver;
    @Autowired
    ExtraTimingResolver extraTimingResolver;
    @Autowired
    SwitchResolver switchResolver;
    @Autowired
    GeneralResolver generalResolver;
    @Autowired
    PumpsResolver pumpsResolver;
    @Override
    public List<String> resolver(String afn, ChannelHandlerContext ctx, String message) throws DecoderException {
        List<String> list=new ArrayList<>();
        if("2F".equals(afn)){//链路维持报解析
            return linkMaintenanceResolver.resolver(afn,ctx,message);
        }else if("32".equals(afn)){//遥测站定时报
            return timingResolver.resolver(afn,ctx,message);
        }else if("33".equals(afn)){
            return extraTimingResolver.resolver(afn,ctx,message);
        }else if("E0".equals(afn)){//遥测站开关泵报（E0H）
            return switchResolver.resolver(afn,ctx,message);
        }else if("37".equals(afn)){
            return generalResolver.resolver(afn,ctx,message);
        }else if("46".equals(afn)){
            return generalResolver.resolver(afn,ctx,message);
        }else if("E1".equals(afn)){//查询遥测站开关泵报
            return pumpsResolver.resolver(afn,ctx,message);
        }else if("4A".equals(afn)){
            return generalResolver.resolver(afn,ctx,message);
        }else if("51".equals(afn)){
            return generalResolver.resolver(afn,ctx,message);
        }else if("40".equals(afn)){
            return generalResolver.resolver(afn,ctx,message);
        }else if("41".equals(afn)){
            return generalResolver.resolver(afn,ctx,message);
        }else if("42".equals(afn)){
            return generalResolver.resolver(afn,ctx,message);
        }else if("43".equals(afn)){
            return generalResolver.resolver(afn,ctx,message);
        }else if("4C".equals(afn)){
            return generalResolver.resolver(afn,ctx,message);
        }else if("47".equals(afn)){
            return generalResolver.resolver(afn,ctx,message);
        }
        return null;
    }
}
