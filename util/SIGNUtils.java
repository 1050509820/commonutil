package com.basic.IoTCardPlatform.util;

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @Author: Henry1901
 * @Date: 2020/3/2 10:50
 * @Version 1.0
 */
public class SIGNUtils {
    /**
     * 签名key
     */
    private final static String SIGN_KEY = "sign";
    /**
     * 降序
     */
    public final static String SORT_DESC = "desc";
    /**
     * 升序
     */
    public final static String SORT_ASC = "asc";
    /**
     * 例外的数组
     */
    private final static String[] EXCEPTION_STR = new String[]{"sign","t","callback","_callback","_"};

    /**
     * 获得请求参数的签名字符串
     * @param params 参数
     * @param appSecret 签名key
     * @param sort 排序方式
     * @return
     */
    public static String assembleSign(Map<String,Object> params,String appSecret, final String sort){
        List<String> exList = Arrays.asList(EXCEPTION_STR);
        List<String> keys = new ArrayList<String>();
        if (params==null||params.isEmpty()){
            return "";
        }
        for (Map.Entry<String,Object> entry : params.entrySet()){
            keys.add(entry.getKey());
        }
        Collections.sort(keys,new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                if (sort==null){
                    return o1.compareTo(o2);
                }else if (SORT_DESC.equals(sort)){
                    return o2.compareTo(o1);
                }else if (SORT_ASC.equals(sort)){
                    return o1.compareTo(o2);
                }
                return 0;
            }
        });
        StringBuffer sb = new StringBuffer(appSecret);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if (exList.contains(key.toLowerCase())){
                continue;
            }
            sb.append(key).append(params.get(key));
        }
        sb.append(appSecret);
        return Md5Utils.md5(sb.toString()).toUpperCase();
    }

    /**
     * 获得请求参数的签名字符串-升序
     * @param params 参数
     * @param appSecret 签名key
     * @return
     */
    public static String assembleSign(Map<String,Object> params,String appSecret){
        return assembleSign(params,appSecret,SORT_ASC);
    }
    /**
     * 校验签名是否正确
     * @param params 参数
     * @param appSerect 签名字符格式
     * @param sort 排序方式
     * @return
     */
    public static Boolean validateSign(Map<String,Object> params,String appSerect,final String sort){
        if (StringUtils.isBlank(appSerect)) {
            return false;
        }
        String sign = assembleSign(params, appSerect, sort);
        return sign.equals(params.get(SIGN_KEY));
    }
    /**
     * 校验签名是否正确
     * @param params 参数
     * @return
     */
    public static Boolean validateSign(Map<String,Object> params, String appSerect){
        return validateSign(params,appSerect, SORT_ASC);
    }
}