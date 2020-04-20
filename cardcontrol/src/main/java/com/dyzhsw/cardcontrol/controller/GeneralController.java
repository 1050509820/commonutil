package com.dyzhsw.cardcontrol.controller;


import com.dyzhsw.cardcontrol.cache.ChannelHandlerContextThreadLocal;
import com.dyzhsw.cardcontrol.dto.equipment;
import com.dyzhsw.cardcontrol.response.ResultObject;
import com.dyzhsw.cardcontrol.service.equipmentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value="GeneralController",tags={"获取设备列表接口"})
public class GeneralController {
    @Autowired
    private equipmentService equipmentService;


    @ApiOperation(notes = "获得设备列表",value = "getEquipmetPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, dataType = "String"),
    })
    @PostMapping("getEquipmetPage")//获得设备列表
    public ResultObject getEquipment(Integer pageSize, Integer pageNo){
        PageHelper.startPage(pageNo,pageSize);
        List<equipment> list = equipmentService.getEquipmetPage();
        for(equipment es:list){
            if(ChannelHandlerContextThreadLocal.LocalVar.GETINSTANCE.get().get(es.getChannel())==null){
                es.setIsonlie("0");
            } else{
                es.setIsonlie("1");
            }
        }
        PageInfo<equipment> page = new PageInfo<equipment>(list);
        return ResultObject.success("page",page);
    }


}
