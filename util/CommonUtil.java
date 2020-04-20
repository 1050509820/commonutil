package com.basic.IoTCardPlatform.util;


import com.alibaba.fastjson.JSONObject;
import com.basic.IoTCardPlatform.entity.BasicInfo;
import com.basic.IoTCardPlatform.entity.Organization;
import com.basic.IoTCardPlatform.service.OrganizationService;
import com.basic.IoTCardPlatform.util.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 掉接口请求工具类
 * @author xyp
 */
@Component
public class CommonUtil {

    private static final Logger logger  = LoggerFactory.getLogger(CommonUtil.class);
    //工具类中使用service
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private OrganizationService organizationService;
    //重点二：建一个静态的本类
    private static CommonUtil commonUtil;
    //重点三：初始化
    @PostConstruct
    public void init() {
        commonUtil= this;
        commonUtil.redisUtil= this.redisUtil;
        commonUtil.tokenUrl=this.tokenUrl;
        commonUtil.ecBillUrl=this.ecBillUrl;
        commonUtil.organizationService = this.organizationService;
    }

    //下面是读取url
    @Value("${IoTCardPlatform.interface.token}")
    private  String  tokenUrl;
    @Value("${IoTCardPlatform.interface.ec_bill}")
    private  String  ecBillUrl;




    /**
     * 获取事务编码
     * @param appid
     * @return
     * @throws Exception
     */
    public static String getTransid(String appid){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmSS");
        String data = sdf.format(new Date());

        Integer no = (Integer) commonUtil.redisUtil.get(appid+"_transid");
        if (no == null) {
            no = 1;
        } else {
            if (no == 99999999) {
                no=1;
            } else {
                no = no + 1;
            }
        }
        commonUtil.redisUtil.set(appid+"_transid",no);
        String num = org.apache.commons.lang3.StringUtils.leftPad(no.toString(),8, "0");
        return appid+data+num;
    }

    /**
     * 通过卡获得token
     * @param basicInfo
     * @return
     */
    public   static String getTokenByBasicInfo(BasicInfo basicInfo){
        String oid = basicInfo.getPurchasingDept();
        Organization organization = new Organization();
        try{
            organization.setId(oid);
            organization = commonUtil.organizationService.list(organization).get(0);
        }catch (Exception e){
            logger.info("组织id异常或没有此组织"+e);
            return null;
        }
        String token = getToken(organization.getAppid(),organization.getPassword());
        if(token==null){
            throw new RuntimeException("获取token失败，请检查appid和password");
        }
        return token;
    }

    /**
     * 通过卡获得appid
     * @param basicInfo
     * @return
     */
    public static String getAppid(BasicInfo basicInfo){
        String oid = basicInfo.getPurchasingDept();
        if(oid==null){
            throw new RuntimeException("组织id不能为空");
        }
        Organization organization = new Organization();
        try{
            organization.setId(oid);
            organization = commonUtil.organizationService.list(organization).get(0);
        }catch (Exception e){
            logger.info("组织id异常或没有此组织");
            return null;
        }
        return organization.getAppid();
    }



    /**
     * 获取token值
     * @param appid
     * @param password
     * @return
     */
    public static String getToken(String appid,String password){
        Map map1 = (Map) commonUtil.redisUtil.get(appid+"_token");
        if(map1!=null){
            if(new Date().getTime()-(long)map1.get("time")>50*60*1000){
                String token = getTokenP(appid,password);
                if(token!=null){
                    Map map = new HashMap<>();
                    map.put("time",new Date().getTime());
                    map.put("token",token);
                    commonUtil.redisUtil.set(appid+"_token",map);
                    return token;
                }else{
                    return null;
                }
            }else{
                return (String)map1.get("token");
            }
        }else{
            String token = getTokenP(appid,password);
            if(token!=null){
                Map map = new HashMap<>();
                map.put("time",new Date().getTime());
                map.put("token",token);
                commonUtil.redisUtil.set(appid+"_token",map);
                return token;
            }else{
                return null;
            }

        }
    }

    private static String getTokenP(String appid,String password){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appid", appid);
        jsonObject.put("password", password);
        jsonObject.put("transid", getTransid(appid));
        String body = jsonObject.toJSONString();
        jsonObject = JSONObject.parseObject(HttpUtils.doPost(commonUtil.tokenUrl, body));
        if("0".equals(jsonObject.get("status"))){
            String token = jsonObject.getJSONArray("result").getJSONObject(0).getString("token");
            return token;
        }else{
            logger.error(appid+"  "+jsonObject.get("message"));
            return null;
        }
    }

    /**
     * 集团客户账单实时查询
     * @param basicInfo
     * @param queryDate
     * @return
     */
    public static JSONObject getBill(BasicInfo basicInfo,String queryDate){

        JSONObject jsonObject = getJsonObject(basicInfo);
        jsonObject.put("queryDate", queryDate);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost(commonUtil.ecBillUrl, body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            return null;
        }
    }

    /**
     * token值主动过期，删除redis键值对
     * @param jsonObject
     * @param appid
     * @return
     */
    public static Boolean tokenIsEnable(JSONObject jsonObject,String appid){
        if(jsonObject==null){
            return false;
        }
        boolean flag = !"12021".equals(jsonObject.get("status"));
        if(!flag){
            commonUtil.redisUtil.del(appid+"_token");
        }
        return flag ;
    }

    /**
     * CMIOT_API23S02-单卡状态变更历史查询。
     * @param basicInfo
     * @return
     */
    public static JSONObject simChangeHistory(BasicInfo basicInfo){
        JSONObject jsonObject = getJsonObject(basicInfo);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-change-history", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            return null;
        }
    }

    /**
     * 查询物联卡的状态信息。
     * @param basicInfo
     * @return
     */
    public static JSONObject simStatus(BasicInfo basicInfo){
        JSONObject jsonObject = getJsonObject(basicInfo);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-status", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            return null;
        }
    }

    /**
     * CMIOT_API23S00-单卡基本信息查询
     * @param basicInfo
     * @return
     */
    public static JSONObject siMBasicInfo(BasicInfo basicInfo){
        JSONObject jsonObject = getJsonObject(basicInfo);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-basic-info", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            return null;
        }
    }

    /**
     * CMIOT_API23M08-单卡通信功能开通查询
     * @param basicInfo
     * @return
     */
    public static JSONObject simCommunicationFunction(BasicInfo basicInfo){
        JSONObject jsonObject = getJsonObject(basicInfo);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-communication-function-status", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            return null;
        }
    }




    /**
     * CMIOT_API23S04-单卡绑定IMEI实时查询
     * @param basicInfo
     * @return
     */
    public static JSONObject simImei(BasicInfo basicInfo){
        JSONObject jsonObject = getJsonObject(basicInfo);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-imei", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            return null;
        }
    }

    /**
     * CMIOT_API23U07-单卡本月套餐流量用量实时查询
     * @param basicInfo
     * @return
     */
    public static JSONObject simDataMargin(BasicInfo basicInfo){
        JSONObject jsonObject = getJsonObject(basicInfo);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-data-margin", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            return null;
        }
    }


    /**
     * CMIOT_API23U06-单卡本月套餐内短信使用量实时查询
     * @param basicInfo
     * @return
     */
    public static JSONObject simSmsMargin(BasicInfo basicInfo){
        JSONObject jsonObject = getJsonObject(basicInfo);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-sms-margin", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            return null;
        }
    }


    /**
     * CMIOT_API25U01-物联卡单日GPRS流量使用量批量查询
     * @param basicInfo
     * @return
     */
    public static JSONObject simDataUsageDailyBatch(BasicInfo basicInfo){
        JSONObject jsonObject = getJsonObject(basicInfo);
        jsonObject.put("",jsonObject.get(""));
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-data-usage-daily/batch", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            return null;
        }
    }
    /**
     * CMIOT_API25U04-单卡本月流量累计使用量查询
     * @param basicInfo
     * @return
     */
    public static JSONObject simDataUsage(BasicInfo basicInfo){
        JSONObject jsonObject = getJsonObject(basicInfo);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-data-usage", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            throw new RuntimeException();
        }
    }


    /**
     * 获得token，transid，三选一的jsonobject
     * @param basicInfo
     * @return
     */
    public static JSONObject getJsonObject(BasicInfo basicInfo){
        JSONObject jsonObject = new JSONObject();
        String token = getTokenByBasicInfo(basicInfo);
        String appid = getAppid(basicInfo);
        if(token==null&&appid==null){
            return null;
        }
        jsonObject.put("token", token);
        jsonObject.put("transid", getTransid(appid));
        if(basicInfo.getImsi()!=null&&jsonObject.get("imsi")==null&&jsonObject.get("iccid")==null&&jsonObject.get("msisdn")==null&&!"".equals(basicInfo.getImsi())){
            jsonObject.put("imsi",basicInfo.getImsi());
        }else if(basicInfo.getIccid()!=null&&jsonObject.get("imsi")==null&&jsonObject.get("iccid")==null&&jsonObject.get("msisdn")==null&&!"".equals(basicInfo.getIccid())) {
            jsonObject.put("iccid", basicInfo.getIccid());
        }else if(basicInfo.getMSISDN()!=null&&jsonObject.get("imsi")==null&&jsonObject.get("iccid")==null&&jsonObject.get("msisdn")==null&&!"".equals(basicInfo.getMSISDN())){
            jsonObject.put("msisdn",basicInfo.getMSISDN());
        }
        return jsonObject;
    }

    /**
     * CMIOT_API25U01-物联卡单日 GPRS 流量使用量批量查询
     * @param basicInfo
     * @return
     */
    public static JSONObject getDataUsage(BasicInfo basicInfo,String queryDate){
        JSONObject jsonObject = getJsonObject(basicInfo);
        if(basicInfo.getImsi()!=null&&jsonObject.get("imsis")==null&&jsonObject.get("iccids")==null&&jsonObject.get("msisdns")==null){
            jsonObject.put("imsis",basicInfo.getImsi());
        }else if(basicInfo.getIccid()!=null&&jsonObject.get("imsis")==null&&jsonObject.get("iccids")==null&&jsonObject.get("msisdns")==null) {
            jsonObject.put("iccids", basicInfo.getImsi());
        }else if(basicInfo.getMSISDN()!=null&&jsonObject.get("imsis")==null&&jsonObject.get("iccids")==null&&jsonObject.get("msisdns")==null){
            jsonObject.put("msisdns",basicInfo.getImsi());
        }
        jsonObject.put("queryDate",queryDate);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-data-usage-daily/batch", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            throw new RuntimeException();
        }
    }

    /**
     * CMIOT_API25U00-物联卡单日短信使用量批量查询
     * @param basicInfo
     * @return
     */
    public static JSONObject getSmsUsage(BasicInfo basicInfo,String queryDate){
        JSONObject jsonObject = getJsonObject(basicInfo);
        if(basicInfo.getImsi()!=null&&jsonObject.get("imsis")==null&&jsonObject.get("iccids")==null&&jsonObject.get("msisdns")==null){
            jsonObject.put("imsis",basicInfo.getImsi());
        }else if(basicInfo.getIccid()!=null&&jsonObject.get("imsis")==null&&jsonObject.get("iccids")==null&&jsonObject.get("msisdns")==null) {
            jsonObject.put("iccids", basicInfo.getImsi());
        }else if(basicInfo.getMSISDN()!=null&&jsonObject.get("imsis")==null&&jsonObject.get("iccids")==null&&jsonObject.get("msisdns")==null){
            jsonObject.put("msisdns",basicInfo.getImsi());
        }
        jsonObject.put("queryDate",queryDate);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-sms-usage-daily/batch", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            throw new RuntimeException();
        }
    }

    /**
     * CMIOT_API25S04-单卡状态查询
     * @param basicInfo
     * @return
     */
    public static JSONObject getStatus(BasicInfo basicInfo,String queryDate){
        JSONObject jsonObject = getJsonObject(basicInfo);
        jsonObject.put("queryDate",queryDate);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/sim-status", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            throw new RuntimeException();
        }
    }

    /**
     * CMIOT_API23B01-单卡余额信息实时查询
     * @param basicInfo
     * @return
     */
    public static JSONObject balanceInfo(BasicInfo basicInfo){
        JSONObject jsonObject = getJsonObject(basicInfo);
        String body = jsonObject.toJSONString();
        JSONObject result = JSONObject.parseObject(HttpUtils.doPost("https://api.iot.10086.cn/v5/ec/query/balance-info", body));
        if(tokenIsEnable(result,getAppid(basicInfo))){
            return result;
        }else{
            throw new RuntimeException();
        }
    }



}

