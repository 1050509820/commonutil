package com.dyjs.meeting.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyjs.meeting.service.UserService;
import com.dyjs.meeting.util.BaseResponse;
import com.dyjs.meeting.util.BaseResponseUtil;
import com.dyjs.meeting.util.HttpClientUtil;
import com.dyjs.meeting.wx.UserConstantInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserApi {

    @Autowired
    private UserService userService;


    @PostMapping("/me/login")
    public BaseResponse user_login(
            @RequestParam("code") String code
    ){
        // 配置请求参数
        Map<String, String> param = new HashMap<>();
        param.put("appid", UserConstantInterface.WX_LOGIN_APPID);
        param.put("secret", UserConstantInterface.WX_LOGIN_SECRET);
        param.put("js_code", code);
        param.put("grant_type", UserConstantInterface.WX_LOGIN_GRANT_TYPE);
        // 发送请求
        String wxResult = HttpClientUtil.doGet(UserConstantInterface.WX_LOGIN_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);

        try {
            String session_key = jsonObject.get("session_key").toString();
            String open_id = jsonObject.get("openid").toString();
            // 根据返回的user实体类，判断用户是否是新用户，不是的话，更新最新登录时间，是的话，将用户信息存到数据库
            // 封装返回小程序
            Map<String, String> result = new HashMap<>();
            result.put("session_key", session_key);
            result.put("open_id", open_id);
            return  BaseResponseUtil.success(result);
        }catch (Exception e){
            return BaseResponseUtil.error(500,"登陆失败");
        }


    }

}
