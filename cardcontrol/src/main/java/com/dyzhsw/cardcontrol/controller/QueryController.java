package com.dyzhsw.cardcontrol.controller;

import com.dyzhsw.cardcontrol.dto.*;
import com.dyzhsw.cardcontrol.mapper.*;
import com.dyzhsw.cardcontrol.response.ResultObject;
import com.dyzhsw.cardcontrol.util.StringReplaceUtils;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.metadata.ConfigurationMetadata;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dyzhsw.cardcontrol.service.equipmentService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Api(value="QueryController",tags={"获取实时响应接口"})
@RestController
public class QueryController {
    @Autowired
    private StationTimeMapper stationTimeMapper;
    @Autowired
    private StationAddMapper stationAddMapper;
    @Autowired
    private StationClockMapper stationClockMapper;
    @Autowired
    private ConfigurationMapper configurationMapper;
    @Autowired
    private SwitchPumpMapper switchPumpMapper;
    @Autowired
    private StationModifyMapper stationModifyMapper;
    @Autowired
    private equipmentService service;

    @PostMapping("/getTiming")
    public ResultObject inquireTiming(String functionCode,String  telemetryAddress,String time) throws InterruptedException {
        int i=0;
        while(i<10){
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<StationTime> stationTime = stationTimeMapper.selectStationTime(functionCode,telemetryAddress,null,time,null);
            if(stationTime.size()!=0){
                return ResultObject.success("result",stationTime.get(0));
            }
            i++;
            Thread.sleep(1000);
        }
        return ResultObject.fail("500","遥测站超时无响应");

    }

    @ApiOperation(value = "getStationStatus", notes = "中心站查询遥测站状态和报警信息（46H） ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "functionCode", value = "功能码（46H）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "时间", required = true, dataType = "String")
    })
    @PostMapping("/getStationStatus")
    public ResultObject inquireStationStatus(String functionCode,String  telemetryAddress,String time) throws InterruptedException {
        int i = 0;
        while (i<10){
            List<StationAdd> list = stationAddMapper.selectStationAdd(functionCode,telemetryAddress,null,time,null);
            if (list.size()!=0){
                return ResultObject.success("result",list.get(0));
            }
            i++;
            Thread.sleep(1000);
        }
        return ResultObject.fail("500","遥测站超时无响应");
    }

    @ApiOperation(value = "getStationClock", notes = "中心站设置遥测站时钟（4AH） ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "functionCode", value = "功能码(4AH)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "时间", required = true, dataType = "String")
    })
    @PostMapping("/getStationClock")
    public ResultObject inquireStationClock(String functionCode,String  telemetryAddress,String time) throws InterruptedException {
        int i = 0;
        while (i<10){
            List<StationClock> list = stationClockMapper.selectStationClock(functionCode,telemetryAddress,null,time,null);
            if (list.size()!=0){
                return ResultObject.success("result",list.get(0));
            }
            i++;
            Thread.sleep(1000);
        }
        return ResultObject.fail("500","遥测站超时无响应");
    }

    @ApiOperation(value = "getQueryClock", notes = "中心站查询遥测站时钟（51H） ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "functionCode", value = "功能码(51)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "时间", required = true, dataType = "String")
    })
    @PostMapping("/getQueryClock")
    public ResultObject getQueryClock(String functionCode,String  telemetryAddress,String time) throws InterruptedException {
        int i = 0;
        while (i<10){
            List<StationClock> list = stationClockMapper.selectStationClock(functionCode,telemetryAddress,null,time,null);
            if (list.size()!=0){
                return ResultObject.success("result",list.get(0));
            }
            i++;
            Thread.sleep(1000);
        }
        return ResultObject.fail("500","遥测站超时无响应");
    }

    @PostMapping("getSetConfigeration")//设置参数回复
    public ResultObject getSetConfigeration(String functionCode,String  telemetryAddress,String time) throws InterruptedException {
        int i = 0;
        while (i<10){
            List<Configuration> list = configurationMapper.selectConfigeration(functionCode,telemetryAddress,null,time,null);
            if (list.size()!=0){
                return ResultObject.success("result",list.get(0));
            }
            i++;
            Thread.sleep(1000);
        }
        return ResultObject.fail("500","遥测站超时无响应");
    }

    @PostMapping("getConfigeration")//查询参数回复
    public ResultObject getConfigeration(String functionCode,String  telemetryAddress,String time) throws InterruptedException {
        int i = 0;
        while (i<10){
            List<Configuration> list = configurationMapper.selectConfigeration(functionCode,telemetryAddress,null,time,null);
            if (list.size()!=0){
                return ResultObject.success("result",list.get(0));
            }
            i++;
            Thread.sleep(1000);
        }
        return ResultObject.fail("500","遥测站超时无响应");
    }

    @ApiOperation(value = "getSwitchPump", notes = "中心站查询遥测站开关泵记录（E1H） ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "functionCode", value = "功能码(E1H)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "时间", required = true, dataType = "String")
    })
    @PostMapping("getSwitchPump")
    public ResultObject getSwitchPump(String functionCode,String  telemetryAddress,String time) throws InterruptedException {
        int i = 0;
        while (i<10){
            List<SwitchPump> list = switchPumpMapper.selectSwitchPump(functionCode,telemetryAddress,null,time,null);
            if (list.size()!=0){
                return ResultObject.success("result",list);
            }
            i++;
            Thread.sleep(1000);
        }
        return ResultObject.fail("500","遥测站超时无响应");
    }

    @ApiOperation(value = "getStationModify", notes = "中心站查询遥测站运行参数 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "functionCode", value = "功能码(42,43)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", value = "遥测站地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "时间", required = true, dataType = "String")
    })
    @PostMapping("getStationModify")
    public ResultObject getStationModify(String functionCode,String  telemetryAddress,String time) throws InterruptedException {
        int i = 0;
        while (i<10){
            List<StationModify> list = stationModifyMapper.selectStationModify(functionCode,telemetryAddress,null,time,null);
            if (list.size()!=0){
                return ResultObject.success("result",list);
            }
            i++;
            Thread.sleep(1000);
        }
        return ResultObject.fail("500","遥测站超时无响应");
    }

    @PostMapping("getMessage")
    public   ResultObject   getMessage(String functionCode,String  telemetryAddress,String time) throws InterruptedException {
        if(functionCode==null){
            return null;
        }
        int i=0;
        while(i<5){
            List list = new ArrayList();
            if("37".equals(functionCode)){
                 list = stationTimeMapper.selectStationTime(functionCode,telemetryAddress,null,time,null);
                 if(list.size()!=0) {
                     StationTime stationTime = (StationTime) list.get(0);
                     return ResultObject.success("result", StringReplaceUtils.getStationTime(stationTime));
                 }
            }else if("46".equals(functionCode)){
                 list = stationAddMapper.selectStationAdd(functionCode,telemetryAddress,null,time,null);
                 if(list.size()!=0) {
                     StationAdd stationAdd = (StationAdd) list.get(0);
                     return ResultObject.success("result", StringReplaceUtils.getStationTime(stationAdd));
                 }
            }else if("E1".equals(functionCode)||"e1".equals(functionCode)){
                 list = switchPumpMapper.selectSwitchPump(functionCode,telemetryAddress,null,time,null);
                 if(list.size()!=0) {
                     for (int j = 0; j < list.size(); j++) {
                         SwitchPump switchPump = (SwitchPump) list.get(j);
                         list.set(j, StringReplaceUtils.getStationTime(switchPump));
                     }
                     return ResultObject.success("result", list);
                 }
            }else if("4A".equals(functionCode)||"4a".equals(functionCode)||"51".equals(functionCode)||
                    "47".equals(functionCode)||"4C".equals(functionCode)||"4c".equals(functionCode)){
                 list = stationClockMapper.selectStationClock(functionCode,telemetryAddress,null,time,null);
                 if(list.size()!=0){
                     return ResultObject.success("result", list.get(0));
                 }
            }else if("40".equals(functionCode)||"41".equals(functionCode)){
                 list = configurationMapper.selectConfigeration(functionCode,telemetryAddress,null,time,null);
                if(list.size()!=0){
                    return ResultObject.success("result", list.get(0));
                }
            }else if("42".equals(functionCode)||"43".equals(functionCode)){
                 list = stationModifyMapper.selectStationModify(functionCode,telemetryAddress,null,time,null);
                if(list.size()!=0){
                    return ResultObject.success("result", list.get(0));
                }
            }
            i++;
            Thread.sleep(2000);
        }
        return ResultObject.fail("500","遥测站超时无响应");
    }

    @ApiOperation(value = "获取主动上报数据（分页）",notes = "获取主动上报数据",response = SwitchPump.class)
    @PostMapping("selectVoluntaryReporting")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "第几页,默认为1", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", paramType = "query",value = "每页的数量，默认为10", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "fuctioncode", paramType = "query",value = "功能码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "telemetryAddress", paramType = "query",value = "设备地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "String")

    })
    public ResultObject selectVoluntaryReporting(@RequestParam(name ="pageNo",required = false,defaultValue = "1") Integer pageNo, @RequestParam(name ="pageSize",required = false,defaultValue = "10") Integer pageSize,
                                                 @RequestParam(name ="functionCode",required = false,defaultValue = "")String functionCode,
                                                 @RequestParam(name="telemetryAddress",required = false,defaultValue = "") String telemetryAddress,
                                                 @RequestParam(name="startTime",required = false,defaultValue = "") String startTime,
                                                 @RequestParam(name="endTime",required = false,defaultValue = "") String endTime
                                                 ){
        return ResultObject.success("result",service.selectVoluntaryReporting(pageNo,pageSize,functionCode,telemetryAddress,startTime,endTime));
    }




}
