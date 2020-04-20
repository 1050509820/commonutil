package com.dyzhsw.cardcontrol.service;

import com.dyzhsw.cardcontrol.dto.OutMessage;
import com.dyzhsw.cardcontrol.util.equipment_base;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.codec.DecoderException;

public interface InquireService {

    /**
     * 查询遥测站实时值
     * @param ctx  通道id
     * @param equipment_base  基础信息
     */
    public OutMessage inquireTiming(ChannelHandlerContext ctx, equipment_base equipment_base);
    /**
     * 查询遥测站状态和报警信息
     * @param ctx  通道id
     * @param equipment_base  基础信息
     */
    public OutMessage inquireWarning(ChannelHandlerContext ctx, equipment_base equipment_base);

    /**
     *
     * @param ctx 通道id
     * @param equipment_base 基础信息
     * @param mark 开关泵数据批次标识符
     */
    public OutMessage inquirePump(ChannelHandlerContext ctx, equipment_base equipment_base,String mark);

    public OutMessage setClock(ChannelHandlerContext ctx,equipment_base equipment_base);

    public OutMessage queryClock(ChannelHandlerContext ctx,equipment_base equipment_base);

    public OutMessage modifyConfiguration(ChannelHandlerContext ctx,equipment_base equipment_base);

    public OutMessage queryConfiguration(ChannelHandlerContext ctx,equipment_base equipment_base);

    public OutMessage modifyRunningParam(ChannelHandlerContext ctx,equipment_base equipment_base);

    public OutMessage queryRunningParam(ChannelHandlerContext ctx,equipment_base equipment_base);

    public OutMessage remotePump(ChannelHandlerContext ctx,equipment_base equipment_base);

    public OutMessage initRegister(ChannelHandlerContext ctx,equipment_base equipment_base);
}
