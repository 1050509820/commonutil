package com.dyzhsw.cardcontrol.controller;


import com.dyzhsw.cardcontrol.cache.ChannelHandlerContextThreadLocal.*;
import com.dyzhsw.cardcontrol.dto.OutMessage;
import com.dyzhsw.cardcontrol.dto.StationTime;
import com.dyzhsw.cardcontrol.mapper.StationTimeMapper;
import com.dyzhsw.cardcontrol.resolver.LinkMaintenanceResolver;
import com.dyzhsw.cardcontrol.response.ResultObject;
import com.dyzhsw.cardcontrol.service.InquireService;
import com.dyzhsw.cardcontrol.util.CRC8;
import com.dyzhsw.cardcontrol.util.FormatUtils;
import com.dyzhsw.cardcontrol.util.equipment_base;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.swagger.annotations.*;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@Api(value="SendController",tags={"中心站主动查询修改接口"})
public class SendController {
    @Autowired
    private InquireService inquireService;
    private static Logger logger = LoggerFactory.getLogger(SendController.class);

    @ApiOperation(value = "inquireTiming", notes = "中心站查询遥测站实时值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "通道id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress", value = "中心站地址1字节", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping("inquireTiming")
    public ResultObject InquireTiming(equipment_base equipment_base) throws DecoderException, InterruptedException {
        ChannelHandlerContext ctx= LocalVar.GETINSTANCE.get().get(equipment_base.getKey());
        if(ctx==null){
            return ResultObject.fail("500","设备未连接!");
        }
        OutMessage outMessage = inquireService.inquireTiming(ctx,equipment_base);
        if(outMessage!=null){
                return ResultObject.success("result",outMessage);
        }else{
            return ResultObject.fail("500","报文发送失败!");
        }

    }

    @ApiOperation(value = "inquireWarning", notes = "中心站查询遥测站状态和报警信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "通道id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress", value = "中心站地址1字节", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping("inquireWarning")
    public ResultObject inquireWarning(equipment_base equipment_base) throws DecoderException{
        ChannelHandlerContext ctx= LocalVar.GETINSTANCE.get().get(equipment_base.getKey());
        if(ctx==null){
            return ResultObject.fail("500","设备未连接");
        }
        OutMessage outMessage = inquireService.inquireWarning(ctx,equipment_base);
        if(outMessage != null){
            return ResultObject.success("result",outMessage);
        }else{
            return ResultObject.fail("500","报文发送失败!");
        }
    }

    @ApiOperation(value = "inquirePump", notes = "中心站查询遥测站开关泵记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "通道id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress", value = "中心站地址1字节", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "mark", value = "开关泵数据批次标识符", required = true, dataType = "String"),
    })
    @PostMapping("inquirePump")
    public ResultObject inquirePump(equipment_base equipment_base,String mark) throws DecoderException{
        ChannelHandlerContext ctx= LocalVar.GETINSTANCE.get().get(equipment_base.getKey());
        if(ctx==null){
            return ResultObject.fail("500","设备未连接");
        }
        if(mark!=null&&mark.length()<6){
            mark= FormatUtils.addZero(mark,6);
        }
        OutMessage outMessage = inquireService.inquirePump(ctx,equipment_base,mark);
        if(outMessage!=null){
            return ResultObject.success("result",outMessage);
        }else{
            return ResultObject.fail("500","报文发送失败!");
        }
    }

    @ApiOperation(value = "setClock", notes = "中心站设置遥测站时钟")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "通道id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress", value = "中心站地址1字节", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
    })
    @PostMapping("setClock")
    public ResultObject setClock(equipment_base equipment_base){
        ChannelHandlerContext ctx= LocalVar.GETINSTANCE.get().get(equipment_base.getKey());
        if(ctx==null){
            return ResultObject.fail("500","设备未连接");
        }
        OutMessage outMessage = inquireService.setClock(ctx,equipment_base);
        if(outMessage!=null){
            return ResultObject.success("result",outMessage);
        }else{
            return ResultObject.fail("500","报文发送失败!");
        }
    }

    @ApiOperation(value = "queryClock", notes = "中心站查询遥测站时钟")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "通道id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress", value = "中心站地址1字节", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
    })
    @PostMapping("queryClock")
    public ResultObject queryClock(equipment_base equipment_base){
        ChannelHandlerContext ctx= LocalVar.GETINSTANCE.get().get(equipment_base.getKey());
        if(ctx==null){
            return ResultObject.fail("500","设备未连接");
        }
        OutMessage outMessage = inquireService.queryClock(ctx,equipment_base);
        if(outMessage != null){
            return ResultObject.success("result",outMessage);
        }else{
            return ResultObject.fail("500","报文发送失败!");
        }
    }


    @ApiOperation(value = "modifyConfiguration", notes = "中心站修改遥测站基本配置参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "通道id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress", value = "中心站地址1字节", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress4", value = "中心站地址4字节", required = false, dataType = "String"),
            @ApiImplicitParam(name = "channel1", value = "中心站1主信道及地址标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "channel2", value = "中心站2主信道及地址标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "channel3", value = "中心站3主信道及地址标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "channel4", value = "中心站4主信道及地址标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "workStyle", value = "工作方式标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "colletKey", value = "采集要素", required = false, dataType = "String"),

    })
    @PostMapping("modifyConfiguration")
    public ResultObject modifyConfiguration(equipment_base equipment_base){
        ChannelHandlerContext ctx= LocalVar.GETINSTANCE.get().get(equipment_base.getKey());
        if(ctx==null){
            return ResultObject.fail("500","设备未连接");
        }
        equipment_base = FormatUtils.replaceParam(equipment_base);
        logger.info(equipment_base.toString());
        OutMessage outMessage=inquireService.modifyConfiguration(ctx,equipment_base);
        if(outMessage!=null){
            return ResultObject.success("result",outMessage);
        }else{
            return ResultObject.fail("500","报文发送失败!");
        }
    }

    @ApiOperation(value = "queryConfiguration", notes = "中心站查询遥测站基本配置参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "通道id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress", value = "中心站地址1字节", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress4", value = "中心站地址4字节", required = false, dataType = "String"),
            @ApiImplicitParam(name = "channel1", value = "中心站1主信道及地址标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "channel2", value = "中心站2主信道及地址标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "channel3", value = "中心站3主信道及地址标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "channel4", value = "中心站4主信道及地址标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "workStyle", value = "工作方式标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "colletKey", value = "采集要素", required = false, dataType = "String"),

    })
    @PostMapping("queryConfiguration")
    public ResultObject queryConfiguration(equipment_base equipment_base){
        ChannelHandlerContext ctx= LocalVar.GETINSTANCE.get().get(equipment_base.getKey());
        if(ctx==null){
            return ResultObject.fail("500","设备未连接");
        }
        OutMessage outMessage = inquireService.queryConfiguration(ctx,equipment_base);
        if(outMessage!=null){
            return ResultObject.success("result",outMessage);
        }else{
            return ResultObject.fail("500","报文发送失败!");
        }
    }


    @ApiOperation(value = "modifyRunningParam", notes = "中心站修改遥测站运行参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "通道id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress", value = "中心站地址1字节", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeIdentifer", value = "定时时间标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "heartBeat", value = "心跳间隔", required = false, dataType = "String"),
            @ApiImplicitParam(name = "waterLimit", value = "年机井用水量上限标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "balanceLimit", value = "用水户余额报警下限标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "waterPrice", value = "水价标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "powerPrice", value = "电价标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "flowmeterProtocol", value = "流量计协议标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "colletCycle", value = "流量计采集周期标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "energyProtocol", value = "电能表协议标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "energyCycle", value = "电能表采集周期标识符", required = false, dataType = "String"),

    })
    @PostMapping("modifyRunningParam")
    public ResultObject modifyRunningParam(equipment_base equipment_base){
        ChannelHandlerContext ctx= LocalVar.GETINSTANCE.get().get(equipment_base.getKey());
        if(ctx==null){
            return ResultObject.fail("500","设备未连接");
        }
        equipment_base = FormatUtils.replaceParam(equipment_base);
        logger.info(equipment_base.toString());
        OutMessage outMessage = inquireService.modifyRunningParam(ctx,equipment_base);
        if(outMessage != null){
            return ResultObject.success("result",outMessage);
        }else{
            return ResultObject.fail("500","报文发送失败!");
        }
    }

    @ApiOperation(value = "queryRunningParam", notes = "中心站查询遥测站运行参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "通道id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress", value = "中心站地址1字节", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeIdentifer", value = "定时时间标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "heartBeat", value = "心跳间隔", required = false, dataType = "String"),
            @ApiImplicitParam(name = "waterLimit", value = "年机井用水量上限标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "balanceLimit", value = "用水户余额报警下限标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "waterPrice", value = "水价标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "powerPrice", value = "电价标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "flowmeterProtocol", value = "流量计协议标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "colletCycle", value = "流量计采集周期标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "energyProtocol", value = "电能表协议标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "energyCycle", value = "电能表采集周期标识符", required = false, dataType = "String"),
            @ApiImplicitParam(name = "billingMethod", value = "计费方式", required = false, dataType = "String"),

    })
    @PostMapping("queryRunningParam")
    public ResultObject queryRunningParam(equipment_base equipment_base){
        ChannelHandlerContext ctx= LocalVar.GETINSTANCE.get().get(equipment_base.getKey());
        if(ctx==null){
            return ResultObject.fail("500","设备未连接");
        }
        OutMessage outMessage = inquireService.queryRunningParam(ctx,equipment_base);
        if(outMessage != null){
            return ResultObject.success("result",outMessage);
        }else{
            return ResultObject.fail("500","报文发送失败!");
        }
    }

    @ApiOperation(value = "remotePump", notes = "中心站遥控遥测站水泵启停")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "通道id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress", value = "中心站地址1字节", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pumpIsopen", value = "水泵开关控制标识符", required = true, dataType = "String"),
    })
    @PostMapping("remotePump")
    public ResultObject remotePump(equipment_base equipment_base){
        ChannelHandlerContext ctx= LocalVar.GETINSTANCE.get().get(equipment_base.getKey());
        if(ctx==null){
            return ResultObject.fail("500","设备未连接");
        }
        OutMessage outMessage = inquireService.remotePump(ctx,equipment_base);
        if(outMessage != null){
            return ResultObject.success("result",outMessage);
        }else{
            return ResultObject.fail("500","报文发送失败!");
        }
    }

    @ApiOperation(value = "initRegister", notes = "中心站初始化遥测站固态寄存器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "通道id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "centerAdress", value = "中心站地址1字节", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
    })
    @PostMapping("initRegister")
    public ResultObject initRegister(equipment_base equipment_base){
        ChannelHandlerContext ctx= LocalVar.GETINSTANCE.get().get(equipment_base.getKey());
        if(ctx==null){
            return ResultObject.fail("500","设备未连接");
        }
        OutMessage outMessage = inquireService.initRegister(ctx,equipment_base);
        if(outMessage!=null){
            return ResultObject.success("result",outMessage);
        }else{
            return ResultObject.fail("500","报文发送失败!");
        }
    }

}
