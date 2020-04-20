package com.dyzhsw.cardcontrol.resolver;

import com.dyzhsw.cardcontrol.util.FormatUtils;

import java.util.*;

/**
 * 配置信息的解析，不定长
 */
public class ResolverConfigurationMsg  {
    public static Map<String,Object> resolver(String msg) {
        Map<String, Object> map = new HashMap<>();
        String[] param ={"0400","0600","0800","0A00",};
        Map<String,Integer> map1 = new HashMap<>();
        if(msg.indexOf("0120")>-1){
            map.put("0120",msg.substring(msg.indexOf("0120")+4,msg.indexOf("0120")+12));
            msg=msg.replaceAll("0120"+map.get("0120"),"").trim();
        }
        if(msg.indexOf("0C08")>-1){
            map.put("0C08",msg.substring(msg.indexOf("0C08")+4,msg.indexOf("0C08")+6));
            msg=msg.replaceAll("0C08"+map.get("0C08"),"").trim();
        }
        if(msg.indexOf("0D40")>-1){
            map.put("0D40",msg.substring(msg.indexOf("0D40")+4,msg.indexOf("0D40")+20));
            msg=msg.replaceAll("0D40"+map.get("0D40"),"").trim();
        }
        for(int i=0;i<param.length;i++){
            if(msg.indexOf(param[i])>-1){
                map1.put(param[i],msg.indexOf(param[i]));
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map1.entrySet());
        //升序排序
        Collections.sort(list, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        for(int j=0;j<list.size();j++){
            Map.Entry<String,Integer> beginMap = list.get(j);
            if(j<list.size()-1){
                Map.Entry<String,Integer> endMap = list.get(j+1);
                map.put(beginMap.getKey(),msg.substring(beginMap.getValue()+4,endMap.getValue()));
            }else{
                map.put(beginMap.getKey(),msg.substring(beginMap.getValue()+4,msg.length()));
            }
        }

        Map map2 = FormatUtils.format(map);
        return map2;
    }
}
