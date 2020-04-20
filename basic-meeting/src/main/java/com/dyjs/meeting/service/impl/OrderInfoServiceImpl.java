package com.dyjs.meeting.service.impl;

import com.dyjs.meeting.dao.OrderInfoDto;
import com.dyjs.meeting.mapper.OrderInfoMapper;
import com.dyjs.meeting.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;


    @Override
    public OrderInfoDto selectOrderInfo(OrderInfoDto orderInfoDao) {
        return orderInfoMapper.selectByPrimaryKey(orderInfoDao.getId());
    }

    @Override
    public OrderInfoDto selectBytel(String tel) {
        return orderInfoMapper.selectByTel(tel);
    }

    @Override
    public int insert(OrderInfoDto orderInfoDao) {
        return orderInfoMapper.insertSelective(orderInfoDao);
    }

    @Override
    public List<OrderInfoDto> selectOrderInfos(OrderInfoDto orderInfoDto) {
        return orderInfoMapper.queryList(orderInfoDto);
    }

    @Override
    public void updateInfo(OrderInfoDto orderInfoDto) {
        orderInfoMapper.updateByPrimaryKeySelective(orderInfoDto);
    }

    @Override
    public Map<String, Integer> getNum() {
        return orderInfoMapper.getNum();
    }

    @Override
    public OrderInfoDto selectById(Integer id) {
        return orderInfoMapper.selectById(id);
    }

    @Override
    public List<OrderInfoDto> queryForPerson(OrderInfoDto orderInfoDto) {
        return orderInfoMapper.query(orderInfoDto);
    }
}
