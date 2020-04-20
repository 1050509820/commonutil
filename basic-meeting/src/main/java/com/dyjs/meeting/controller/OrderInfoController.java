package com.dyjs.meeting.controller;


import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.dyjs.meeting.dao.CodeDto;
import com.dyjs.meeting.dao.OrderInfoDto;
import com.dyjs.meeting.dao.QrCodeDto;
import com.dyjs.meeting.excel.ExcelException;
import com.dyjs.meeting.excel.ExcelUtil;
import com.dyjs.meeting.service.CodeService;
import com.dyjs.meeting.service.OrderInfoService;
import com.dyjs.meeting.service.QrCodeService;
import com.dyjs.meeting.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api("预约相关接口")
@RestController
@Transactional
@RequestMapping("basic-meeting")
public class OrderInfoController {
    Logger logger = LoggerFactory.getLogger(OrderInfoController.class);
    @Value("${savepath}")
    private String path;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private QrCodeService qrCodeService;



    @RequestMapping("/addOrderInfo")
    @ApiOperation(value = "预约接口", notes = "预约接口")
    @ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "tel", value = "电话", required = true, dataType = "String"),
            @ApiImplicitParam(name = "unit", value = "单位", required = true, dataType = "String"),
            @ApiImplicitParam(name = "position", value = "职位", required = true, dataType = "String"),
            @ApiImplicitParam(name = "accommodation", value = "住宿标识 0：否 1：是", required = true, dataType = "String"),
            @ApiImplicitParam(name = "moveIntoTime", value = "入住时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "roomType", value = "房间类型", required = false, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "String")
           })
    public BaseResponse addOrderInfo(String username, String tel, String unit, String position, int accommodation,
                                     String moveIntoTime, String roomType,String code,String dock,String moveOutTime,String openid,
                                    HttpServletRequest request) throws ParseException, ClientException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        OrderInfoDto orderInfoDao = new OrderInfoDto();
        orderInfoDao.setUsername(username);
        orderInfoDao.setTel(tel);
        orderInfoDao.setUnit(unit);
        orderInfoDao.setPosition(position);
        orderInfoDao.setAccommodation(accommodation);
        orderInfoDao.setRoomType(roomType);
        orderInfoDao.setDock(dock);
        if(moveIntoTime!=null&&!"".equals(moveIntoTime)) {
            orderInfoDao.setMoveIntoTime(sdf.parse(moveIntoTime));
        }
        if(moveOutTime!=null&&!"".equals(moveOutTime)) {
            orderInfoDao.setMoveOutTime(sdf.parse(moveOutTime));
        }
        orderInfoDao.setOrdertime(new Date());
        orderInfoDao.setIsvalid("1");
        OrderInfoDto orderInfoDto = orderInfoService.selectBytel(orderInfoDao.getTel());
        QrCodeDto qrCodeDto1 = qrCodeService.selectByOpenid(openid);
        if(orderInfoDto==null&&qrCodeDto1==null){//未预约
            CodeDto codeDto = codeService.getCode(tel);
            if(code.equals(codeDto.getCode())){//验证码正确

                //生成二维码并保存
                String text = tel;
                String filename=tel;
                String str="";
                try {
                    str = QRCodeUtil.encode(text, null, path, null, true);
                    QrCodeDto qrCodeDto =new QrCodeDto();
                    qrCodeDto.setOpenid(openid);
                    qrCodeDto.setTel(tel);
                    qrCodeDto.setImgurl("img/"+str);
                    qrCodeService.insert(qrCodeDto);
                    } catch (Exception e) {
                    logger.info("生成二维码失败!"+e);
                    return BaseResponseUtil.success(200,"400","二维码生成失败");
                }
                orderInfoService.insert(orderInfoDao);
                SmsUtil.sendSms(tel,"SMS_178756031");//发送预约成功短信
                CodeDto codeDto1 = new CodeDto();

                codeDto1.setTel(tel);
                codeDto1.setIsdel("1");
                codeService.update(codeDto1);

                return BaseResponseUtil.success(200,"200","预约成功");
            }else{
                return BaseResponseUtil.success(200,"300","验证码错误");
            }
        }else{
            return BaseResponseUtil.success(200,"500","此微信号或手机号已预约");
        }
    }

     @PostMapping("queryInfo")
    @ApiOperation(value = "查询是否预约", notes = "查询是否预约")
    @ApiImplicitParams({ @ApiImplicitParam(name = "openid", value = "用户唯一标识", required = true, dataType = "String"),
    })
    public BaseResponse queryInfo(String openid){
        QrCodeDto qrCodeDto = qrCodeService.selectByOpenid(openid);
        if(qrCodeDto!=null) {
            OrderInfoDto orderInfoDto = orderInfoService.selectBytel(qrCodeDto.getTel());
            if (orderInfoDto == null) {
                return BaseResponseUtil.error(200, "0");
            } else {
                return BaseResponseUtil.success(200, "1", orderInfoDto);
            }
        }
            return BaseResponseUtil.error(200, "0");
    }


    @PostMapping("queryList")
    @ApiOperation(value = "预约列表查询", notes = "预约列表查询")
    @ApiImplicitParams({ @ApiImplicitParam(name = "tel", value = "电话", required = false, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "姓名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = false, dataType = "Int"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "Int"),
    })
    public BaseResponse queryList(String username,String startTime,String endTime,String tel,Integer pageSize,Integer pageNum,String unit,Integer accommodation,String roomtype,String moveIntoTime,String isCheck) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        OrderInfoDto orderInfoDto = new OrderInfoDto();
        orderInfoDto.setUsername(username);
        orderInfoDto.setTel(tel);
        orderInfoDto.setUnit(unit);
        orderInfoDto.setAccommodation(accommodation);
        orderInfoDto.setRoomType(roomtype);
        orderInfoDto.setIsCheck(isCheck);
        if(moveIntoTime!=null){
            orderInfoDto.setMoveIntoTime(sdf.parse(moveIntoTime));
        }
        if(endTime!=null){
            orderInfoDto.setEndTime(sdf.parse(endTime));
        }
        if(startTime!=null){
            orderInfoDto.setStartTime(sdf.parse(startTime));
        }
        List<OrderInfoDto> list= orderInfoService.selectOrderInfos(orderInfoDto);
        if(pageSize==null&&pageNum==null){
            return BaseResponseUtil.success(list);
        }else{
            PageHelper.startPage(pageNum,pageSize);
            PageInfo<OrderInfoDto> pageInfo =new PageInfo<>(orderInfoService.selectOrderInfos(orderInfoDto));
            for(OrderInfoDto infoDto:pageInfo.getList()){
                infoDto.setImgUrl(qrCodeService.selectByTel(infoDto.getTel()).getImgurl());
            }
            return BaseResponseUtil.success(pageInfo);
        }
    }

    @RequestMapping("getExcel")
    public  BaseResponse getExcel(String username,String startTime,String endTime,String tel,Integer pageSize,Integer pageNum,String unit,Integer accommodation,String roomtype,String moveIntoTime, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        OrderInfoDto orderInfoDto = new OrderInfoDto();
        orderInfoDto.setUsername(username);
        orderInfoDto.setTel(tel);
        orderInfoDto.setUnit(unit);
        orderInfoDto.setAccommodation(accommodation);
        orderInfoDto.setRoomType(roomtype);
        if(moveIntoTime!=null){
            orderInfoDto.setMoveIntoTime(sdf.parse(moveIntoTime));
        }
        if(endTime!=null){
            orderInfoDto.setEndTime(sdf.parse(endTime));
        }
        if(startTime!=null){
            orderInfoDto.setStartTime(sdf.parse(startTime));
        }
        List<OrderInfoDto> list= orderInfoService.selectOrderInfos(orderInfoDto);
        Map map =new LinkedHashMap<>();
        map.put("username","姓名");
        map.put("tel","电话号码");
        map.put("unit","单位");
        map.put("position","职位");
        map.put("accommodation","是否住宿(1：是 0：否)");
        map.put("moveIntoTime","入住时间");
        map.put("moveOutTime","退房时间");
        map.put("roomType","房间类型 0:单间 1：标间");
        map.put("ordertime","预约时间");
        map.put("dock","会议对接人");
        map.put("isCheck","是否签到0：否 1：是");
        map.put("hotel","入住酒店");
        map.put("dining","用餐信息");
        map.put("seat","座位信息");
        ExportExcel.excelExport(list,map,"预约表",request,response);
        return BaseResponseUtil.success();
    }

    @PostMapping("delOrderInfo")
    public BaseResponse delOrderInfo(Integer id) {
        OrderInfoDto orderInfoDto1 = orderInfoService.selectById(id);
        OrderInfoDto orderInfoDto = new OrderInfoDto();
        orderInfoDto.setId(id);
        orderInfoDto.setIsvalid("0");
        orderInfoService.updateInfo(orderInfoDto);
        qrCodeService.deleteByTel(orderInfoDto1.getTel());
        return BaseResponseUtil.success("删除成功！");
    }

    @PostMapping("getCode")
    public BaseResponse getCode(String tel) throws ClientException {
        OrderInfoDto orderInfoDto = orderInfoService.selectBytel(tel);
        if(orderInfoDto!=null){
            return BaseResponseUtil.success(200,"400","您已经预约");
        }
        Map response=SmsUtil.sendSms(tel,"SMS_178756028");
        CommonResponse commonResponse= (CommonResponse) response.get("response");
        Map json = JSONObject.parseObject(commonResponse.getData(),Map.class);
        if("OK".equals(json.get("Code"))){
            CodeDto codeDto1 =codeService.getCode(tel);
            if(codeDto1==null){
                CodeDto codeDto = new CodeDto();
                codeDto.setTel(tel);
                codeDto.setCode((String) response.get("code"));
                codeDto.setIsdel("0");
                codeDto.setCreatetime(new Date());
                codeService.insert(codeDto);
            }else{
                codeDto1.setCode((String) response.get("code"));
                codeDto1.setIsdel("0");
                codeDto1.setTel(tel);
                codeDto1.setCreatetime(new Date());
                codeService.update(codeDto1);
            }
            return BaseResponseUtil.success(200,"200","发送成功");
        }else{
            return BaseResponseUtil.success(200,"300","发送失败");
        }
    }

    @PostMapping("check")
    public BaseResponse check(String tel,HttpServletRequest request){
        OrderInfoDto orderInfoDto = orderInfoService.selectBytel(tel);
        if(orderInfoDto==null){
            return BaseResponseUtil.success(200,"300","您尚未预约，不可签到");
        }else{
            if("0".equals(orderInfoDto.getIsCheck())){
                OrderInfoDto orderInfoDto1 = new OrderInfoDto();
                orderInfoDto1.setId(orderInfoDto.getId());
                orderInfoDto1.setIsCheck("1");
                orderInfoService.updateInfo(orderInfoDto1);

                return BaseResponseUtil.success(200,"200","签到成功");
            }else{
                return BaseResponseUtil.success(200,"400","您已经签到");
            }
        }

    }

    @PostMapping("test")
    public void test(String tel,HttpServletRequest request) throws FileNotFoundException {
      CodeDto codeDto = new CodeDto();
      codeDto.setCode("1231");
      codeDto.setTel("12");
      codeService.insert(codeDto);
      try {
          System.out.println(1/0);
      }catch (Exception e){
          e.printStackTrace();
      }


    }
    @PostMapping("getImgUrl")
    public BaseResponse getImgUrl(String openid){
        QrCodeDto qrcode = qrCodeService.selectByOpenid(openid);
        String imgurl="";
        if(qrcode!=null){
           imgurl = qrcode.getImgurl();
           return BaseResponseUtil.success(imgurl);
        }else{
            return BaseResponseUtil.error(500,"您尚未预约");
        }
    }

    @PostMapping("getImgUrlByTel")
    public BaseResponse getImgUrlByTel(String tel){
        QrCodeDto qrcode = qrCodeService.selectByTel(tel);
        String imgurl="";
        if(qrcode!=null){
            imgurl = qrcode.getImgurl();
            return BaseResponseUtil.success(imgurl);
        }else{
            return BaseResponseUtil.error(500,"您尚未预约");
        }
    }



    @PostMapping("getNumOfPeople")
    public BaseResponse getNumOfPeople(){


        return BaseResponseUtil.success(orderInfoService.getNum());

    }


    @PostMapping("resolveExcel")
    public BaseResponse resolverExcel(MultipartFile file) throws Exception {

        File pdfFile = multipartFileToFile(file);
        FileInputStream fileInputStream = new FileInputStream(pdfFile);
        MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        try {
            List<OrderInfoDto> list = ExcelUtil.readExcel(multipartFile, OrderInfoDto.class);
            String res = "";
            for (int i = 0; i < list.size(); i++) {
                OrderInfoDto orderInfoDto = list.get(i);
                String tel = orderInfoDto.getTel();
                orderInfoDto.setUsername(orderInfoDto.getUsername().replaceAll(" ", ""));
                if (tel == null || "".equals(tel)) {
                    res += "第" + (i + 1) + "行出现错误***";
                    logger.info("***************第" + i+1 + "行出现错误***************");
                } else {
                    OrderInfoDto orderInfoDto1 = orderInfoService.selectBytel(tel);
                    if (orderInfoDto1 == null) {
                        orderInfoDto.setOrdertime(new Date());
                        if ("0.0".equals(orderInfoDto.getRoomType())) {
                            orderInfoDto.setRoomType("0");
                        }
                        orderInfoService.insert(orderInfoDto);
                        String savepath = "/data/img/";
                        String str = QRCodeUtil.encode(tel, null, savepath, null, true);
                        QrCodeDto qrCodeDto = new QrCodeDto();
                        qrCodeDto.setTel(tel);
                        qrCodeDto.setImgurl("img/" + str);
                        qrCodeService.insert(qrCodeDto);
                    } else {
                        orderInfoDto.setId(orderInfoDto1.getId());
                        orderInfoService.updateInfo(orderInfoDto);
                        logger.info("更新数据成功："+orderInfoDto.getTel());
                        res+="更新成功："+orderInfoDto1.getTel();
                    }
                }
            }
            if(res.length()==0||"".equals(res)){
                res="导入成功";
            }
            return BaseResponseUtil.success(res);
        }catch (Exception e){
            return BaseResponseUtil.error(500,"数据格式错误");
        }
    }

    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除本地临时文件
     * @param file
     */
    public static void delteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }

    @PostMapping("OrderForPc")//pc端预约接口
    public BaseResponse OrderForPc(OrderInfoDto orderInfoDto) throws Exception {
        if(orderInfoDto.getTel()==null){
            return BaseResponseUtil.error(500,"参数错误");
        }else{
            if(orderInfoService.selectBytel(orderInfoDto.getTel())!=null){
                return BaseResponseUtil.error(500,"电话已被预约");
            }else{
                orderInfoDto.setOrdertime(new Date());
               orderInfoService.insert(orderInfoDto);
                String savepath = "/data/img/";
                String str = QRCodeUtil.encode(orderInfoDto.getTel(), null, savepath, null, true);
                QrCodeDto qrCodeDto = new QrCodeDto();
                qrCodeDto.setTel(orderInfoDto.getTel());
                qrCodeDto.setImgurl("img/" + str);
                qrCodeService.insert(qrCodeDto);
               return BaseResponseUtil.success("预约成功！");
            }
        }
    }


    @PostMapping("deleteall")
    public BaseResponse deleteall(MultipartFile file) throws Exception {

        File pdfFile = multipartFileToFile(file);
        FileInputStream fileInputStream = new FileInputStream(pdfFile);
        MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
            List<OrderInfoDto> list = ExcelUtil.readExcel(multipartFile, OrderInfoDto.class);
            String res = "";
            for (int i = 0; i < list.size(); i++) {
                OrderInfoDto orderInfoDto = list.get(i);
                OrderInfoDto orderInfoDto1 = orderInfoService.selectBytel(orderInfoDto.getTel());
                OrderInfoDto del = new OrderInfoDto();
                if(orderInfoDto1!=null){
                    del.setId(orderInfoDto1.getId());
                    del.setIsvalid("0");
                    orderInfoService.updateInfo(del);
                    qrCodeService.deleteByTel(orderInfoDto.getTel());
                }
            }
        return BaseResponseUtil.error(500,"数据格式错误");
    }

    public void sendMessages() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2019-12-08");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int x = calendar.get(Calendar.DAY_OF_YEAR);
        Date now = new Date();
        calendar.setTime(now);
        int y = calendar.get(Calendar.DAY_OF_YEAR);
        int day = x-y;
        System.out.println(day);

    }

    @PostMapping("queryForPerson")
    @ApiOperation(value = "预约列表查询", notes = "预约列表查询")
    @ApiImplicitParams({ @ApiImplicitParam(name = "tel", value = "电话", required = false, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "姓名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = false, dataType = "Int"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "Int"),
    })
    public BaseResponse queryForPerson(String username,String startTime,String endTime,String tel,Integer pageSize,Integer pageNum,String unit,Integer accommodation,String roomtype,String moveIntoTime) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        OrderInfoDto orderInfoDto = new OrderInfoDto();
        orderInfoDto.setUsername(username);
        orderInfoDto.setTel(tel);
        orderInfoDto.setUnit(unit);
        orderInfoDto.setAccommodation(accommodation);
        orderInfoDto.setRoomType(roomtype);
        if(moveIntoTime!=null){
            orderInfoDto.setMoveIntoTime(sdf.parse(moveIntoTime));
        }
        if(endTime!=null){
            orderInfoDto.setEndTime(sdf.parse(endTime));
        }
        if(startTime!=null){
            orderInfoDto.setStartTime(sdf.parse(startTime));
        }
        List<OrderInfoDto> list= orderInfoService.selectOrderInfos(orderInfoDto);
        if(pageSize==null&&pageNum==null){
            return BaseResponseUtil.success(list);
        }else{
            PageHelper.startPage(pageNum,pageSize);
            PageInfo<OrderInfoDto> pageInfo =new PageInfo<>(orderInfoService.queryForPerson(orderInfoDto));
            for(OrderInfoDto infoDto:pageInfo.getList()){
                infoDto.setImgUrl(qrCodeService.selectByTel(infoDto.getTel()).getImgurl());
            }
            return BaseResponseUtil.success(pageInfo);
        }
    }

    @RequestMapping("createQr")
    public BaseResponse createQr(String tel) throws Exception {
        OrderInfoDto orderInfoDto = orderInfoService.selectBytel(tel);
        if(orderInfoDto==null){
            return BaseResponseUtil.success("尚未预约");
        }else{
            QrCodeDto qrCodeDto= qrCodeService.selectByTel(tel);
            if(qrCodeDto!=null){
                qrCodeService.deleteByTel(tel);
            }
                String savepath = "/data/img/";
                String str = QRCodeUtil.encode(tel, null, savepath, null, true);
                QrCodeDto qrCodeDto1 = new QrCodeDto();
                qrCodeDto1.setTel(orderInfoDto.getTel());
                qrCodeDto1.setImgurl("img/" + str);
                qrCodeService.insert(qrCodeDto1);
        }
        return  BaseResponseUtil.success();
    }


}
