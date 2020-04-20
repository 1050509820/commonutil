package com.dyzhsw.cardcontrol.util;

import java.util.HashMap;
import java.util.Map;

public class ElementCode {
    public static final Map<String,Integer> ELEMENT = new HashMap<String,Integer>();
    public static final Map<String,String> INFO = new HashMap<>();
    public static final Map<String,Integer> FORMAT = new HashMap<>();
    static {
        ELEMENT.put("F0F0",14);//观测时间
        ELEMENT.put("F1F1",16);//遥测站地址
        ELEMENT.put("3812",8);//电源电压
        ELEMENT.put("7011",8);//交流A相电压
        ELEMENT.put("7111",8);//交流B相电压
        ELEMENT.put("7211",8);//交流C相电压
        ELEMENT.put("7311",8);//交流A相电流
        ELEMENT.put("7411",8);//交流B相电流
        ELEMENT.put("7511",8);//交流C相电流
        ELEMENT.put("FF0008",8);//CSQ信号强度
        ELEMENT.put("FF012A",16);//瞬时流量
        ELEMENT.put("FF022A",16);//累计流量
        ELEMENT.put("FF0329",16);//累计电量
        ELEMENT.put("FF0438",20);//累计用时
        ELEMENT.put("FF0528",16);//用水户号
        ELEMENT.put("FF0620",14);//用水户IC卡地址
        ELEMENT.put("FF0722",14);//用水户余额
        ELEMENT.put("FF082A",16);//用水户本次用水量（从开泵到关泵）
        ELEMENT.put("FF0929",16);//用水户本次用电量（从开泵到关泵）
        ELEMENT.put("FF0A28",16);//用水户本次用水用时（从开泵到关泵）
        ELEMENT.put("FF0B30",18);//用水户本次用水开始时间（开泵，年月日时分秒）
        ELEMENT.put("FF0C30",18);//用水户本次用水结束时间（关泵，年月日时分秒）
        ELEMENT.put("FF0D22",14);//用水户本次消费金额（从开泵到关泵）
        ELEMENT.put("FF0E08",8);//本次开关泵类型
        ELEMENT.put("FF0F18",12);//开关泵数据批次（3字节）
        ELEMENT.put("FF1008",8);//水泵开关控制
        ELEMENT.put("4520",12);//遥测站报警
        ELEMENT.put("0120",12);//中心站地址
        ELEMENT.put("0228",14);//遥测站地址
        ELEMENT.put("OC08",6);//工作方式
        //ELEMENT.put("FF0008",8);//心跳，分钟
        ELEMENT.put("FF0120",14);//年机井用水量上限
        ELEMENT.put("FF0210",10);//年机井用水量下限
        ELEMENT.put("FF0312",10);//水价
        ELEMENT.put("FF0412",10);//电价
        ELEMENT.put("FF0508",8);//流量计协议
        ELEMENT.put("FF0610",10);//流量计采集周期
        ELEMENT.put("FF0708",8);//电能表协议
        ELEMENT.put("FF0810",10);//电能表采集周期
        ELEMENT.put("FF0908",8);//计费方式
        ELEMENT.put("2008",6);//定时报时间间隔标识符

    }
    static {
        INFO.put("F0F0","观测时间");//观测时间
        INFO.put("F1F1","遥测站地址");//遥测站地址
        INFO.put("3812","电源电压");//电源电压
        INFO.put("7011","交流A相电压");//交流A相电压
        INFO.put("7111","交流B相电压");//交流B相电压
        INFO.put("7211","交流C相电压");//交流C相电压
        INFO.put("7311","交流A相电流");//交流A相电流
        INFO.put("7411","交流B相电流");//交流B相电流
        INFO.put("7511","交流C相电流");//交流C相电流
        INFO.put("FF0008","CSQ信号强度");//CSQ信号强度
        INFO.put("FF012A","瞬时流量");//瞬时流量
        INFO.put("FF022A","累计流量");//累计流量
        INFO.put("FF0329","累计电量");//累计电量
        INFO.put("FF0438","累计用时");//累计用时
        INFO.put("FF0528","用水户号");//用水户号
        INFO.put("FF0620","用水户IC卡地址");//用水户IC卡地址
        INFO.put("FF0722","用水户余额");//用水户余额
        INFO.put("FF082A","用水户本次用水量");//用水户本次用水量（从开泵到关泵）
        INFO.put("FF0929","用水户本次用电量");//用水户本次用电量（从开泵到关泵）
        INFO.put("FF0A28","用水户本次用水用时");//用水户本次用水用时（从开泵到关泵）
        INFO.put("FF0B30","用水户本次用水开始时间");//用水户本次用水开始时间（开泵，年月日时分秒）
        INFO.put("FF0C30","用水户本次用水结束时间");//用水户本次用水结束时间（关泵，年月日时分秒）
        INFO.put("FF0D22","用水户本次消费金额");//用水户本次消费金额（从开泵到关泵）
        INFO.put("FF0E08","本次开关泵类型");//本次开关泵类型
        INFO.put("FF0F18","开关泵数据批次");//开关泵数据批次（3字节）
        INFO.put("FF1008","水泵开关控制");//水泵开关控制
        INFO.put("4520","遥测站报警");//遥测站报警
        INFO.put("0120","中心站地址");//中心站地址
        INFO.put("0228","遥测站地址");//遥测站地址
        INFO.put("OC08","工作方式");//工作方式
        //ELEMENT.put("FF0008",8);//心跳，分钟
        INFO.put("FF0120","年机井用水量上限");//年机井用水量上限
        INFO.put("FF0210","年机井用水量下限");//年机井用水量下限
        INFO.put("FF0312","水价");//水价
        INFO.put("FF0412","电价");//电价
        INFO.put("FF0508","流量计协议");//流量计协议
        INFO.put("FF0610","流量计采集周期");//流量计采集周期
        INFO.put("FF0708","电能表协议");//电能表协议
        INFO.put("FF0810","电能表采集周期");//电能表采集周期
        INFO.put("FF0908","计费方式");//计费方式
        INFO.put("2008","定时报时间间隔");//定时报时间间隔标识符
        INFO.put("0600","中心站2主信道及地址");
        INFO.put("0400","中心站1主信道及地址");
        INFO.put("0D40","遥测站采集要素");
        INFO.put("0A00","中心站4主信道及地址");
        INFO.put("0C08","工作方式");
        INFO.put("0800","中心站3主信道及地址");



    }

    static {
        FORMAT.put("F0F0",-1);//观测时间
        FORMAT.put("F1F1",-1);//遥测站地址
        FORMAT.put("3812",2);//电源电压
        FORMAT.put("7011",1);//交流A相电压
        FORMAT.put("7111",1);//交流B相电压
        FORMAT.put("7211",1);//交流C相电压
        FORMAT.put("7311",1);//交流A相电流
        FORMAT.put("7411",1);//交流B相电流
        FORMAT.put("7511",1);//交流C相电流
        FORMAT.put("FF0008",-1);//CSQ信号强度
        FORMAT.put("FF012A",2);//瞬时流量
        FORMAT.put("FF022A",2);//累计流量
        FORMAT.put("FF0329",1);//累计电量
        FORMAT.put("FF0438",-1);//累计用时
        FORMAT.put("FF0528",-1);//用水户号
        FORMAT.put("FF0620",-1);//用水户IC卡地址
        FORMAT.put("FF0722",2);//用水户余额
        FORMAT.put("FF082A",2);//用水户本次用水量（从开泵到关泵）
        FORMAT.put("FF0929",1);//用水户本次用电量（从开泵到关泵）
        FORMAT.put("FF0A28",0);//用水户本次用水用时（从开泵到关泵）
        FORMAT.put("FF0B30",-1);//用水户本次用水开始时间（开泵，年月日时分秒）
        FORMAT.put("FF0C30",-1);//用水户本次用水结束时间（关泵，年月日时分秒）
        FORMAT.put("FF0D22",2);//用水户本次消费金额（从开泵到关泵）
        FORMAT.put("FF0E08",-1);//本次开关泵类型
        FORMAT.put("FF0F18",-1);//开关泵数据批次（3字节）
        FORMAT.put("FF1008",-1);//水泵开关控制
        FORMAT.put("4520",-1);//遥测站报警
        FORMAT.put("0120",-1);//中心站地址
        FORMAT.put("0228",-1);//遥测站地址
        FORMAT.put("OC08",-1);//工作方式
        //ELEMENT.put("FF0008",8);//心跳，分钟
        FORMAT.put("FF0120",-1);//年机井用水量上限
        FORMAT.put("FF0210",-1);//年机井用水量下限
        FORMAT.put("FF0312",2);//水价
        FORMAT.put("FF0412",2);//电价
        FORMAT.put("FF0508",-1);//流量计协议
        FORMAT.put("FF0610",-1);//流量计采集周期
        FORMAT.put("FF0708",-1);//电能表协议
        FORMAT.put("FF0810",-1);//电能表采集周期
        FORMAT.put("FF0908",-1);//计费方式
        FORMAT.put("2008",-1);//定时报时间间隔标识符
        FORMAT.put("0600",-1);
        FORMAT.put("0400",-1);
        FORMAT.put("0D40",-1);
        FORMAT.put("0A00",-1);
        FORMAT.put("0800",-1);
        FORMAT.put("0C08",-1);

    }

}
