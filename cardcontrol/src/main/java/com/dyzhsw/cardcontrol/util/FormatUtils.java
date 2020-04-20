package com.dyzhsw.cardcontrol.util;

import java.util.HashMap;
import java.util.Map;

public class FormatUtils {
    public static Map format(Map map){
        Map info = ElementCode.INFO;
        Map<String,Integer> format = ElementCode.FORMAT;
        Map map1= new HashMap();
        map.forEach((key, value) -> {
            if(format.get(key)!=null&&format.get(key)!=-1&&format.get(key)!=0){
                String val = StringReplaceUtils.del0(value.toString());
                if("".equals(val)||"0".equals(val)){
                    val="0";
                }else {
                    int index = format.get(key);
                    String frontStr = val.substring(0,val.length()-index);
                    String afterStr = val.substring(val.length()-index,val.length());
                    if("".equals(frontStr)){
                        frontStr = "0";
                    }
                    val = frontStr+"."+afterStr;
                }
                map1.put(info.get(key),val);
            }else if(format.get(key)==0){
                String val = StringReplaceUtils.del0(value.toString());
                map1.put(info.get(key),val);
            }else{
                if("0400".equals(key)||"0600".equals(key)||"0800".equals(key)||"0A00".equals(key)){
                    map1.put(info.get(key),resolveAddress(value.toString()));
                }else{
                    map1.put(info.get(key),value);
                }

            }
        });
        return map1;
    }

    public static equipment_base replaceParam(equipment_base equipment_base){
        if(equipment_base.getWorkStyle()!=null&&equipment_base.getWorkStyle().length()<2){
            equipment_base.setWorkStyle(FormatUtils.addZero(equipment_base.getWorkStyle(),2));
        }
        if(equipment_base.getChannel1()!=null){
            equipment_base.setChannel1(FormatUtils.getAddress(equipment_base.getChannel1()));
        }
        if(equipment_base.getChannel2()!=null){
            equipment_base.setChannel2(FormatUtils.getAddress(equipment_base.getChannel2()));
        }
        if(equipment_base.getChannel3()!=null){
            equipment_base.setChannel3(FormatUtils.getAddress(equipment_base.getChannel3()));
        }
        if(equipment_base.getChannel4()!=null){
            equipment_base.setChannel4(FormatUtils.getAddress(equipment_base.getChannel4()));
        }
        if(equipment_base.getBalanceLimit()!=null&&equipment_base.getBalanceLimit().length()<4){
            equipment_base.setBalanceLimit(addZero(equipment_base.getBalanceLimit(),4));
        }
        if(equipment_base.getBillingMethod()!=null&&equipment_base.getBillingMethod().length()<2){
            equipment_base.setBillingMethod(addZero(equipment_base.getBillingMethod(),2));
        }
        if(equipment_base.getEnergyCycle()!=null&&equipment_base.getEnergyCycle().length()<4){
            equipment_base.setEnergyCycle(addZero(equipment_base.getEnergyCycle(),4));
        }
        if(equipment_base.getColletCycle()!=null&&equipment_base.getColletCycle().length()<4){
            equipment_base.setColletCycle(addZero(equipment_base.getColletCycle(),4));
        }
        if(equipment_base.getEnergyProtocol()!=null&&equipment_base.getEnergyProtocol().length()<2){
            equipment_base.setEnergyProtocol(addZero(equipment_base.getEnergyProtocol(),2));
        }
        if(equipment_base.getFlowmeterProtocol()!=null&&equipment_base.getFlowmeterProtocol().length()<2){
            equipment_base.setFlowmeterProtocol(addZero(equipment_base.getFlowmeterProtocol(),2));
        }
        if(equipment_base.getHeartBeat()!=null&&equipment_base.getHeartBeat().length()<2){
            equipment_base.setHeartBeat(addZero(equipment_base.getHeartBeat(),2));
        }
        if(equipment_base.getWaterPrice()!=null&&equipment_base.getWaterPrice().replaceAll("\\.","").length()<4){
            equipment_base.setWaterPrice(addZero(equipment_base.getWaterPrice(),4));
        }
        if(equipment_base.getPowerPrice()!=null&&equipment_base.getPowerPrice().replaceAll("\\.","").length()<4){
            equipment_base.setPowerPrice(addZero(equipment_base.getPowerPrice(),4));
        }
        if(equipment_base.getTimeIdentifer()!=null&&equipment_base.getTimeIdentifer().length()<2){
            equipment_base.setTimeIdentifer(addZero(equipment_base.getTimeIdentifer(),2));
        }
        if(equipment_base.getWaterLimit()!=null&&equipment_base.getWaterLimit().length()<8){
            equipment_base.setWaterLimit(addZero(equipment_base.getWaterLimit(),8));
        }

        return equipment_base;

    }
    public static String addZero(String str,int length){
        str = str.replaceAll("\\.","");
        int j=str.length();
        for(int i=0;i<length-j;i++){
            str="0"+str;
        }
        return str;
    }
    public static String getAddress(String str){
        String res="";
        String front = str.substring(0,str.indexOf(","));
        String after = str.substring(str.indexOf(",")+1);
        res+=FormatUtils.addZero(front,2);
        String ipAddress=after.substring(0,after.lastIndexOf(":"));
        String port = after.substring(after.lastIndexOf(":")+1);
        String[] arrs = ipAddress.split("\\.");
        for(int i=0;i<arrs.length;i++){
            String arr = arrs[i];
            if(arr.length()<3){
                arr=FormatUtils.addZero(arr,3);
            }
            res+=arr;
        }
        res+=FormatUtils.addZero(port,6);
        return res;
    }
    public static String resolveAddress(String str){
        String res="";
        res+=StringReplaceUtils.del0(str.substring(0,2))+",";
        res+=StringReplaceUtils.del0(str.substring(2,5))+".";
        res+=StringReplaceUtils.del0(str.substring(5,8))+".";
        res+=StringReplaceUtils.del0(str.substring(8,11))+".";
        res+=StringReplaceUtils.del0(str.substring(11,14))+":";
        res+=StringReplaceUtils.del0(str.substring(14,20));
        return res;
    }




}
