package com.dyjs.meeting.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.dyjs.meeting.dao.CodeDto;
import com.dyjs.meeting.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import springfox.documentation.spring.web.json.Json;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SmsUtil {

    @Autowired
    private CodeService codeService;
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "yourAccessKeyId";
    static final String accessKeySecret = "yourAccessKeySecret";

    public  static  Map sendSms(String tel,String sms) throws ClientException {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIlQ52Uvtz320y", "uwUQRIiVFnF0t5mxpnBApvzVD96zoB");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("sendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers",tel);
        request.putQueryParameter("TemplateCode",sms);
        request.putQueryParameter("SignName", "中国节水论坛");
        String code="";
        if("SMS_178756031".equals(sms)){
            Map map= new HashMap<>();
            map.put("name",tel);
            request.putQueryParameter("TemplateParam", JSON.toJSONString(map));
        }else{
            Map map = new HashMap();
            code = getCode();
            map.put("code",code);
            request.putQueryParameter("TemplateParam", JSON.toJSONString(map));
        }
        Map map = new HashMap();
        try {
            CommonResponse response = client.getCommonResponse(request);
            Map json = JSONObject.parseObject(response.getData(),Map.class);
            if("OK".equals(json.get("Code"))&&!"SMS_178756031".equals(sms)){
                map.put("code",code);
            }
            map.put("response",response);
            return map;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return  null;
    }


    public static String getCode(){
        String str = "0123456789";
        StringBuilder sb = new StringBuilder(4);
        for(int i=0; i < 4; i++){
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();

    }

    public static Map sendMessage(Map map,String sms) throws ClientException {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIlQ52Uvtz320y", "uwUQRIiVFnF0t5mxpnBApvzVD96zoB");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("sendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", (String) map.get("tel"));
        request.putQueryParameter("TemplateCode",sms);
        request.putQueryParameter("SignName", "中国节水论坛");
        Map param = new HashMap();
        param.put("tel",map.get("tel"));
        request.putQueryParameter("TemplateParam", JSON.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            Map json = JSONObject.parseObject(response.getData(),Map.class);
            if("OK".equals(json.get("Code"))){
               return json;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return  null;

    }



}
