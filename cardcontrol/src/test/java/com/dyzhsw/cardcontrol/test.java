package com.dyzhsw.cardcontrol;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test  {
    public static void main(String[] args) {
        System.out.println(hexString2binaryString("00000680"));
    }
    public static String hexString2binaryString(String hexString)
    {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++)
        {
            tmp = "0000"
                    + Integer.toBinaryString(Integer.parseInt(hexString
                    .substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        StringBuffer sb=new StringBuffer();
        sb.append(bString);
        return sb.reverse().toString();
    }
}

