package com.dyzhsw.cardcontrol.service;

import com.dyzhsw.cardcontrol.dto.SwitchPump;
import com.dyzhsw.cardcontrol.dto.equipment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface equipmentService {
    List<equipment> getEquipmetPage();

    PageInfo<SwitchPump> selectVoluntaryReporting(Integer pageNo,Integer pageSize,String funtionCode,String telemetryAddress,String startTime,String endTime);
}
