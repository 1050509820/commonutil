package com.dyjs.meeting.controller;


import com.dyjs.meeting.dao.QrCodeDto;
import com.dyjs.meeting.dao.Seat;
import com.dyjs.meeting.service.QrCodeService;
import com.dyjs.meeting.service.SeatService;
import com.dyjs.meeting.util.BaseResponse;
import com.dyjs.meeting.util.BaseResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("选座接口")
@RestController
@RequestMapping("basic-meeting")
public class SeatController {
    @Autowired
    private SeatService seatService;
    @Autowired
    private  QrCodeService qrCodeService;


    @PostMapping("/orderSeat")
    @ApiOperation(value = "座位预约", notes = "座位预约")
    @ApiImplicitParams({ @ApiImplicitParam(name = "tel", value = "电话", required = true, dataType = "String"),
            @ApiImplicitParam(name = "seatNo", value = "座位号", required = true, dataType = "String"),
    })
    public BaseResponse orderSeat(String tel,String seatNo,String name){
        try {
            if(seatService.orderSeat(tel,seatNo,name)){
                return BaseResponseUtil.success(200,"座位预定成功",null);
            }
        } catch (Exception e) {
            return BaseResponseUtil.error(501,"座位预约失败");
        }
        return BaseResponseUtil.success(200,"座位预定成功",null);
    }
    @PostMapping("getSeat")
    @ApiOperation(value = "获得已预约座位", notes = "获得已预约座位")
    public BaseResponse getSeat(){
        List<Seat> list =seatService.getSeat();
        return BaseResponseUtil.success(list);
    }
    @PostMapping("getSeatByTel")
    @ApiOperation(value = "获得预约座位", notes = "获得预约座位")
    public BaseResponse getSeatBytel(String tel){
        List<Seat> list = seatService.getSeatBytel(tel);
        return BaseResponseUtil.success(list);
    }
    @PostMapping("removeOrder")
    @ApiOperation(value = "取消预约", notes = "取消预约")
    public BaseResponse removeOrder(String tel,String seatNo){
        if(tel==null&&seatNo==null){
            return BaseResponseUtil.error(504,"参数值为空");
        }
        Seat seat =new Seat();
        seat.setTel(tel);
        seat.setSeatno(seatNo);
        seatService.delOrder(seat);
        return BaseResponseUtil.success();
    }

    @PostMapping("getSeat4Pc")
    public BaseResponse getSeat(String tel){
        QrCodeDto qrCodeDto = qrCodeService.selectByTel(tel);
        if(qrCodeDto!=null){
            List<Seat> list  = seatService.getSeatBytel(qrCodeDto.getOpenid());
            return BaseResponseUtil.success(list);
        }
        return BaseResponseUtil.success();
    }
}
