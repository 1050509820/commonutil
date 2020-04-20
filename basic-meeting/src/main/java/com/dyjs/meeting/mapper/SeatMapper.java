package com.dyjs.meeting.mapper;

import com.dyjs.meeting.dao.Seat;

import java.util.List;

public interface SeatMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seat_table
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seat_table
     *
     * @mbggenerated
     */
    int insert(Seat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seat_table
     *
     * @mbggenerated
     */
    int insertSelective(Seat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seat_table
     *
     * @mbggenerated
     */
    Seat selectBySeatNo(String seatNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seat_table
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Seat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seat_table
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Seat record);

    List<Seat> getSeat();

    List<Seat> selectByTel(String tel);

    int delByDto(Seat seat);
}