package com.dyzhsw.cardcontrol.resolver;

import com.dyzhsw.cardcontrol.util.ElementCode;
import com.dyzhsw.cardcontrol.util.FormatUtils;
import com.dyzhsw.cardcontrol.util.StringReplaceUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.*;

public class ResolverMsgData {
    public static Map<String,Object> resolver(String msg){
        Map<String,Object> map = new HashMap<>();
        Map<String,Integer> element = ElementCode.ELEMENT;
        Map<String,String> info = ElementCode.INFO;
        Iterator<Entry<String, Integer>> entries = element.entrySet().iterator();//遍历查找msg中的标识符
        while(entries.hasNext()){//通过2步判断标识符为有效标识符
            Entry<String, Integer> entry = entries.next();
            String key = entry.getKey();//标识符值
            int num = entry.getValue();//标识符长度+内容值长度
            int index;
            int fromIndex=0;
            while((index=msg.indexOf(key,fromIndex))!=-1){
                fromIndex=index+1;
                boolean flag1 = false;
                boolean flag2 = false;
                if(index+num==msg.length()||index==0){//证明标识+内容出现在报文的最后或者首部，为有效标识符
                    map.put(key,msg.substring(index+key.length(),index+num));
                    break;//
                }
                if(index+num>msg.length()){
                    break;
                }
                //否则证明标识符+内容不在报文的最后，需通过遍历index+num后msg的内容
                String afterMsg = msg.substring(index+num,msg.length());
                Iterator<Entry<String, Integer>> entryIterator = element.entrySet().iterator();//通过找到的 标识符的位置+标识符长度+内容长度得到下一个标识符的开始，如果能找到一个//出现位置为0的标识符，证明标识符有效
                 while(entryIterator.hasNext()){
                     Entry<String, Integer> entry1 = entryIterator.next();
                     String key1=entry1.getKey();
                     if(key.equals(key1)){//将自己排除
                         continue;
                     }else{
                         if(afterMsg.indexOf(key1)==0){//除去当前标识符+内容的报文中，如果出现一个标识符在首部，则证明标识符有效
                             flag1=true;
                             break;
                         }
                     }
                 }
                 //检测前面是否有
                Iterator<Entry<String, Integer>> entryIterator1 = element.entrySet().iterator();//通过找到的 标识符的位置+标识符长度+内容长度得到下一个标识符的开始，如果能找到一个//出现位置为0的标识符，证明标识符有效
                while(entryIterator1.hasNext()){
                    Entry<String, Integer> entry1 = entryIterator1.next();
                    String key1=entry1.getKey();
                    int value = entry1.getValue();
                    if(index-entry1.getValue()<0){//位于首位排除
                        continue;
                    }
                    if(key.equals(key1)){//将自己排除
                        continue;
                    }else{
                        if(key1.equals(msg.substring(index-value,index-value+key1.length()))){//除去当前标识符+内容的报文中，如果出现一个标识符在首部，则证明标识符有效
                            flag2=true;
                            break;
                        }
                    }
                }
                if(flag1&&flag2){
                    map.put(key,msg.substring(index+key.length(),index+num));
                     break;
                 }

            }
        }
        Map map1 = FormatUtils.format(map);
        return map1;
    }
}
