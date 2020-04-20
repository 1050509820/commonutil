package com.dyzhsw.cardcontrol.service.impl;

import com.dyzhsw.cardcontrol.dto.StationAdd;
import com.dyzhsw.cardcontrol.dto.SwitchPump;
import com.dyzhsw.cardcontrol.dto.equipment;
import com.dyzhsw.cardcontrol.mapper.*;
import com.dyzhsw.cardcontrol.service.equipmentService;
import com.dyzhsw.cardcontrol.util.StringReplaceUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class equipmentServiceImpl  implements equipmentService {
    @Autowired
    private equipmentMapper equipmentMapper;
    @Autowired
    private SwitchPumpMapper switchPumpMapper;

    @Override
    public List<equipment> getEquipmetPage() {
        return equipmentMapper.getEquipmentPage();
    }

    //32,33,E0
    @Override
    public PageInfo<SwitchPump> selectVoluntaryReporting(Integer pageNo, Integer pageSize, String funtionCode, String telemetryAddress,String startTime,String endTime){
        PageHelper.startPage(pageNo,pageSize);
        List<SwitchPump> list = switchPumpMapper.selectAll(funtionCode,telemetryAddress,startTime,endTime);
        PageInfo<SwitchPump> page = new PageInfo<>(list);
        List<SwitchPump> list1 = page.getList();
        for(int i=0;i<list1.size();i++){
            list1.set(i, (SwitchPump) StringReplaceUtils.getStationTime(list1.get(i)));
        }
        page.setList(list1);
        return page;
    }
}
