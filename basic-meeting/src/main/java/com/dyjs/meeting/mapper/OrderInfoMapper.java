package com.dyjs.meeting.mapper;

import com.dyjs.meeting.dao.OrderInfoDto;

import java.util.List;
import java.util.Map;

public interface OrderInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbggenerated
     */
    int insert(OrderInfoDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbggenerated
     */
    int insertSelective(OrderInfoDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbggenerated
     */
    OrderInfoDto selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(OrderInfoDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OrderInfoDto record);

    OrderInfoDto selectByTel(String tel);

    List<OrderInfoDto> queryList(OrderInfoDto orderInfoDto);

    Map<String,Integer> getNum();


    OrderInfoDto selectById(Integer id);

    List<OrderInfoDto> query(OrderInfoDto orderInfoDto);
}