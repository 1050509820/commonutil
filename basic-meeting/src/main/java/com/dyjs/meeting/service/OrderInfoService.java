package com.dyjs.meeting.service;

import com.dyjs.meeting.dao.OrderInfoDto;

import java.util.List;
import java.util.Map;

public interface OrderInfoService {
    public OrderInfoDto selectOrderInfo(OrderInfoDto orderInfoDao);

    public OrderInfoDto selectBytel(String tel);

    public int insert(OrderInfoDto orderInfoDao);

    public List<OrderInfoDto> selectOrderInfos(OrderInfoDto orderInfoDto);

    public void updateInfo(OrderInfoDto orderInfoDto);

    Map<String,Integer> getNum();

    public OrderInfoDto selectById(Integer id);

    public List<OrderInfoDto> queryForPerson(OrderInfoDto orderInfoDto);

}
