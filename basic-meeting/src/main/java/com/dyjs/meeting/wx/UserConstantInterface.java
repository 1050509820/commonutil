package com.dyjs.meeting.wx;

public interface UserConstantInterface {
    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 你的appid
    public static final String WX_LOGIN_APPID = "wx21b88826c0be579f";
    // 你的密匙
    public static final String WX_LOGIN_SECRET = "dc47d098922d5d42b082238d96d0772a";
    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

}
