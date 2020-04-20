package com.dyjs.meeting.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyjs.meeting.util.BaseResponse;
import com.dyjs.meeting.util.BaseResponseUtil;
import com.dyjs.meeting.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("basic-meeting")
public class WeatherController {


    @PostMapping("forecast24hours")
    public BaseResponse forecast24hours(String lat,String lon){
        String host = "http://aliv8.data.moji.com";
        String path = "/whapi/json/aliweather/forecast24hours";
        String method = "POST";
        String appcode = "bca10c141c914bb78e9f0cd5f75b1447";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("lat", lat);
        bodys.put("lon", lon);
        //bodys.put("token", "1b89050d9f64191d494c806f78e8ea36");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);

            //return BaseResponseUtil.success();
            //获取response的body
            Map json = JSONObject.parseObject(EntityUtils.toString(response.getEntity()),Map.class);
            return  BaseResponseUtil.success(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("condition")
   public BaseResponse condition(String lat,String lon){
       String host = "http://aliv8.data.moji.com";
       String path = "/whapi/json/aliweather/condition";
       String method = "POST";
       String appcode = "bca10c141c914bb78e9f0cd5f75b1447";
       Map<String, String> headers = new HashMap<String, String>();
       //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
       headers.put("Authorization", "APPCODE " + appcode);
       //根据API的要求，定义相对应的Content-Type
       headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
       Map<String, String> querys = new HashMap<String, String>();
       Map<String, String> bodys = new HashMap<String, String>();
       bodys.put("lat", lat);
       bodys.put("lon", lon);
       //bodys.put("token", "7538f7246218bdbf795b329ab09cc524");


       try {
           /**
            * 重要提示如下:
            * HttpUtils请从
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
            * 下载
            *
            * 相应的依赖请参照
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
            */
           HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
           Map json = JSONObject.parseObject(EntityUtils.toString(response.getEntity()),Map.class);
           return  BaseResponseUtil.success(json);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;

   }

    @PostMapping("aqi")
    public BaseResponse getAqi(String lat,String lon){
        String host = "http://aliv8.data.moji.com";
        String path = "/whapi/json/aliweather/aqi";
        String method = "POST";
        String appcode = "bca10c141c914bb78e9f0cd5f75b1447";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("lat", lat);
        bodys.put("lon", lon);
        //bodys.put("token", "7538f7246218bdbf795b329ab09cc524");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            Map json = JSONObject.parseObject(EntityUtils.toString(response.getEntity()),Map.class);
            return  BaseResponseUtil.success(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    @PostMapping("forecast15days")
    public BaseResponse forecast15days(String lat,String lon){
        String host = "http://aliv8.data.moji.com";
        String path = "/whapi/json/aliweather/forecast15days";
        String method = "POST";
        String appcode = "bca10c141c914bb78e9f0cd5f75b1447";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("lat", lat);
        bodys.put("lon", lon);
        //bodys.put("token", "7538f7246218bdbf795b329ab09cc524");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            Map json = JSONObject.parseObject(EntityUtils.toString(response.getEntity()),Map.class);
            return  BaseResponseUtil.success(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    }
