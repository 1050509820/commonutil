package com.dyzhsw.cardcontrol.resolver;

import com.dyzhsw.cardcontrol.util.ElementCode;
import com.dyzhsw.cardcontrol.util.FormatUtils;
import com.dyzhsw.cardcontrol.util.StringReplaceUtils;

import java.util.*;

public class ResolvePumpsData {
    public static List<Map<String,Object>> resolver(String msgs){
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(new HashMap<String,Object>());//第一条记录
        list.add(new HashMap<String,Object>());//第二条记录
        list.add(new HashMap<String,Object>());//第三条记录
        list.add(new HashMap<String,Object>());//第四条记录
        list.add(new HashMap<String,Object>());//第五条记录
        int nums[] =new int[5];
        int count = Integer.parseInt(StringReplaceUtils.del0(msgs.substring(0,6)));
        nums[0]= Integer.parseInt(StringReplaceUtils.del0(msgs.substring(6,8)))*2;
        nums[1] = Integer.parseInt(StringReplaceUtils.del0(msgs.substring(8,10)))*2;
        nums[2]= Integer.parseInt(StringReplaceUtils.del0(msgs.substring(10,12)))*2;
        nums[3]= Integer.parseInt(StringReplaceUtils.del0(msgs.substring(12,14)))*2;
        nums[4]= Integer.parseInt(StringReplaceUtils.del0(msgs.substring(14,16)))*2;
        String message[] =new String[5];
        msgs = msgs.substring(16,msgs.length());
        int flag = 0;
        for(int i = 0;i<nums.length;i++){//将每条记录存储在message
            if(nums[i]!=0){
                message[i] = msgs.substring(flag,flag+nums[i]);
                flag+=nums[i];
            }else{
                message[i]="";
            }
        }


        for(int i=0;i<message.length;i++) {//分别解析每条记录
            String msg = message[i];
            if (message[i].length() == 0) {
                continue;
            }
            Map<String, Object> map = new HashMap<>();
            Map<String, Integer> element = ElementCode.ELEMENT;
            Map<String, String> info = ElementCode.INFO;
            Iterator<Map.Entry<String, Integer>> entries = element.entrySet().iterator();//遍历查找msg中的标识符
            while (entries.hasNext()) {
                Map.Entry<String, Integer> entry = entries.next();
                String key = entry.getKey();//标识符值
                int num = entry.getValue();//标识符长度+内容值长度
                int index;
                int fromIndex = 0;
                while ((index = msg.indexOf(key, fromIndex)) != -1) {
                    fromIndex = index + 1;
                    boolean flag1 = false;
                    boolean flag2 = false;
                    if (index + num == msg.length() || index == 0) {//证明标识+内容出现在报文的最后或者首部，为有效标识符
                        list.get(i).put(key, msg.substring(index + key.length(), index + num));
                        break;//
                    }
                    if (index + num > msg.length()) {
                        break;
                    }
                    //否则证明标识符+内容不在报文的最后，需通过遍历index+num后msg的内容
                    String afterMsg = msg.substring(index + num, msg.length());
                    Iterator<Map.Entry<String, Integer>> entryIterator = element.entrySet().iterator();//通过找到的 标识符的位置+标识符长度+内容长度得到下一个标识符的开始，如果能找到一个//出现位置为0的标识符，证明标识符有效
                    while (entryIterator.hasNext()) {
                        Map.Entry<String, Integer> entry1 = entryIterator.next();
                        String key1 = entry1.getKey();
                        if (key.equals(key1)) {//将自己排除
                            continue;
                        } else {
                            if (afterMsg.indexOf(key1) == 0) {//除去当前标识符+内容的报文中，如果出现一个标识符在首部，则证明标识符有效
                                flag1 = true;
                                break;
                            }
                        }
                    }
                    //检测前面是否有
                    Iterator<Map.Entry<String, Integer>> entryIterator1 = element.entrySet().iterator();//通过找到的 标识符的位置+标识符长度+内容长度得到下一个标识符的开始，如果能找到一个//出现位置为0的标识符，证明标识符有效
                    while (entryIterator1.hasNext()) {
                        Map.Entry<String, Integer> entry1 = entryIterator1.next();
                        String key1 = entry1.getKey();
                        int value = entry1.getValue();
                        if (index - entry1.getValue() < 0) {//位于首位排除
                            continue;
                        }
                        if (key.equals(key1)) {//将自己排除
                            continue;
                        } else {
                            if (key1.equals(msg.substring(index - value, index - value + key1.length()))) {//除去当前标识符+内容的报文中，如果出现一个标识符在首部，则证明标识符有效
                                flag2 = true;
                                break;
                            }
                        }
                    }
                    if (flag1 && flag2) {
                        list.get(i).put(key, msg.substring(index + key.length(), index + num));
                        break;
                    }
                }
            }
        }
        List<Map<String,Object>> list1 = new ArrayList<>();
        for(int i = 0 ;i<list.size();i++){
            list1.add(FormatUtils.format(list.get(i)));
        }
        return list1;
    }
}
